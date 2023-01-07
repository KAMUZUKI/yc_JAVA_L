package com.mu.net.comcat2;

import com.mu.net.comcat2.javax.servlet.ServletContext;
import com.mu.net.comcat2.javax.servlet.WebServlet;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author : MUZUKI
 * @date : 2023-01-04 17:32
 **/

public class TomcatServer {
    static final Logger logger = Logger.getLogger(TomcatServer.class);

    public static void main(String[] args) {
        logger.debug("程序启动");
        TomcatServer server = new TomcatServer();
        int port = server.parsePortFromXml();
        logger.debug("服务器配置端口为:" + port);
        server.startServer(port);
    }

    private void startServer(int port){
        boolean flag = true;

        String packageName = "com.mu";
        String packagePath = packageName.replaceAll("\\.", "/");
        //服务器自动时 扫描它所有的 classes，查找@WebServlet注解，将其解析出来，存储到ServletContext中的map中
        //jvm类加载器
        try{
            Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while(files.hasMoreElements()){
                URL url = files.nextElement();
                logger.info("正在扫描的包路径为:"+ url.getFile());
                //查找此包下的文件
                findPackageClasses(url.getFile(),packageName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try(ServerSocket ss = new ServerSocket(port)) {
            logger.debug("服务器启动成功，配置端口为:" + port);
            while (flag) {
                try{
                    Socket s = ss.accept();
                    s.setKeepAlive(true);
                    logger.debug("客户端:" + s.getRemoteSocketAddress() + "已连接");
                    TaskService task = new TaskService(s);
                    Thread t = new Thread(task);
                    t.start();
                }catch (Exception e){
                    logger.error("客户端连接失败...");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
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
                        if (clazz.isAnnotationPresent(WebServlet.class)){
                            logger.info("加载了一个类:" + clazz.getName());
                            WebServlet anno = (WebServlet) clazz.getAnnotation(WebServlet.class);
                            String url = anno.value();
                            //通过 注解的value()方法取出 url地址 存到ServletContext的map中
                            ServletContext.servletClass.put(url,clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private int parsePortFromXml() {
        int port = 8080;
        String serverXmlPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "server.xml";
        try(InputStream iis = new FileInputStream(serverXmlPath);){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(iis);
            NodeList nodeList = doc.getElementsByTagName("Connector");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element node = (Element) nodeList.item(i);
                port = Integer.parseInt(node.getAttribute("port"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
