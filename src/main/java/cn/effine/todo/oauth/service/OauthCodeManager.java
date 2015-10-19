package cn.effine.service;

import java.util.List;

import main.java.com.effine.model.OauthCode;

public interface OauthCodeManager {
    public OauthCode getoCode(String id);
	
	public List<OauthCode> getAlloCode();
	
	public void addoCode(OauthCode oauthCode);
	
	
	public boolean deloCode(String id);
	
	
	public boolean updateoCode(String id);
	
	
	public boolean checkCid(String uid);
}
