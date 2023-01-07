package com.mu.net;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author : MUZUKI
 * @date : 2023-01-05 17:10
 **/

public class getFileExtensionTest {
    @Test
    public void test1(){
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
                System.out.println(extension + " : " + mimeType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
