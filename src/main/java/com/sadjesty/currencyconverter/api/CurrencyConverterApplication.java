package com.sadjesty.currencyconverter.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import com.sadjesty.currencyconverter.jbdc.ConnectionToDB;

public class CurrencyConverterApplication {
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter your API convert key: ");
			String key = scanner.nextLine();
			CurrencyConverter cc = new CurrencyConverter(key);
			System.out.println("Enter your password from data base: ");
			String password = scanner.nextLine();
			ConnectionToDB dataBase = new ConnectionToDB(password);
			
			System.out.println("Enter the currency you want to transfer from (USD, BTC, EUR, RUB): ");
			String fromCurrency = scanner.nextLine();
			System.out.println("Enter the currency you want to convert (USD, BTC, EUR, RUB): ");
			String toCurrency = scanner.nextLine();
			System.out.println("Enter the amount of money you want to convert (USD, BTC, EUR, RUB): ");
			String amount = scanner.nextLine();
			
			try{
				BigDecimal result = cc.convert(fromCurrency, toCurrency, amount);
				System.out.println(amount + " " + fromCurrency + " = " + result + " " + toCurrency);
				dataBase.insertTransactionDataIntoTable(fromCurrency, toCurrency, amount, result.toString());
				System.out.println("Convertion successfuly insert in DataBase");
			} catch (IOException e) {
				System.out.println("Invalid arguments. Try again");
			} catch (SQLException e) {
				System.out.println("SQL exception");
			} catch (ClassNotFoundException e1) {
				System.out.println("SQL exception");
			}
		
		}
	}
}
