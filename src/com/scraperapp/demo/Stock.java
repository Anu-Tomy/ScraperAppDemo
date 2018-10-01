package com.scraperapp.demo;

public class Stock {
	
	//private int rowNumber;
	private String symbol;
	private String name;
	private double price;
	private double chang;
	private double percentChang;
    private String volum;
    private String avgVol;
	private double marketCap;
	private String peRatio;
	
	
	public Stock(String symbol, String name, double price, double chang, double percentChang, String volum,
			String avgVol, double marketCap, String peRatio, double fiftTwoWkRange) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		this.chang = chang;
		this.percentChang = percentChang;
		this.volum = volum;
		this.avgVol = avgVol;
		this.marketCap = marketCap;
		this.peRatio = peRatio;
		//this.fiftTwoWkRange = fiftTwoWkRange;
	}
	
	

	public Stock(String symbol, String name, double price, double chang, double percentChang, String volum,
			String avgVol, double marketCap, String peRatio) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		this.chang = chang;
		this.percentChang = percentChang;
		this.volum = volum;
		this.avgVol = avgVol;
		this.marketCap = marketCap;
		this.peRatio = peRatio;
	}


	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public double getChang() {
		return chang;
	}

	public void setChang(double chang) {
		this.chang = chang;
	}

	public double getPercentChang() {
		return percentChang;
	}

	public void setPercentChang(double percentChang) {
		this.percentChang = percentChang;
	}

	public String getVolum() {
		return volum;
	}

	public void setVolum(String volum) {
		this.volum = volum;
	}

	public String getAvgVol() {
		return avgVol;
	}

	public void setAvgVol(String avgVol) {
		this.avgVol = avgVol;
	}

	public double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}

	public String getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(String peRatio) {
		this.peRatio = peRatio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", price=" + price + ", chang=" + chang
				+ ", percentChang=" + percentChang + ", volum=" + volum + ", avgVol=" + avgVol + ", marketCap="
				+ marketCap + ", peRatio=" + peRatio + "]";
	}

}

