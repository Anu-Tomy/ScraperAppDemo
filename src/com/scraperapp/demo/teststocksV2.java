package com.scraperapp.demo;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class teststocksV2 {
	public static void main(String[] args) throws Exception {
		
		
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
			System.out.println();			
		} 
					
		driver.close();
		
		//wait
		Thread.sleep(5000);
			
	}

}


