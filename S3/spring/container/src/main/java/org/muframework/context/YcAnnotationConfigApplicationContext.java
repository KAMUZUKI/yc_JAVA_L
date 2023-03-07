package org.muframework.context;

import org.muframework.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 10:16
 **/

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {

    private final Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);

    private final Map<String,Object> beanMap = new ConcurrentHashMap<>();

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /**
     * 初始化容器
     */
    public YcAnnotationConfigApplicationContext(Class<?>... componentClasses) throws ClassNotFoundException, IOException {
        //循环这个动态数组
        List<String> toScanPaths = new ArrayList<String>();
        for (Class cls:componentClasses){
            //配置类中的@Bean注解的方法
            parseBeanAnnotation(cls);
            //1.读取AppConfig对象里面的ComponentScan的value
            if(cls.isAnnotationPresent(ComponentScan.class)){
                ComponentScan componentScan = (ComponentScan) cls.getAnnotation(ComponentScan.class);
                String[] basePackages = componentScan.value();
                if(basePackages==null||basePackages.length<=0){
                    basePackages=componentScan.basePackages();
                }
                if (basePackages!=null&&basePackages.length<=0){
                    basePackages=new String[1];
                    //获取当前配置文件类所在的路径
                    basePackages[0]=cls.getPackage().getName();
                }
                //2.开始扫描basePackages包下的bean，并加载到beanDefinitionMap中
                recursiveLoadBeanDefinition(basePackages);
            }
        }

        try {
            creatBean();
        }catch (Exception e){
            logger.error("创建bean失败",e.getMessage());
            e.printStackTrace();
        }

        //1.读取AppConfig对象里面的ComponentScan的value

        //2.扫描这个包下面的所有类

        //jvm类加载器

            //3.判断这个类上面是否有Component注解
            //4.如果有，就把这个类实例化，放到beanMap里面
        //如果有value，则按指定的路径扫描
        //如果有value，则扫描AppConfig类所在的路径
        //结果则是将待扫描的路径到一个数组
        //2.开始扫描
            //TODO：


        /**循环这个动态数组

        //1.读取AppConfig对象里面的ComponentScan的value
        Class clazz = ClassLoader.getSystemClassLoader().loadClass(AppConfig.class.getName());
        Annotation annotation = clazz.getDeclaredAnnotation(ComponentScan.class);
        ComponentScan componentScan = (ComponentScan) annotation;
        String[] packageName = componentScan.value();

        //2.扫描这个包下面的所有类
        String packagePath = packageName[0].replaceAll("\\.", "/");
        //jvm类加载器
        try{
            Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while(files.hasMoreElements()){
                URL url = files.nextElement();
                logger.info("正在扫描的包路径为:"+ url.getFile());
                //查找此包下的文件
                findPackageClasses(url.getFile(),packageName[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Annotation ComponentAnnotation = clazz.getDeclaredAnnotation(Component.class);
        if (ComponentAnnotation != null) {
            //3.判断这个类上面是否有Component注解
            //4.如果有，就把这个类实例化，放到beanMap里面
            Object o = null;
            try {
                o = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            beanMap.put(clazz.getSimpleName(),o);
        }
        //如果有value，则按指定的路径扫描
        //如果有value，则扫描AppConfig类所在的路径
        //结果则是将待扫描的路径到一个数组
        //2.开始扫描
        //TODO：
        */
    }

    private void doDi() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        for(Map.Entry<String,Object> entry:beanMap.entrySet()){
            String beanId = entry.getKey();
            Object beanObj = entry.getValue();
            Field[] fields = beanObj.getClass().getDeclaredFields();
            for (Field field:fields){
                if (field.isAnnotationPresent(Resource.class)){
                    Resource resource = field.getAnnotation(Resource.class);
                    String toDiBeanId = resource.value();
                    Object obj = getToDiObject(toDiBeanId);
                    //给field赋值
                    field.setAccessible(true);
                    field.set(beanObj,obj);
                }
            }
        }
    }

    private Object getToDiObject(String toDiBeanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //先从beanMap中找
        if(beanMap.containsKey(toDiBeanId)) {
            return beanMap.get(toDiBeanId);
        }
        //如果beanMap中没有，就去beanDefinitionMap中找
        if (!beanDefinitionMap.containsKey(toDiBeanId)){
            throw new RuntimeException("没有找到beanId为"+toDiBeanId+"的bean");
        }
        BeanDefinition bd = beanDefinitionMap.get(toDiBeanId);
        //如果是懒加载的，就创建bean
        if (bd.isLazy()){
            String classPath = bd.getClassInfo();
            Object obj = Class.forName(classPath).newInstance();
            beanMap.put(toDiBeanId,obj);
            return obj;
        }
        if (bd.getScope().equalsIgnoreCase("prototype")){
            String classPath = bd.getClassInfo();
            Object obj = Class.forName(classPath).newInstance();
            return obj;
        }
        return null;
    }

    private void creatBean() throws Exception {
        //循环beanDefinitionMap
        for (Map.Entry<String,BeanDefinition> entry:beanDefinitionMap.entrySet()){
            //如果是单例的，就创建bean
            String beanId = entry.getKey();
            BeanDefinition bd = entry.getValue();
            if (!bd.isLazy() && bd.getScope().equalsIgnoreCase("prototype")){
                String classpath = bd.getClassInfo();
                System.out.println("正在创建bean:"+beanId + "\t" + classpath);
                Object beanObj = Class.forName(classpath).newInstance();
                beanMap.put(beanId,beanObj);
            }
        }
    }

    private void recursiveLoadBeanDefinition(String[] basePackages){
        for (String basePackage:basePackages){
            String packagePath = basePackage.replaceAll("\\.", "/");
            //jvm类加载器
            try{
                Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                while(files.hasMoreElements()){
                    URL url = files.nextElement();
                    logger.info("正在扫描的包路径为:"+ url.getFile());
                    //查找此包下的文件
                    findPackageClasses(url.getFile(),basePackage);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String getBeanId(Class cls){
        String beanId = null;
        Component anno = (Component) cls.getAnnotation(Component.class);
        Repository rep = (Repository) cls.getAnnotation(Repository.class);
        Service ser = (Service) cls.getAnnotation(Service.class);
        Controller con = (Controller) cls.getAnnotation(Controller.class);
        if (anno != null) {
            beanId = anno.value();
        } else if (rep != null) {
            beanId = rep.value();
        } else if (ser != null) {
            beanId = ser.value();
        } else if (con != null) {
            beanId = con.value();
        }

        if(beanId==null||"".equalsIgnoreCase(beanId)){
            String typename = cls.getSimpleName();
            beanId = typename.substring(0,1).toLowerCase() + typename.substring(1);
        }
        return beanId;
    }

    private String getDefinitionName(String clzName) {
        return clzName.substring(0,1).toLowerCase() + clzName.substring(1);
    }

    private void findPackageClasses(String packagePath, String packageName) {
        if (packagePath.startsWith("/")){
            packagePath = packagePath.substring(1);
        }
        //取这个路径下所有的字节码文件
        File file = new File(packagePath);
        File[] classFile = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".class") || pathname.isDirectory()){
                    return true;
                }else {
                    return false;
                }
            }
        });
        if (classFile != null&&classFile.length>0){
            for (File f : classFile){
                if (f.isDirectory()){
                    findPackageClasses(f.getAbsolutePath(),packageName + "." + f.getName());
                }else {
                    //将字节码文件转换成类名
                    URLClassLoader uc = new URLClassLoader(new URL[]{});
                    try {
                        Class clazz = uc.loadClass(packageName + "." + f.getName().replace(".class",""));
                        if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Repository.class)){
                            parseBeanAnnotation(clazz);
                        }
                        logger.info("扫描到的类为:"+ clazz.getName());
                        BeanDefinition bd = new BeanDefinition();
                        if (clazz.isAnnotationPresent(Lazy.class)){
                            bd.setLazy(true);
                        }
                        if (clazz.isAnnotationPresent(Scope.class)){
                            Scope scope = (Scope) clazz.getDeclaredAnnotation(Scope.class);
                            bd.setScope(scope.value());
                        }
                        bd.setClassInfo(packageName + "." + f.getName().replaceAll(".class",""));
                        String beanId = getBeanId(clazz);
                        beanDefinitionMap.put(beanId,bd);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = beanDefinitionMap.get(beanId);
        if (bd == null){
            throw new RuntimeException("没有找到beanId为"+beanId+"的bean");
        }
        String scope = bd.getScope();
        if(scope.equalsIgnoreCase("prototype")){
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
        if (beanMap.containsKey(beanId)){
            return beanMap.get(beanId);
        }
        if (bd.isLazy()){
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                beanMap.put(beanId,obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
        return null;
    }

    private void parseBeanAnnotation(Class clazz){
        try {
            //创建 AppConfig对象 new AppConfig对象
            Object obj = clazz.newInstance();
            String clzName = clazz.getSimpleName();
            BeanDefinition bd = new BeanDefinition();
            bd.setLazy(true);
            bd.setScope("singleton");
            bd.setClassInfo(clazz.getName());
            //配置类本身也被spring托管
            beanDefinitionMap.put(getDefinitionName(clzName),bd);
            beanMap.put(getDefinitionName(clzName),obj);
            //取出配置类里面的所有方法
            Method[] ms = clazz.getDeclaredMethods();
            //完成ioc
            //判断哪些方法上有@Bean注解
            for(Method m : ms){
                if (m.isAnnotationPresent(Bean.class)){
                    //激活这个方法，取得这个方法的返回值 Parson
                    Object o = m.invoke(obj,null);
                    //取出方法名 作为 beans中的键
                    //方法名用作beanId名
                    String id = m.getName();
                    //将这个值当成 beans的值，存入，ioc完成
                    beanMap.put(id,o);

                    BeanDefinition bd2 = new BeanDefinition();
                    bd2.setLazy(true);
                    bd2.setScope("singleton");
                    bd2.setClassInfo(o.getClass().getName());
                    beanDefinitionMap.put(id,bd2);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
