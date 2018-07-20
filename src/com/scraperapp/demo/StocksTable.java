package com.scraperapp.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StocksTable {
	public static Connection getConnection() throws Exception {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/yahoo_finance_scraper?useSSL=false";
	    String username = "yahoo";
	    String password = "yahoo";
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }

	  public static void main(String[] args) throws Exception{
	    PreparedStatement pstmt = null;
	    Connection conn = null;
	    try {
	      StringBuffer sql = new StringBuffer("CREATE TABLE stocks(");
	      sql.append("rowNumber			INTEGER, ");
	      sql.append("symbol			VARCHAR(6), ");
	      sql.append("name				VARCHAR(25), ");
	      sql.append("price				DOUBLE PRECISION, ");
	      sql.append("chang				DECIMAL(3,2), ");
	      sql.append("percentChang			DECIMAL(3,3), ");
	      sql.append("volum				VARCHAR(12), ");    
	      sql.append("avgVol			VARCHAR(10), ");
	      sql.append("marketCap			DOUBLE PRECISION, ");
	      sql.append("peRatio			VARCHAR(10), "); 
	      sql.append("fiftTwoWkRange		DOUBLE PRECISION)");

	      
	      conn = getConnection();
	      pstmt = conn.prepareStatement(sql.toString()); 
	      pstmt.executeUpdate();
	      
	      System.out.println("The Stocks table is created");
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      conn.close();
	    }
	  }
}
