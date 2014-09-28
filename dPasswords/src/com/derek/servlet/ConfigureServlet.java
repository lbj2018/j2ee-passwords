package com.derek.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.derek.model.DBDao;
import com.derek.model.DBHelper;

public class ConfigureServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		DBHelper.createDBAndTables();
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
