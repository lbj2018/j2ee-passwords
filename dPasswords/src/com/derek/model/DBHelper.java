package com.derek.model;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper {

	public static Date getDateFromDateString(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDateStringFromDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = format.format(date);
		
		return dateString;
	}
	
	public static boolean createDBAndTables() {
		boolean result = false;

		DBDao dd = DBDao.get();
		try {
			String sql = "CREATE SCHEMA IF NOT EXISTS drk_user DEFAULT CHARACTER SET latin1";
			dd.create(sql);

			sql = "CREATE TABLE IF NOT EXISTS drk_user.t_user  ( "
					+ "user_id INT NOT NULL AUTO_INCREMENT,"
					+ "user_name TEXT NOT NULL," + "password TEXT NOT NULL,"
					+ "email TEXT NOT NULL," + "user_info TEXT NOT NULL,"
					+ "nick_name TEXT NOT NULL," + "date_created TEXT NOT NULL,"
					+ "user_role INT DEFAULT 1,"
					+ "PRIMARY KEY (user_id)" + ")";
			dd.create(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS drk_user.t_account  ( "
					+ "account_id TEXT NOT NULL,"
					+ "user_id INT,"
					+ "account_name TEXT NOT NULL,"
					+ "user_name TEXT NOT NULL," 
					+ "password TEXT NOT NULL,"
					+ "date_created TEXT NOT NULL,"
					+  "FOREIGN KEY (user_id) REFERENCES drk_user.t_user(user_id)"
					+ ")";
			dd.create(sql);
			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean isExistUser(int userId) {
		boolean result = false;

		DBDao dd = DBDao.get();

		try {
			ResultSet rs = dd.query("select user_name from drk_user.t_user "
					+ "where user_id = ?", userId);

			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean isExistAccount(String accountId) {
		boolean result = false;

		DBDao dd = DBDao.get();

		try {
			ResultSet rs = dd.query("select account_name from drk_user.t_account "
					+ "where account_id = ?", accountId);

			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getPasswordFromDB(String username) {
		String password = null;

		DBDao dd = DBDao.get();

		try {
			ResultSet rs = dd.query("select password from drk_user.t_user "
					+ "where user_name = ?", username);

			if (rs.next()) {
				password = rs.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return password;
	}
	
	public static User getUserFromDB(String username) {
		User user = null;
		
		DBDao dd = DBDao.get();

		try {
			ResultSet rs = dd.query("select * from drk_user.t_user "
					+ "where user_name = ?", username);

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setNickName(rs.getString("nick_name"));
				user.setUserInfo(rs.getString("user_info"));
				user.setUserRole(rs.getInt("user_role"));
				user.setDateCreated(rs.getString("date_created"));
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public static boolean insertUserIntoDB(String username, String password) {
		boolean result = false;

		DBDao dd = DBDao.get();

		Date date = new Date();
		String dateString = getDateStringFromDate(date);
		try {
			result = dd
					.insert("insert into drk_user.t_user(user_name, password, email, user_info, nick_name, date_created) values(?, ?, ?, ?, ?, ?)",
							username, password, "", "", "", dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean insertAccountIntoDB(int userId, String accountId, String accountName, String username, String password, String dateCreatedString) {
		boolean result = false;

		DBDao dd = DBDao.get();

		try {
			result = dd
					.insert("insert into drk_user.t_account(account_id, user_id, account_name, user_name, password, date_created) values(?, ?, ?, ?, ?, ?)",
							accountId, userId, accountName, username, password, dateCreatedString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static ArrayList<Account> loadAccountsFromDB(int userId) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		DBDao dd = DBDao.get();

		try {
			ResultSet rs = dd.query("select * from drk_user.t_account "
					+ "where user_id = ?", userId);

			while (rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getString("account_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setUserName(rs.getString("user_name"));
				account.setPassword(rs.getString("password"));  
				account.setDateCreated(rs.getString("date_created")); 
				account.setUserId(rs.getInt("user_id")); 
				
				accounts.add(account); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}
	
	public static boolean deleteAccount(String accountId) {
		boolean result = false;

		DBDao dd = DBDao.get();
		try {
			dd.update("delete from drk_user.t_account where account_id = ?",
							accountId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean changeAccount(String accountId, String accountName, String userName, String password) {
		boolean result = false;

		DBDao dd = DBDao.get();
		try {
			dd.modify("update drk_user.t_account set account_name = ?, user_name = ?, password = ? where account_id = ?",
							accountName, userName, password, accountId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
