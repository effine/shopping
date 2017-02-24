/**
 * @author effine
 * @date 2014年10月30日  下午11:42:14
 */

package cn.effine.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Desc JSON 封装类(采用fastjson实现)
 */
public class JsonUtils {

    private JsonUtils() {
    }

    /**
     * @param obj Object对象
     * @return String
     * @desc 对象转换成json
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * @param obj List对象
     * @return String
     * @desc List转换成json(空值返回"")
     */
    public static String listToJSONString(List<?> list) {
        // TODO FEI Model转换成json时,空字符串转换成""
        return JSON.toJSONString(list, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * @param obj Map对象
     * @return String
     * @desc Map转换成json(空值返回"")
     */
    public static String mapToJSONString(Map<String, Object> map) {
        // TODO FEI Model转换成json时,空字符串转换成""
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @param jsonStr 待转换的json字符串
     * @param clazz   目标Model Class
     * @return List<Model>
     * @desc Json字符串转换成Model
     */
    public static <T extends Serializable> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

    public static final JsonUtils jsonUtil = new JsonUtils();

    /***
     * 把JSON文本parse为JSONObject或者JSONArray
     *
     * @param text
     * @return
     */
    public Object jsonToObject(String text) {
        return JSON.parse(text);
    }

    /***
     * 把JSON文本parse成JSONObject
     *
     * @param text
     * @return
     */
    public JSONObject jsonToJsonObject(String text) {
        return JSON.parseObject(text);
    }

    /***
     * 把JSON文本parse为JavaBean
     *
     * @param text
     * @return
     */
    public <T> T jsonToModel(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /***
     * 把JSON文本parse成JSONArray
     *
     * @param text
     * @return
     */
    public JSONArray jsonToJsonArray(String text) {
        return JSON.parseArray(text);
    }

    /***
     * 把JSON文本parse成JavaBean集合
     *
     * @param text
     * @return
     */
    public <T> List<T> jsonToList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /***
     * 将JavaBean序列化为JSON文本
     *
     * @param text
     * @return
     */
    public String objectToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /***
     * 将JavaBean序列化为带格式的JSON文本
     *
     * @param text
     * @return
     */
    public String modelToFMTJson(Object object, boolean prettyFormat) {
        return JSON.toJSONString(object, prettyFormat);
    }

    /***
     * 把将JavaBean转换为JSONObject或者JSONArray。
     *
     * @param text
     * @return
     */
    public Object modelToObject(Object javaObject) {
        return JSON.toJSON(javaObject);
    }
}

