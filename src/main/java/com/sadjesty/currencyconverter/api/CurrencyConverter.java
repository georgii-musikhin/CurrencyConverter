package com.sadjesty.currencyconverter.api;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * @author sadjesty
 * 20.10.2020
 */

import com.sadjesty.currencyconverter.util.Currency;

public class CurrencyConverter implements Convertor {
	
	private final String API_KEY;
	private final String USER_AGENT_ID = "Java/" + System.getProperty("java.version");
	
	public CurrencyConverter(String key) {
		API_KEY = key;
	}

	/**
	 * @param fromCurrency currency of original amount
	 * @param toCurrency currency of result
	 * @param amount amount of money in fromCurrency
	 * @return amount of money in toCurrency
	 */
	public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount)
			throws IllegalArgumentException, MalformedURLException, IOException  {
		BigDecimal result;
	
		if(fromCurrency == null || toCurrency == null) {
			throw new IllegalArgumentException("Please, input both currencies");
		} else if (amount.equals(new BigDecimal(0))) {
			result = new BigDecimal(0);
		} else {
			result = amount.multiply(calculateRates(fromCurrency, toCurrency));
		}
		
		return result.round(new MathContext(4, RoundingMode.HALF_EVEN));
	}

	public BigDecimal calculateRates(Currency fromCurrency, Currency toCurrency) throws MalformedURLException, IOException {
		String queryPath = "https://free.currconv.com/api/v7/convert?q="
	                     + fromCurrency + "_"
	                     + toCurrency
	                     + "&compact=ultra&apiKey=" + API_KEY;
		URL queryURL = new URL(queryPath);
		HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT_ID);
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			InputStream stream = (InputStream) connection.getContent();
			try(Scanner scanner = new Scanner(stream)) {
				String quote = scanner.nextLine();
				String number = quote.substring(quote.indexOf(':') + 1, quote.indexOf('}'));
				return new BigDecimal(number);
			}
		} else {
			String exceptionMessage = "Query " + queryPath + " returned status " + responseCode;
			throw new RuntimeException(exceptionMessage);
		}
	}
	
	public static void main(String[] args) throws IllegalArgumentException, MalformedURLException, IOException {
		CurrencyConverter convertor = new CurrencyConverter("fa40569c891e1936689e");
		System.out.println(convertor.convert(Currency.BTC, Currency.USD, new BigDecimal(0.003)));
	}

}
