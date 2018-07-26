package com.scraperapp.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrapeAppDemoDbUtil {

	public static void main(String[] args) throws Exception {
		
		dropOldTable();
		createStocksTable();
		scrapeData();
		/*
		 * getCookies
		 * connectDb();
		 * dropOldTable();
		 * createStocksTable();
		 * scrapeData();
		 * insertNewStocksData();	
		 * disconnectDb();
		 * 
		 * 
		 */
		
	}	
	
	
	public static Connection connectDb() throws Exception {
		String url = "jdbc:mysql://localhost:3306/yahoo_finance_scraper?useSSL=false";
	    String username = "yahoo";
	    String password = "yahoo";
	    Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    System.out.println("Database connection successful!");
	    return conn;
	  }
		
	public static void dropOldTable() throws SQLException {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		try {
			//get connection to the database
			 conn = connectDb();
						 
			//TRUNCATE Old TABLE data
			 String mySqlQuery = "DROP TABLE stocksTable";
			 pstmt = conn.prepareStatement(mySqlQuery);
			 pstmt.executeUpdate(); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pstmt.close();
		}
		System.out.println("Drop old table");
	}

	public static void createStocksTable() throws Exception{
		
	    PreparedStatement pstmt = null;
	    Connection conn = null;
	    try {
	      StringBuffer sql = new StringBuffer("CREATE TABLE stocksTable(");
	      //sql.append("rowNumber			INTEGER, ");
	      sql.append("symbol			VARCHAR(6), ");
	      sql.append("name				VARCHAR(50), ");
	      sql.append("price				DOUBLE PRECISION, ");
	      sql.append("chang					DECIMAL(5,2), ");
	      sql.append("percentChang			DECIMAL(5,2), ");
	      sql.append("volum				VARCHAR(12), ");    
	      sql.append("avgVol			VARCHAR(10), ");
	      sql.append("marketCap			DOUBLE PRECISION, ");
	      sql.append("peRatio			VARCHAR(10)) "); 
	      //sql.append("fiftTwoWkRange		DOUBLE PRECISION)");

	      
	      conn = connectDb();
	      pstmt = conn.prepareStatement(sql.toString()); 
	      pstmt.executeUpdate();
	      
	      System.out.println("The Stocks table is created");
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	  }
	
	public static void scrapeData() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tomya\\eclipse-workspace\\ScraperAppDemo\\WebContent\\WEB-INF\\lib\\chromedriver\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://finance.yahoo.com/most-active");
		
		System.out.println(driver.getTitle()); 
		
		String rows = "//*[@id=\"scr-res-table\"]/div[2]/table/tbody/tr[*]";
		List<WebElement> tableRows = driver.findElements(By.xpath(rows));
		int numRows = tableRows.size();
		System.out.println("No. of Rows: " + numRows);
		
		
		String colmns= "//*[@id=\"scr-res-table\"]/div[1]/table/thead/tr/th[*]";
		List<WebElement> tableColumns = driver.findElements(By.xpath(colmns));
		int numColumns = tableColumns.size();
		
		System.out.println("No. of Columns: " + numColumns);	
		
		
				
		String[] cell = new String[numColumns];
		
		String cellData = "";

		//Looping through the table to get data
		for(int i = 1; i <= numRows; i++) {
			for(int j = 1; j <=numColumns; j++) {
				cellData = driver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]")).getText();
				
				if( j == 5 || j == 6 || j == 7  || j == 8) {
					
					String pattern = "[%,MB](?!\\d+.\\d+)";
					cellData = cellData.replaceAll(pattern, "");
					cell[j-1] = cellData;
					System.out.println(cell[j-1]);
				}else {
					cell[j-1] = cellData;
					System.out.println(cell[j-1]);
					}			
			}
			//System.out.print("Inserting data to DB\n");
			try {
				
				insertNewStocksData(cell);
								
			}catch (Exception e){
				e.printStackTrace();
			}
			
		} 
					
		driver.close();
		
		//wait
		Thread.sleep(5000);
			
	}
	

	
	private static void insertNewStocksData(String[] cell) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//get connection to the database
			 conn = connectDb();
			 
			//Inserts stocks data
			 
			 String symbol = "";
			 String name = "";
			 Double price = 0.00;
			 Double chang = 0.00;
			 Double percentChang = 0.00;
			 String volum = "";
			 String avgVol = "";
			 Double marketCap = 0.00;
			 String peRatio = "";
		//	 Double fiftTwoWkRange = "";
			 
			 Stock stock = new Stock(symbol, name, price, chang, percentChang, volum, avgVol, marketCap, peRatio);
			
			 for(int i = 0; i < cell.length; i++){
				 
				 switch (i) {
				 
				 	case 0: symbol = cell[0];
				 			stock.setSymbol(symbol);
				 			break;
				 			
				 	case 1: name = cell[1];
				 			stock.setName(name);
				 			break;
				 			
				 	case 2: price = Double.parseDouble(cell[2].replaceAll("[,]",""));
		 					stock.setPrice(price);
		 					break;
		 					
				 	case 3: chang = Double.parseDouble(cell[3]);
				 			stock.setChang(chang);
				 			break;
				 			
				 	case 4: percentChang = Double.parseDouble(cell[4]);
		 					stock.setPercentChang(percentChang);
		 					break;	
		 					
				 	case 5: volum = cell[5];
				 			stock.setVolum(volum);
				 			break;
				 			
				 	case 6: avgVol = cell[6];
				 			stock.setAvgVol(avgVol);
				 			break;
				 			
				 	case 7: marketCap = Double.parseDouble(cell[7]);
				 			stock.setMarketCap(marketCap);
				 			break;
				 			
				 	case 8: peRatio = cell[8];
				 			stock.setPeRatio(peRatio);
				 			break;		 	
							 			
				 			
				 }
				 
			 }
			 
			String sqlQuery = "INSERT INTO stocksTable(symbol, name, price, chang, percentChang, volum, avgVol, marketCap, peRatio) VALUES(?, ? ,?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sqlQuery);
			
			pstmt.setString(1, symbol);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);
			pstmt.setDouble(4, chang);
			pstmt.setDouble(5, percentChang);
			pstmt.setString(6, volum);
			pstmt.setString(7, avgVol);
			pstmt.setDouble(8, marketCap);
			pstmt.setString(9, peRatio);
			
			pstmt.executeUpdate();
						
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			pstmt.close();
		}
		System.out.println("The Stocks table data is added to the database");
	}

}
