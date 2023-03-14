package com.shop.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtil {
    //用于从XML文件中提取具体变量
    public Integer getText() {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse(new File("shop/src/main/resources/passwordIterations.xml"));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("iterations");
            Node classNode = nl.item(0).getFirstChild();
            Integer cName = Integer.valueOf(classNode.getNodeValue());
            return cName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
