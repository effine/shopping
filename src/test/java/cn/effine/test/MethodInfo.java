/**
 * @author effine
 * @Date 2015年11月4日  下午3:54:23
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
	String author() default "Pankaj";

	String date();

	int revision() default 1;

	String comments();
}
