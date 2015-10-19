package cn.effine.todo.oauth.dao;

import java.util.List;

import main.java.com.effine.model.OauthCompany;
import main.java.com.effine.model.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class OauthCompanyDaoImpl implements OauthCompanyDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public boolean checkCid(String name){
		String sql = "select * from yl_oauth2_company where company_name='"+name+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		boolean flag = false;
		if(query.list().size()>0){
			flag = true;
		}
		return  flag;
	}



	@Override
	public void addoCompany(OauthCompany oauthCompany) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(oauthCompany);
	}



	@Override
	public boolean deloCompany(String id) {
		// TODO Auto-generated method stub
		String hql = "delete oauthCompany u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (query.executeUpdate() > 0);
	}



	@Override
	public List<OauthCompany> getAlloCompany() {
		// TODO Auto-generated method stub
		String hql = "from oauthCompany";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}



	@Override
	public OauthCompany getoCompany(String id) {
		// TODO Auto-generated method stub
		String hql = "from oauthCompany u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (OauthCompany)query.uniqueResult();
	}



	@Override
	public boolean updateoCompany(String id) {
		// TODO Auto-generated method stub
		return false;
	}




}
