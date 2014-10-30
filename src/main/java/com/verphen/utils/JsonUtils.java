
/**
 * @author verphen
 * @date 2014年10月30日  下午11:42:14
 */

package com.verphen.utils;

import com.alibaba.fastjson.JSON;

/**
 * Json工具类
 */
public class JsonUtils {
	
	public String toJsonString(String str){
		return JSON.toJSONString(str);
	}
}


