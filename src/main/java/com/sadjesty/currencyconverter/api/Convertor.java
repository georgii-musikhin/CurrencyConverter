package com.sadjesty.currencyconverter.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

import com.sadjesty.currencyconverter.util.Currency;

public interface Convertor {
	public BigDecimal convert(String fromCurrency, String toCurrency, String amount)
			throws IllegalArgumentException, MalformedURLException, IOException;
}
