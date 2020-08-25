package com.vijay.jpaprac.jpademo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

@Entity
//@FilterDef(name="filter1",parameters=@ParamDef(name="uname",type="string"))
@Table(name="USERH")
public class User {
	/*@Filter(
			name="filter1",
			condition="username == uname"
			)*/
	
	@Id 
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name="USERID")
	private int userId;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String passWord;
	@Column(name="USERROLE")
	private String userRole;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
