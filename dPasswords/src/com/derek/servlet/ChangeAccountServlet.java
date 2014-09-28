package com.derek.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derek.model.Contants;
import com.derek.model.DBHelper;

public class ChangeAccountServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userIdString = request.getParameter("user_id");
		String accountId = request.getParameter("account_id");
		String accountName = request.getParameter("account_name");
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");

		if (DBHelper.isExistAccount(accountId)) {
			if (DBHelper.changeAccount(accountId, accountName, userName, password)) {
				response.getWriter().write(Contants.SUCCESS_STATUS + "");
			} else {
				response.getWriter().write(Contants.FAIL_STATUS + "");
			}
		} else {
			response.getWriter().write(Contants.FAIL_STATUS + "");
		}
	}
}
