package cn.effine.todo.oauth.dao;

import java.util.List;

import main.java.com.effine.model.OauthCode;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class OauthCodeDaoImpl implements OauthCodeDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public boolean checkCid(String name){
		String sql = "select * from yl_oauth2_code where company_name='"+name+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		boolean flag = false;
		if(query.list().size()>0){
			flag = true;
		}
		return  flag;
	}



	@Override
	public void addoCode(OauthCode oauthCode) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(oauthCode);
	}



	@Override
	public boolean deloCode(String id) {
		// TODO Auto-generated method stub
		String hql = "delete OauthCode u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (query.executeUpdate() > 0);
	}



	@Override
	public List<OauthCode> getAlloCode() {
		// TODO Auto-generated method stub
		String hql = "from OauthCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}



	@Override
	public OauthCode getoCode(String id) {
		// TODO Auto-generated method stub
		String hql = "from OauthCode u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (OauthCode)query.uniqueResult();
	}



	@Override
	public boolean updateoCode(String id) {
		// TODO Auto-generated method stub
		return false;
	}




}
