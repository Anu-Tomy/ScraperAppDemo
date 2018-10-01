package com.scraperapp.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ScrapeAppDemoStocksDbUtil {

	private static DataSource dataSource;	
	
	public ScrapeAppDemoStocksDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
		 
	
	//retrieve stocks data from database
	public static List<Stock> getStocksData() throws Exception {		
		
		List<Stock> stocks = new ArrayList<>();
				
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get connection to the database
			 myConn = dataSource.getConnection();
			 
			 String sql = "SELECT * FROM  stocksTable";
			 
			 myStmt = myConn.createStatement();
			 myRs = myStmt.executeQuery(sql);
			 
			 while(myRs.next()) {
				 String symbol = myRs.getString("symbol");
				 String name = myRs.getString("name");
				 Double price = myRs.getDouble("price");
				 Double chang = myRs.getDouble("chang");
				 Double percentChang = myRs.getDouble("percentChang");
				 String volum = myRs.getString("volum");
				 String avgVol = myRs.getString("avgVol");
				 Double marketCap = myRs.getDouble("marketCap");
				 String peRatio = myRs.getString("peRatio");
				 
				//create a new stock object
				 Stock tempStock = new Stock(symbol, name, price, chang, percentChang, volum, avgVol, marketCap, peRatio);
				 
				 //add it to the list of stocks
			      stocks.add(tempStock); 
			 }
						 
		}catch (SQLException e) {
			System.out.println("58. err");
			e.printStackTrace();
		}finally {
			try {
				
				if(myRs != null)
					myRs.close();
				if(myStmt != null)
					myStmt.close();
				if(myConn != null)
					myConn.close();		
			} catch (SQLException e ) {
				e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
					}
			}
		return stocks;	
	}
}


