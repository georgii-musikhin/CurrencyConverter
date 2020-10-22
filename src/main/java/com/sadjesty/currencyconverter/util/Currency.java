package com.sadjesty.currencyconverter.util;

/**
 * 
 * Created by Sadjesty 20.10.2020
 */

public enum Currency {
	BTC("BTC"), USD("USD"), RUB("RUB"), EUR("EUR");
	
	private final String symbol;
	
	private Currency(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
}
