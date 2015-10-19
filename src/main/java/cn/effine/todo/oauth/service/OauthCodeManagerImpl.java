package cn.effine.service;

import java.util.List;

import main.java.com.effine.dao.OauthCodeDao;
import main.java.com.effine.model.OauthCode;

public class OauthCodeManagerImpl implements OauthCodeManager {

	public OauthCodeDao oauthCodeDao;
	
	

	public void setOauthCodeDao(OauthCodeDao oauthCodeDao) {
		this.oauthCodeDao = oauthCodeDao;
	}

	@Override
	public void addoCode(OauthCode oauthCode) {
		// TODO Auto-generated method stub
		oauthCodeDao.addoCode(oauthCode);
	}

	@Override
	public boolean checkCid(String uid) {
		// TODO Auto-generated method stub
		return oauthCodeDao.checkCid(uid);
	}

	@Override
	public boolean deloCode(String id) {
		// TODO Auto-generated method stub
		return oauthCodeDao.deloCode(id);
	}

	@Override
	public List<OauthCode> getAlloCode() {
		// TODO Auto-generated method stub
		return oauthCodeDao.getAlloCode();
	}

	@Override
	public OauthCode getoCode(String id) {
		// TODO Auto-generated method stub
		return oauthCodeDao.getoCode(id);
	}

	@Override
	public boolean updateoCode(String id) {
		// TODO Auto-generated method stub
		return oauthCodeDao.updateoCode(id);
	}


}
