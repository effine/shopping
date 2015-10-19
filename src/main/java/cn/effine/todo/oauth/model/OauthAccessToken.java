package cn.effine.todo.oauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "yl_oauth2_accesstoken")
public class OauthAccessToken {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id; // 唯一锟斤拷锟斤拷
	@Column(length = 32)
	private String user_id; // 锟矫伙拷锟斤拷锟絠d
	@Column(length = 32)
	private String code; // code值
	@Column(length = 255)
	private String redirect_url; // 锟截讹拷锟斤拷锟街�
	@Column(length = 32)
	private String client_id; // 锟酵伙拷/锟斤拷锟斤拷/锟斤拷司id
	@Column(length = 32)
	private String grant_type; // 锟斤拷权锟斤拷锟斤拷
	@Column(length = 32)
	private String client_secret; // 锟酵伙拷/锟斤拷司/锟斤拷锟斤拷锟斤拷锟斤拷钥
	@Column(length = 2000)
	private String access_token; // 锟斤拷锟斤拷锟斤拷锟斤拷
	
	@Column(length = 32)
	private String expires_in; // 锟斤拷锟节硷拷锟�
	
	@Column(length = 32)
	private String createtime; // 锟斤拷锟斤拷时锟斤拷

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}



	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


}
