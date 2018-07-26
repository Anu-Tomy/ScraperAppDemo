package com.scraperapp.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ScraperAppDemoServlet
 */
@WebServlet("/ScraperAppDemoServlet")
public class ScraperAppDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/yahoo_finance_scraper")
	private DataSource dataSource;	
	
    public ScraperAppDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection myConn = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yahoo_finance_scraper", "yahoo" , "yahoo");
			//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yahoo_finance_scraper?useSSL=false", "yahoo" , "yahoo");
			
			System.out.println("Database connection successful!");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
		}		
		
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
