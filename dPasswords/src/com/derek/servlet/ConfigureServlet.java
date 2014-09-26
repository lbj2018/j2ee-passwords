package com.derek.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.derek.model.DbDao;

public class ConfigureServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		DbDao dd = DbDao.get();
		try {
			 String sql =
			 "CREATE SCHEMA IF NOT EXISTS drk_user DEFAULT CHARACTER SET latin1";

			 dd.create(sql);

			sql = "CREATE TABLE IF NOT EXISTS drk_user.t_user  ( "
					+ "userid INT NOT NULL AUTO_INCREMENT,"
					+ "username TEXT NOT NULL," + "password TEXT NOT NULL,"
					+ "email TEXT NOT NULL," + "userinfo TEXT NOT NULL,"
					+ "nickname TEXT NOT NULL," + "datecreated DATE NOT NULL,"
					+ "PRIMARY KEY (userid)" + ")";

			dd.create(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
