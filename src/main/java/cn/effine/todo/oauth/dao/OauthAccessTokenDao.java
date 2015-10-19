package cn.effine.todo.oauth.dao;

import java.util.List;

import cn.effine.model.OauthAccessToken;


public interface OauthAccessTokenDao {
	
	public OauthAccessToken getoAccessToken(String id);
	
	public List<OauthAccessToken> getAlloAccessToken();
	
	public void addoAccessToken(OauthAccessToken oauthAccessToken);
	
	
	public boolean deloAccessToken(String id);
	
	
	public boolean updateoAccessToken(String id);
	
	
	public boolean checkCid(String uid);
	
	public boolean checkCode(String code,String client_id,String client_secret);
	
	public boolean checkToken(String accessToken,String user_id);
}
