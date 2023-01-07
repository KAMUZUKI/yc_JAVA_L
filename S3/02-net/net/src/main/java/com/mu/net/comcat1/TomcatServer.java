package com.mu.net.comcat1;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
