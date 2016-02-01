/**
 * @author effine
 * @Date 2016年2月1日  下午7:26:09
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUtils extends DefaultHandler {
	private Vector<String> tagName;

	private Vector<String> tagValue;

	private int step;

	// 开始解析XML文件
	public void startDocument() throws SAXException {
		tagName = new Vector<String>();
		tagValue = new Vector<String>();
		step = 0;
	}

	// 结束解析XML文件
	public void endDocument() throws SAXException {
		for (int i = 0; i < tagName.size(); i++) {
			if (!tagName.get(i).equals("") || tagName.get(i) != null) {
				System.out.println("节点名称：" + tagName.get(i));
				System.out.println("节点值：" + tagValue.get(i));
			}
		}
	}

	/**
	 * 在解释到一个开始元素时会调用此方法.但是当元素有重复时可以自己写算法来区分 这些重复的元素.qName是什么? <name:page
	 * ll=""></name:page>这样写就会抛出SAXException错误 通常情况下qName等于localName
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 节点名称
		tagName.add(qName);
		// 循环输出属性
		for (int i = 0; i < attributes.getLength(); i++) {
			// 获取属性名称
			System.out.println("属性名称：" + attributes.getQName(i));
			// 获取属性值
			System.out.println("属性值："
					+ attributes.getValue(attributes.getQName(i)));
		}

	}

	/**
	 * 在遇到结束标签时调用此方法
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		step = step + 1;
	}

	/**
	 * 读取标签里的值,ch用来存放某行的xml的字符数据,包括标签,初始大小是2048, 每解释到新的字符会把它添加到char[]里。 *
	 * 注意,这个char字符会自己管理存储的字符, 并不是每一行就会刷新一次char,start,length是由xml的元素数据确定的,
	 * 暂时找不到规律,以后看源代码.
	 * 
	 * 这里一个正标签，反标签都会被执行一次characters，所以在反标签时不用获得其中的值
	 */
	public void characters(char ch[], int start, int length)
			throws SAXException {
		// 只要当前的标签组的长度一至，值就不赋，则反标签不被计划在内
		if (tagName.size() - 1 == tagValue.size()) {
			tagValue.add(new String(ch, start, length));
		}
	}

	public static void main(String[] args) {
		String filename = "src/main/java/zuidaima.com.xml";
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = spf.newSAXParser();
			// saxParser.parse(new File(filename), new ParseXML());
			 saxParser.parse(new File(filename), new HandlerBase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector getTagName() {
		return tagName;
	}

	public void setTagName(Vector tagName) {
		this.tagName = tagName;
	}

	public Vector getTagValue() {
		return tagValue;
	}

	public void setTagValue(Vector tagValue) {
		this.tagValue = tagValue;
	}
}
