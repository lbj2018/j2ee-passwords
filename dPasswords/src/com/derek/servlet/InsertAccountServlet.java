package com.derek.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derek.model.Contants;
import com.derek.model.DBHelper;

public class InsertAccountServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userIdString = request.getParameter("user_id");
		String accountId = request.getParameter("account_id");
		String accountName = request.getParameter("account_name");
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");
	
		Date dateCreated = new Date();
		String dateCreatedString = DBHelper.getDateStringFromDate(dateCreated);
		if (DBHelper.isExistAccount(accountId)) {
			response.getWriter().write(Contants.FAIL_STATUS + ""); 
			return;
		}
		
		try {
			int userId = Integer.parseInt(userIdString);
			if (DBHelper.isExistUser(userId)) {
				if (DBHelper.insertAccountIntoDB(userId, accountId, accountName, userName, password, dateCreatedString)) {
					response.getWriter().write(dateCreatedString); 
				} else {
					response.getWriter().write(Contants.FAIL_STATUS + ""); 
				}
			} else {
				response.getWriter().write(Contants.FAIL_STATUS + ""); 
			}
		} catch (NumberFormatException e) {
			response.getWriter().write(Contants.FAIL_STATUS + ""); 
		}
	}
}
