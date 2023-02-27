package org.muframework.context;

import com.mu.AppConfig;
import org.muframework.annotation.Component;
import org.muframework.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 10:16
 **/

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {
    private Map<String,Object> beanMap = new ConcurrentHashMap<String,Object>();

    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);

    /**
     * 初始化容器
     */
    public YcAnnotationConfigApplicationContext(Class<?>... componentClasses) throws ClassNotFoundException, IOException {
        //循环这个动态数组

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
