package cn.effine.service;

import java.util.List;

import main.java.com.effine.dao.OauthAccessTokenDao;
import main.java.com.effine.model.OauthAccessToken;

public class OauthAccessTokenManagerImpl implements OauthAccessTokenManager {

	public OauthAccessTokenDao oauthAccessTokenDao;
	
	

	public void setOauthAccessTokenDao(OauthAccessTokenDao oauthAccessTokenDao) {
		this.oauthAccessTokenDao = oauthAccessTokenDao;
	}

	@Override
	public void addoAccessToken(OauthAccessToken oauthAccessToken) {
		// TODO Auto-generated method stub
		oauthAccessTokenDao.addoAccessToken(oauthAccessToken);
	}

	@Override
	public boolean checkCid(String uid) {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.checkCid(uid);
	}

	@Override
	public boolean deloAccessToken(String id) {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.deloAccessToken(id);
	}

	@Override
	public List<OauthAccessToken> getAlloAccessToken() {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.getAlloAccessToken();
	}

	@Override
	public OauthAccessToken getoAccessToken(String id) {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.getoAccessToken(id);
	}

	@Override
	public boolean updateoAccessToken(String id) {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.updateoAccessToken(id);
	}

	@Override
	public boolean checkCode(String code, String client_id, String client_secret) {
		// TODO Auto-generated method stub
		return oauthAccessTokenDao.checkCode(code, client_id, client_secret);
	}

	public boolean checkToken(String accessToken,String user_id){
		return oauthAccessTokenDao.checkToken(accessToken, user_id);
	}

}
