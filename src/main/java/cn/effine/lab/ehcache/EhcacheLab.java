package cn.effine.lab.ehcache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author effine
 * @Date 2017-03-15 10:53
 * @email zhangyafei#co-mall.com
 */
public class EhcacheLab {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"ehcache.xml", "spring-ehcache.xml"});
        context.start();
        Ehcache ehcache = (Ehcache) context.getBean("recommendCache");
        ehcache.put(new Element("testkey","HelloWorld"));
        Element element = ehcache.get("testkey");
        if (element != null)
            System.out.println("value:"+element.getObjectValue());
        System.exit(0);
    }
}
