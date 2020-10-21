package com.sadjesty.currencyconverter.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

import com.sadjesty.currencyconverter.util.Currency;

public interface Convertor {
	public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount)
			throws IllegalArgumentException, MalformedURLException, IOException;
}
