package com.scraperapp.demo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class teststocks {
	public static void main(String[] args) throws Exception {
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tomya\\eclipse-workspace\\ScraperAppDemo\\WebContent\\WEB-INF\\lib\\chromedriver\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://finance.yahoo.com/most-active");
		
		System.out.println(driver.getTitle()); 
		
		//String xpath = "//*[@id=\"scr-res-table\"]/div[2]/table/tbody/tr[*]";
		String rows = "//*[@id=\"scr-res-table\"]/div[2]/table/tbody/tr[*]";
		List<WebElement> tableRows = driver.findElements(By.xpath(rows));
		int TotalRows = tableRows.size();
		System.out.println("No. of Rows: " + TotalRows);
		
		String colmns = "//*[@id=\"scr-res-table\"]/div[1]/table/thead/tr/th[*]/span/span";
		List<WebElement> tableColumns = driver.findElements(By.xpath(colmns));
		int TotalColumns = tableColumns.size();
		
		System.out.println("No. of Columns: " + TotalColumns);	
		
		
		//Looping through the table to get data horizontally(row wise) 
//		for(int i = 0; i < TotalRows; i++) {
//			
//			System.out.println((i + 1) + " - " + tableRows.get(i).getText());
//			
//		}	
		
		//Looping through the table to get all data vertically(in one column)
		for(int i = 0; i < TotalRows; i++) {
			//To locate columns(cells) of that specific row 
			List <WebElement> cells = tableRows.get(i).findElements(By.tagName("td"));
			
			for(int j = 0; j < TotalColumns; j++) {
				//To retrieve text from a specific cell
				String celltext = cells.get(j).getText();
				System.out.println(celltext);
			}
			System.out.println("------------------------");
		}	
					
		driver.close();
		
		//wait
		Thread.sleep(5000);
		
		
	}

}


