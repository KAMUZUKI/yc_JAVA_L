package org.muframework.context;

import com.mu.AppConfig;
import org.muframework.annotation.Bean;
import org.muframework.annotation.Component;
import org.muframework.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
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

    private final Map<String,BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();
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
                String[] basePakages = componentScan.value();
                if(basePakages==null||basePakages.length<=0){
                    basePakages=componentScan.basePackages();
                }
                if (basePakages!=null&&basePakages.length<=0){
                    basePakages=new String[1];
                    //获取当前配置文件类所在的路径
                    basePakages[0]=cls.getPackage().getName();
                }
                //2.开始扫描basePackages包下的bean，并加载到beanDefinitionMap中
                recursiveLoadBeanDefinition(basePakages);
            }
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

    private void recursiveLoadBeanDefinition(String[] basePakages){
        for (String basePakage:basePakages){
            String packagePath = basePakage.replaceAll("\\.", "/");
            //jvm类加载器
            try{
                Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                while(files.hasMoreElements()){
                    URL url = files.nextElement();
                    logger.info("正在扫描的包路径为:"+ url.getFile());
                    //查找此包下的文件
                    findPackageClasses(url.getFile(),basePakage);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String getDefinitionName(String clzName) {
        return clzName.substring(0,1).toLowerCase() + clzName.substring(1);
    }

    private void parseBeanAnnotation(Class clazz){
        try {
            //创建 AppConfig对象 new AppConfig对象
            Object obj = clazz.newInstance();
            String clzName = clazz.getSimpleName();
            //配置类本身也被spring托管
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
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
                        logger.info("扫描到的类为:"+ clazz.getName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }
}
