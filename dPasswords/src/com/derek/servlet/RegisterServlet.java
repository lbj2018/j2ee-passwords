package com.derek.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derek.model.DbDao;

public class RegisterServlet extends HttpServlet {
	public static final int SUCCESS_REGISTER = 1;
	public static final int FAIL_REGISTER = 0;
	public static final int MIN_USERNAME_LENGTH = 6;
	public static final int MAX_USERNAME_LENGTH = 45;
	public static final int MIN_PASSWORD_LENGTH = 6;
	public static final int MAX_PAWWORD_LENGTH = 255;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		
		if (username == null || username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
			response.getWriter().write(FAIL_REGISTER + "" + " username style is not right");
			return;
		}
		
		if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PAWWORD_LENGTH) {
			response.getWriter().write(FAIL_REGISTER + "" + " password style is not right");
			return;
		}		
		
		DbDao dd = DbDao.get();
		
		try {
			ResultSet rs = dd.query("select password from drk_user.t_user "
					+ "where username = ?", username);
			if (!rs.next()) { // not exist the same username
				Date date = new Date(); 
				dd.insert("insert into drk_user.t_user(username, password, email, userinfo, nickname, datecreated) values(?, ?, ?, ?, ?, ?)", 
						username, password, "", "", "", date);
				response.getWriter().write(SUCCESS_REGISTER + "");
			} else {
				response.getWriter().write(FAIL_REGISTER + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			response.getWriter().write(FAIL_REGISTER + "" + "error: " + e.getLocalizedMessage());
		}
	}
}
