package com.th.system.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析指令类
 *
 * @Author zhang bao
 * @Date 2021/12/27 15:24
 * @Version 1.0
 */
public class serialMessage {
    public static List dealxml() {
        List list = new ArrayList();
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载serialCommand.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("src/serialCommand.xml"));
            // 通过document对象获取根节点cultivateCodes
            Element cultivateCodes = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = cultivateCodes.elementIterator();
            // 遍历迭代器，获取根节点中的信息
            while (it.hasNext()) {
                Element code = (Element) it.next();
                List<Attribute> codeAttrs = code.attributes();
                for (Attribute attr : codeAttrs) {
                    list.add(attr);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;
    }
}
