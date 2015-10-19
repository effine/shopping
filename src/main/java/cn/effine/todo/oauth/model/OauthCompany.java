package cn.effine.todo.oauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="yl_oauth2_company")
public class OauthCompany {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String client_id;
	
	@Column(length=32)
	private String client_secret;
	
	@Column(length=255)
	private String company_name;
	
	@Column(length=255)
	private String company_url;
	
	@Column(length=2000)
	private String company_detail;
	
	@Column(length=32)
	private String company_license_number;
	
	@Column(length=200)
	private String email;
	
	@Column(length=32)
	private String contact;
	
	@Column(length=32)
	private String phone;
	
	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String clientId) {
		client_id = clientId;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String clientSecret) {
		client_secret = clientSecret;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String companyName) {
		company_name = companyName;
	}

	public String getCompany_url() {
		return company_url;
	}

	public void setCompany_url(String companyUrl) {
		company_url = companyUrl;
	}

	public String getCompany_detail() {
		return company_detail;
	}

	public void setCompany_detail(String companyDetail) {
		company_detail = companyDetail;
	}

	public String getCompany_license_number() {
		return company_license_number;
	}

	public void setCompany_license_number(String companyLicenseNumber) {
		company_license_number = companyLicenseNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	
	
}
