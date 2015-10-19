package cn.effine.service;

import java.util.List;

import main.java.com.effine.dao.OauthCompanyDao;
import main.java.com.effine.model.OauthCompany;

public class OauthCompanyManagerImpl implements OauthCompanyManager {

	public OauthCompanyDao oauthCompanyDao;
	
	

	public void setOauthCompanyDao(OauthCompanyDao oauthCompanyDao) {
		this.oauthCompanyDao = oauthCompanyDao;
	}

	@Override
	public void addoCompany(OauthCompany oauthCompany) {
		// TODO Auto-generated method stub
		oauthCompanyDao.addoCompany(oauthCompany);
	}

	@Override
	public boolean checkCid(String uid) {
		// TODO Auto-generated method stub
		return oauthCompanyDao.checkCid(uid);
	}

	@Override
	public boolean deloCompany(String id) {
		// TODO Auto-generated method stub
		return oauthCompanyDao.deloCompany(id);
	}

	@Override
	public List<OauthCompany> getAlloCompany() {
		// TODO Auto-generated method stub
		return oauthCompanyDao.getAlloCompany();
	}

	@Override
	public OauthCompany getoCompany(String id) {
		// TODO Auto-generated method stub
		return oauthCompanyDao.getoCompany(id);
	}

	@Override
	public boolean updateoCompany(String id) {
		// TODO Auto-generated method stub
		return oauthCompanyDao.updateoCompany(id);
	}


}
