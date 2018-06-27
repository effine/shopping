/**
 * @author effine
 * @Date 2016年2月1日  下午7:26:09
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * dom4j解析xml
 */
public class XmlUtils {

    private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    private XmlUtils() {
    }

    /**
     * 创建XML文档
     *
     * @param fileName 文件名
     */
    public static void createXml(String fileName) {
        Document document = DocumentHelper.createDocument();
        Element employees = document.addElement("employees");
        Element employee = employees.addElement("employee");
        Element name = employee.addElement("name");
        name.setText("ddvip");
        Element sex = employee.addElement("sex");
        sex.setText("m");
        Element age = employee.addElement("age");
        age.setText("29");
        try {
            Writer fileWriter = new FileWriter(fileName);
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * 解析XML文件
     *
     * @param fileName 待解析的XML文件
     */
    public static void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Element employees = document.getRootElement();

            for (Iterator<?> i = employees.elementIterator(); i.hasNext(); ) {
                Element employee = (Element) i.next();
                for (Iterator<?> j = employee.elementIterator(); j.hasNext(); ) {
                    Element node = (Element) j.next();
                    logger.info(node.getName() + ":" + node.getText());
                    logger.info(node.getNodeTypeName());
                }

            }
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
    }
}
