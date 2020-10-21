package com.sadjesty.currencyconverter.util;

/**
 * 
 * Created by Sadjesty 20.10.2020
 */

public enum Currency {
	USD("USD"), BTC("BTC");
	
	private final String symbol;
	
	private Currency(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
}
