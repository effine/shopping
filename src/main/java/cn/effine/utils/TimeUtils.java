/**
 * @author effine
 * @Date 2015年12月6日  上午1:02:38
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对时间和日期进行处理
 *
 */
public class TimeUtils {

	/**
	 * 获取当前时间
	 *
	 * @return 标准格式化时间（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getCurrentTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(currentTime);
	}
}
