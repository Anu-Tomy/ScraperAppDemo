<%@ page import="java.util.*, com.scraperapp.demo.*"%>

<!DOCTYPE html>
<html>
<head>
<title>WebScraper App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%
	//get the stocks from the request object(sent by servlet)
	List<Stock> theStocks = (List<Stock>) request.getAttribute("STOCKS_LIST");
%>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Yahoo Finance Scraper</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Symbol</th>
					<th>Name</th>
					<th>Price(Intraday)</th>
					<th>Change</th>
					<th>%Change</th>
					<th>Volume</th>
					<th>AvgVol(3 month)</th>
					<th>MarketCap</th>
					<th>PE Ratio</th>
				</tr>
				<% for (Stock tempStock : theStocks) { %>
				<tr>
					<td><%= tempStock.getSymbol() %></td>
					<td><%= tempStock.getName() %></td>
					<td><%= tempStock.getPrice() %></td>
					<td><%= tempStock.getChang() %></td>
					<td><%= tempStock.getPercentChang() %></td>
					<td><%= tempStock.getVolum() %></td>
					<td><%= tempStock.getAvgVol() %></td>
					<td><%= tempStock.getMarketCap() %></td>
					<td><%= tempStock.getPeRatio() %></td>

				</tr>
				<% } %>
			</table>
		</div>
	</div>
</body>
</html>