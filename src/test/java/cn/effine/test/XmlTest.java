/**
 * @author effine
 * @Date 2016年2月1日  下午8:51:44
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import org.junit.Test;

import cn.effine.utils.XmlUtils;

public class XmlTest {

    @Test
    public void parseXmlTest() {
        XmlUtils.parserXml("src/main/resources/test.xml");
    }

}
