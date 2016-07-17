/**
 * @author  effine
 * @email   iballader#gmail.com
 * @date    Jul 6, 2016  10:38:52 PM
 * @site    http://effine.cn
 * @since   1.0
 */

package cn.effine.lab.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

public class HttpRequestUtils {
	/** 请求服务器前缀url */
    private static final String PREFIX_URL = "http://localhost:8081";
    /** 编码格式*/
    //private static final String ENCODE_FORMAT = "UTF-8";
    /** 解码格式*/
    private static final String DECODE_FORMAT = "UTF-8";

    /**
     * get请求处理方法
     *
     * @author effine
     *
     * @param request
     *            [javax.servlet.http.HttpServletRequest] 请求Request
     * @param url
     *            请求地址；参数为restful的query类型(请求url不包含可变参数)即为null
     * @param param
     *            url参数
     * @return Map(包含两个key：Http状态码httpCode；方法返回值responseBody)
     *
     * @throws IOException
     * @throws HttpException
     */
    public static Map<String, Object> get(HttpServletRequest request, String url) throws HttpException, IOException{
        if(null == url)
            url = request.getRequestURI();
        HttpMethod method = new GetMethod(PREFIX_URL + url);
        return  passHeaderAndParam(request, method);
    }

    /**
     * post请求处理方法
     *
     * @author effine
     *
     * @param request
     *            [javax.servlet.http.HttpServletRequest] 请求Request
     * @param url
     *            请求路径(如果为空则通过request获取)
     * @return Map(包含两个key：Http状态码httpCode；方法返回值responseBody)
     *
     * @throws IOException
     * @throws HttpException
     */
    public static Map<String, Object> post(HttpServletRequest request, String url) throws HttpException, IOException{
        if(null == url)
            url = request.getRequestURI();
        HttpMethod method = new PostMethod(PREFIX_URL + url);
        return  passHeaderAndParam(request, method);
    }

    /**
     * put请求处理方法
     *
     * @author effine
     *
     * @param request
     *            [javax.servlet.http.HttpServletRequest] 请求Request
     * @param url
     *            请求路径(如果为空则通过request获取)
     * @return Map(包含两个key：Http状态码httpCode；方法返回值responseBody)
     *
     * @throws IOException
     * @throws HttpException
     */
    public static Map<String, Object> put(HttpServletRequest request, String url) throws HttpException, IOException{
        if(null == url)
            url = request.getRequestURI();
        HttpMethod method = new PutMethod(PREFIX_URL + url);
        return  passHeaderAndParam(request, method);
    }

    /**
     * delete 请求处理方法
     *
     * @author effine
     *
     * @param request
     *            [javax.servlet.http.HttpServletRequest] 请求Request
     * @param url
     *            请求路径(如果为空则通过request获取)
     * @return Map(包含两个key：Http状态码httpCode；方法返回值responseBody)
     *
     * @throws IOException
     * @throws HttpException
     */
    public static Map<String, Object> delete(HttpServletRequest request, String url) throws HttpException, IOException{
        if(null == url)
            url = request.getRequestURI();
        HttpMethod method = new DeleteMethod(PREFIX_URL + url);
        return  passHeaderAndParam(request, method);
    }

    /**
     * 操作Apache HttpRequest工具方法，并返回处理结果
     *
     * @author effine
     *
     * @param request
     *            [javax.servlet.http.HttpServletRequest]
     * @param method
     *            Apache HttpMethod
     * @return Map(包含两个key：Http状态码httpCode；方法返回值responseBody)
     *
     * @throws IOException
     * @throws HttpException
     */
    private static Map<String, Object> passHeaderAndParam(HttpServletRequest request, HttpMethod method) throws HttpException, IOException{
        // 传递Header
        @SuppressWarnings("unchecked")
        Enumeration<String> headerRnum = request.getHeaderNames();
        while(headerRnum.hasMoreElements()){
            String key =  headerRnum.nextElement();
            method.setRequestHeader(key, request.getHeader(key));
        }
        method.setRequestHeader("apiVersion", "2.0");

        // 传递参数Param
        List<NameValuePair> params = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Enumeration<String> paramEnum = request.getParameterNames();
        while(paramEnum.hasMoreElements()){
            String key =  paramEnum.nextElement();
            params.add(new NameValuePair(key, request.getParameter(key)));
        }
        method.setQueryString(params.toArray(new NameValuePair[0]));
        HttpClient client = new HttpClient();
        client.executeMethod(method);

        Map<String, Object> map = new HashMap<>();
        map.put("httpCode", method.getStatusCode());
        map.put("responseBody", new String(method.getResponseBody(), DECODE_FORMAT));
        method.releaseConnection();
        return map;
    }
}


