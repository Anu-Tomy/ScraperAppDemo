
package com.scraperapp.demo;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ScraperAppDemoServlet
 */
@WebServlet("/StocksControllerServlet")
public class StocksControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/yahoo_finance_scraper")
	private DataSource dataSource;	
	
	// initialize StocksDataDbUtil
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//Create StocksDataDbUtil and pass conn pool/datasource
		try {
			new StocksDataDbUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the stocks data
		try {
			getStocks(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void getStocks(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//get stocks data from StocksDataDbUtil
		List<Stock> stocks = StocksDataDbUtil.getStocksData();
		
		//add stocks to the request
		request.setAttribute("STOCKS_LIST", stocks);
		
		//send to JSP page(view)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-stocks-demo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
