/**
 * @author effine
 * @Date 2016年3月7日  下午2:15:17
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.collection;

public class InterviewTest {

    public static String str = "good";
    public static char[] ch = {'a', 'b', 'c'};

    public static void main(String[] args) {
        InterviewTest tt = new InterviewTest();
        tt.exchange(str, ch);
        System.out.println(str);
        System.out.println(ch);

    }

    public void exchange(String str, char[] ch) {
        str = "hello";
        ch[0] = 'g';
    }
}
