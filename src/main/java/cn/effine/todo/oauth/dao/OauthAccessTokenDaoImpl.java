package cn.effine.todo.oauth.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import cn.effine.model.OauthAccessToken;

public class OauthAccessTokenDaoImpl implements OauthAccessTokenDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean checkCid(String name){
		String sql = "select * from yl_oauth2_accesstoken where company_name='"+name+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		boolean flag = false;
		if(query.list().size()>0){
			flag = true;
		}
		return  flag;
	}
	
	public boolean checkCode(String code,String client_id,String client_secret){
		String sql = "select a.id as id1,a.user_id,a.code,a.redirect_url as url1,a.client_id as client_id1,a.state,a.response_type,a.scope,c.id as id2,c.client_id as client_id2,c.client_secret,c.company_name,c.company_url,c.company_detail,c.company_license_number,c.email,c.contact,c.phone from yl_oauth2_code a join yl_oauth2_company c  on a.client_id=c.client_id   where a.client_id='"+client_id+"'"+" and a.code='"+code+"' and c.client_secret='"+client_secret+"'";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		boolean flag = false;
		if(query.list().size()>0){
			flag = true;
		}
		return  flag;
	}

	public boolean checkToken(String accessToken,String user_id){
		String sql = "select a.* from yl_oauth2_accesstoken  a where a.access_token='"+accessToken+"' and a.user_id='"+user_id+"'" ;
		System.out.println(sql);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		boolean flag = false;
		if(query.list().size()>0){
			flag = true;
		}
		return  flag;
	}

	@Override
	public void addoAccessToken(OauthAccessToken oauthAccessToken) {
		sessionFactory.getCurrentSession().save(oauthAccessToken);
	}

	@Override
	public boolean deloAccessToken(String id) {
		String hql = "delete OauthAccessToken u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate() > 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OauthAccessToken> getAlloAccessToken() {
		String hql = "from OauthAccessToken";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public OauthAccessToken getoAccessToken(String id) {
		String hql = "from OauthAccessToken u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (OauthAccessToken)query.uniqueResult();
	}

	@Override
	public boolean updateoAccessToken(String id) {
		return false;
	}
}
