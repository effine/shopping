package cn.effine.todo.oauth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="yl_user")
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String    username      ;
	
	@Column(length=32)
	private String    first_name    ;
	
	@Column(length=32)
	private String    last_name     ;
	
	@Column(length=255)
	private String    email         ;
	
	@Column(length=32)
	private String     password     ;
	
	
	@Column(length=1)
	private int   is_staff       ;
	
	@Column(length=1)
	private int   is_active      ;
	
	@Column(length=1)
	private int   is_superuser   ;
	
	@Column(length=32)
	private Date  last_login     ;
	
	@Column(length=32)
	private Date  date_joined    ;
          
	
//	@Column(length=32)
//	private String uid;
//	
//	@Column(length=32)
//	private String password;
//	
//	@Column(length=32)
//	private String realName;
//	
//	@Column(length=32)
//	private int gender;
//	
//	@Column(length=32)
//	private String email;
//	
//	@Column(length=32)
//	private String tel;
//	
//	@Column(length=200)
//	private String question;
//	
//	@Column(length=200)
//	private String validateCode;
//	
//	@Column(length=200)
//	private String answer;
//	
//	@Column(length=10)
//	private int loginNum;
	
	 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getIs_staff() {
		return is_staff;
	}

	public void setIs_staff(int is_staff) {
		this.is_staff = is_staff;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public int getIs_superuser() {
		return is_superuser;
	}

	public void setIs_superuser(int is_superuser) {
		this.is_superuser = is_superuser;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	
	public Date getDate_joined() {
		return date_joined;
	}

	public void setDate_joined(Date date_joined) {
		this.date_joined = date_joined;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", first_name="
				+ first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", is_staff=" + is_staff
				+ ", is_active=" + is_active + ", is_superuser=" + is_superuser
				+ ", last_login=" + last_login + ", date_joined=" + date_joined
				+ "]";
	}

//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRealName() {
//		return realName;
//	}
//
//	public void setRealName(String realName) {
//		this.realName = realName;
//	}
//
//	public int getGender() {
//		return gender;
//	}
//
//	public void setGender(int gender) {
//		this.gender = gender;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getTel() {
//		return tel;
//	}
//
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//
//	public String getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(String question) {
//		this.question = question;
//	}
//
//	public String getValidateCode() {
//		return validateCode;
//	}
//
//	public void setValidateCode(String validateCode) {
//		this.validateCode = validateCode;
//	}
//
//	public String getAnswer() {
//		return answer;
//	}
//
//	public void setAnswer(String answer) {
//		this.answer = answer;
//	}
//
//	public int getLoginNum() {
//		return loginNum;
//	}
//
//	public void setLoginNum(int loginNum) {
//		this.loginNum = loginNum;
//	}

	
	
}
