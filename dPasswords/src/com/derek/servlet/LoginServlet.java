package com.derek.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Enumeration;

import com.derek.model.DbDao;

public class LoginServlet extends HttpServlet {
	public static final int SUCCESS_LOGIN = 1;
	public static final int FAIL_LOGIN = 0;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errMsg = null;

		try {			
			DbDao dd = DbDao.get();

			ResultSet rs = dd.query("select password from drk_user.t_user "
					+ "where username = ?", username);

			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					response.getWriter().write(SUCCESS_LOGIN + "");
				} else {
					errMsg = "Your password is not right.";
				}
			} else {
				errMsg = "Your username is not exist. Please register firstly.";
			}
		} catch (Exception e) {
			errMsg = "Some exceptions happened.";
		} finally {
			if (errMsg != null) {
				response.getWriter().write(FAIL_LOGIN + "");
			}
		}
	}
}
