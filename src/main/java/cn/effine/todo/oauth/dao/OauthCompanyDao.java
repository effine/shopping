package cn.effine.todo.oauth.dao;

import java.util.List;

import main.java.com.effine.model.OauthCompany;


public interface OauthCompanyDao {
	
	public OauthCompany getoCompany(String id);
	
	public List<OauthCompany> getAlloCompany();
	
	public void addoCompany(OauthCompany oauthCompany);
	
	
	public boolean deloCompany(String id);
	
	
	public boolean updateoCompany(String id);
	
	
	public boolean checkCid(String uid);
}
