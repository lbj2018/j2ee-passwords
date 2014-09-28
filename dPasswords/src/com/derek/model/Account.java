package com.derek.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
	private int userId;
	private String accountId;
	private String accountName;
	private String userName;
	private String password;
	private String dateCreated;
	
	public String jsonString() {
		String jsonString = "{" 
						     + "\"user_id\":" + userId + ","
						     + "\"account_id\":" + "\"" + accountId + "\"" + ","
						     + "\"account_name\":" + "\"" + accountName + "\"" + ","
						     + "\"user_name\":" + "\"" + userName + "\"" + ","
						     + "\"password\":" + "\"" + password + "\"" + ","
						     + "\"date_created\":" + "\"" + dateCreated + "\""
						     + "}";
		
		return jsonString;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
