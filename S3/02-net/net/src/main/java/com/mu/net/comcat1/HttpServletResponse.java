package com.mu.net.comcat1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * @author : MUZUKI
 * @date : 2023-01-05 15:30
 **/

public class HttpServletResponse {
    private HttpServletRequest request;
    private OutputStream oos;

    public HttpServletResponse(HttpServletRequest request, OutputStream oos) {
        this.request = request;
        this.oos = oos;
    }

    public void send() {
        String uri = this.request.getRequestURI();
        String realPath = this.request.getRealPath();
        File f = new File(realPath, uri);
        byte[] fileContent = null;
        String responseProtocol = null;
        if (!f.exists()) {
            //文件不存在 则 4xx 响应
            fileContent = readFile(new File(realPath, "/404.html"));
            responseProtocol = gen404(fileContent);
        } else {
            //文件存在则读取 回2xx
            fileContent = readFile(new File(realPath, uri));
            responseProtocol = gen200(fileContent);
        }

        try {
            oos.write(responseProtocol.getBytes());
            oos.flush();
            oos.write(fileContent);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String gen200(byte[] fileContent) {
        String protocol200 = "";
        //先取出请求的资源的类型
        String uri = this.request.getRequestURI();
        //从uri取后缀名
        int index = uri.lastIndexOf(".");
        if (index >= 0) {
            index += 1;
        }
        //TODO:策略模式代替if else
        String fileExtension = uri.substring(index);
        String serverXmlPath = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "server.xml";
        try(InputStream iis = new FileInputStream(serverXmlPath);){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(iis);
            NodeList nodeList = doc.getElementsByTagName("mime-mapping");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String extension = element.getElementsByTagName("extension").item(0).getTextContent();
                String mimeType = element.getElementsByTagName("mime-type").item(0).getTextContent();
                if (fileExtension.equals(extension)) {
                    protocol200 = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: "+ mimeType + "\r\n" +
                    "Content-Length: " + fileContent.length + "\r\n" +
                    "Connection: keep-alive\r\n" +
                    "\r\n";
                    return protocol200;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if ("JPG".equalsIgnoreCase(fileExtension)) {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: image/jpeg\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        } else if ("css".equalsIgnoreCase(fileExtension)) {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/css\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        } else if ("js".equalsIgnoreCase(fileExtension)) {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: application/javascript\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        } else if ("gif".equalsIgnoreCase(fileExtension)) {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: image/gif\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        } else if ("png".equalsIgnoreCase(fileExtension)) {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: image/png\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        } else {
//            protocol200 = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/html;charset=utf-8\r\n" +
//                    "Content-Length: " + fileContent.length + "\r\n" +
//                    "\r\n";
//        }

        protocol200 = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n" +
                    "Content-Length: " + fileContent.length + "\r\n" +
                    "Connection: keep-alive\r\n" +
                    "\r\n";
        return protocol200;
    }

    private String gen404(byte[] fileContent) {
        String protocol404 = "HTTP/1.1 404 Not Found\r\nContent-Type:text/html;charset=utf-8\r\nContent-Length: " + fileContent.length + "\r\n";
        protocol404 += "Server: kitty server\r\n\r\n";
        return protocol404;
    }

    /**
     * 读取本地文件
     *
     * @param file
     * @return
     */
    private byte[] readFile(File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bs = new byte[100 * 1024];
            int length = -1;
            while ((length = fis.read(bs, 0, bs.length)) != -1) {
                baos.write(bs, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }
}
