package com.sadjesty.currencyconverter.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectionToDB {
	private final String PASSWORD;

	public ConnectionToDB(String password) {
		this.PASSWORD = password;
	}

	public void insertTransactionDataIntoTable(String fromCurrency, String toCurrency, String amount, String result)
			throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String date = getDateOfTransaction();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO btcwalletbd.transctions (fromCurrency, toCurrency, amount, result, date)\r\n"
						+ "VALUES (?, ?, ?, ?, ?);");
		preparedStatement.setString(1, fromCurrency);
		preparedStatement.setString(2, toCurrency);
		preparedStatement.setString(3, amount);
		preparedStatement.setString(4, result);
		preparedStatement.setString(5, date);
		preparedStatement.addBatch();
		preparedStatement.executeBatch();
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/btcwalletbd?useUnicode=true&serverTimezone=UTC", "root", PASSWORD);
		return connection;
	}

	public static String getDateOfTransaction() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);

		return date;
	}
}
