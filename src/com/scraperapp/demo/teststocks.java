package com.scraperapp.demo;

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
		System.out.println("No. of Rows: " + tableRows.size());
		
		String colmns = "//*[@id=\"scr-res-table\"]/div[1]/table/thead/tr/th[*]/span/span";
		List<WebElement> tableColumns = driver.findElements(By.xpath(colmns));
		System.out.println("No. of Columns: " + tableColumns.size());	
				
		for(int i = 0; i < 25; i++) {	
			
			System.out.println((i + 1) + " - " + tableRows.get(i).getText());
		}
		
		driver.close();
		
		//wait
		Thread.sleep(5000);
		
		
	}

}
