/**
 * @author effine
 * @Date 2016年2月1日  下午8:51:44
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import cn.effine.utils.XmlUtils;
import org.junit.Test;

public class XmlTest {

    @Test
    public void parseXmlTest() {
        XmlUtils.parserXml("src/main/resources/test.xml");
    }

}
