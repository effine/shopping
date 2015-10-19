package cn.effine.oauth;

import java.util.Properties;

public class OAuth {
	private static String client_id = "client_id";
	private static String client_secret = "client_secret";
	private static String redirect_uri = "redirect_uri";
	private static String access_token = "access_token";
	private static String user_id = "user_id";
	private static Properties pros = OAuthConfig.getPros();
	private static String baseURL = "baseURL";
	public static String getBaseURL() {
		return baseURL;
	}
	public static String getClient_id() {
		return pros.getProperty(client_id);
	}
	public static String getClient_secret() {
		return pros.getProperty(client_secret);
	}
	public static String getRedirect_uri() {
		return pros.getProperty(redirect_uri);
	}
	public static String getAccess_token() {
		return pros.getProperty(access_token);
	}
	public static String getUser_id() {
		return pros.getProperty(user_id);
	}
	public static String fabricateURL(){//����һ����������url
		String url = "";
		String id = pros.getProperty(client_id);
		String secret = pros.getProperty(client_secret);
		String uri = pros.getProperty(redirect_uri);
		url = pros.getProperty(baseURL);
		String state = pros.getProperty("state");
		String response_type = pros.getProperty("response_type");
		String scope = pros.getProperty("scope");
		if(id != null && secret != null && uri != null){
			url = url + "?client_id="+id+"&client_secret="+secret+"&redirect_uri="+uri+"&state="+state+"&response_type="+response_type+
			"&scope="+scope;
		}
		return url;
	}
}
