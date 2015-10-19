package cn.effine.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import main.java.com.effine.json.BrowserLaunch;
import main.java.com.effine.json.JsonObject;
import main.java.com.effine.json.JsonUtil;
import main.java.com.effine.json.MD5Tool;

public class ServerOAuth {
	private static String access_token;
	private static String expires_in;
	private static String code;
	private static Properties pros = OAuthConfig.getPros();
	private static JsonObject json = new JsonObject();
	private static  JsonUtil util = new JsonUtil();
	private static  JsonUtil codeutil = new JsonUtil();
	public static JsonObject getJson() {
		return json;
	}
	public static void setJson(JsonObject json) {
		ServerOAuth.json = json;
	}
	

	public static void genericAccessToken(String client_id ,String client_secret) {
		access_token = System.currentTimeMillis()+client_id+client_secret;
		expires_in = "3600";
		json.getOauthMap().put("access_token", access_token);
		json.getOauthMap().put("expires_in",expires_in);
	}
	public static String getAccessToken(){
		Map<String,String> accessTokenMap = new HashMap<String,String>();
		accessTokenMap.put("access_token",json.getOauthMap().get("access_token"));
		accessTokenMap.put("expires_in",json.getOauthMap().get("expires_in"));
		util.put("access_token", accessTokenMap);
		return util.toString();
	}
	
	public static String getAuthCode(){
		Map<String,String> AuthCodeMap = new HashMap<String,String>();
		AuthCodeMap.put("code",json.getOauthMap().get("code"));
		codeutil.put("AuthCode", AuthCodeMap);
		return codeutil.toString();
	}
	
	public static void genericCode(){
		code = System.currentTimeMillis()+"code";
		json.getOauthMap().put("code",code);
	}
	
	public String genericClientId(){
		String s = String.valueOf((new Random().nextInt(4)));
		return MD5Tool.encoding(s);
	}

	public void redirectURI(){
		String url = pros.getProperty("redirect_uri");
		BrowserLaunch.openURL(url);
	}
	public static void redirectGrantCode(){
		String url = pros.getProperty("redirect_uri");
		url = url + "?code=" + json.getOauthMap().get("code");
		BrowserLaunch.openURL(url);
	}
}
