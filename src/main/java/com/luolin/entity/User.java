package com.luolin.entity;

import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;
import java.util.Date;

public class User extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	@Length(max = 100)
	private String userName;
	/**
	 *
	 */
	@Length(max = 100)
	private String name;
	/**
	 *
	 */
	@Length(max = 100)
	private String password;
	/**
	 *
	 */
	private Date loginTime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
