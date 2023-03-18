package com.shop.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtil2 {
    //用于从XML文件中提取具体变量
    public static String getText() {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse(new File("main/src/main/resources/pictureLocation.xml"));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("location");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();
            return cName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
