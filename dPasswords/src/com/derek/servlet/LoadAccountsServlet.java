package com.derek.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derek.model.Account;
import com.derek.model.Contants;
import com.derek.model.DBHelper;

public class LoadAccountsServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userIdString = request.getParameter("user_id");
		try {
			int userId = Integer.parseInt(userIdString);
			ArrayList<Account> accounts = DBHelper.loadAccountsFromDB(userId);

			StringBuilder builder = new StringBuilder("[");
			for (int i = 0; i < accounts.size(); i++) {
				Account account = accounts.get(i);
				String jsonString = account.jsonString();
				builder.append(jsonString);
				if (i != (accounts.size() - 1))
					builder.append(",");
			}
			builder.append("]");

			String jsonString = builder.toString();
			response.getWriter().write(jsonString);

		} catch (NumberFormatException e) {
			response.getWriter().write(Contants.FAIL_STATUS + "");
		}
	}
}
