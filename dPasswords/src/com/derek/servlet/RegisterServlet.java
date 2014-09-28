package com.derek.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derek.model.Contants;
import com.derek.model.DBHelper;

public class RegisterServlet extends HttpServlet {
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
			response.getWriter().write(Contants.FAIL_STATUS + "" + " username style is not right");
			return;
		}
		
		if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PAWWORD_LENGTH) {
			response.getWriter().write(Contants.FAIL_STATUS + "" + " password style is not right");
			return;
		}		
		
		String queryPassword = DBHelper.getPasswordFromDB(username);
		if (queryPassword == null) {
			boolean result = DBHelper.insertUserIntoDB(username, password);
			if (result) {
				response.getWriter().write(Contants.SUCCESS_STATUS + "");
			} else {
				response.getWriter().write(Contants.FAIL_STATUS + "");
			}
		} else {
			response.getWriter().write(Contants.FAIL_STATUS + "");
		}
	}
}
