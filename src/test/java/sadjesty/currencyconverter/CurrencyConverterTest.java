package sadjesty.currencyconverter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.sadjesty.currencyconverter.api.CurrencyConverter;
import com.sadjesty.currencyconverter.util.Currency;

class CurrencyConverterTest {
	
	private static final Currency U_S_DOLLARS = Currency.USD;
	private static final Currency BIT_COIN = Currency.BTC;
	private static final MathContext TEST_DELTA = new MathContext(1, RoundingMode.HALF_EVEN);
	private static final CurrencyConverter convertor = new CurrencyConverter("fa40569c891e1936689e");

	@Test
	void USDtoBTCSuccess() throws MalformedURLException, IOException {
		BigDecimal expected = new BigDecimal(0.00008);
		BigDecimal actual = convertor.calculateRates(U_S_DOLLARS, BIT_COIN);
		assertEquals(expected.round(TEST_DELTA), actual.round(TEST_DELTA));
	}
	
	@Test
	void BTCtoUSDSuccess() throws MalformedURLException, IOException {
		BigDecimal expected = new BigDecimal(11889);
		BigDecimal actual = convertor.calculateRates(BIT_COIN, U_S_DOLLARS);
		assertEquals(expected.round(TEST_DELTA), actual.round(TEST_DELTA));
	}
	
	@Test
	void rateSuccess() throws MalformedURLException, IOException {
		System.out.println("rate");
		BigDecimal bitcoinToDollarRate = convertor.calculateRates(BIT_COIN, U_S_DOLLARS);
		BigDecimal dollarToBitcoinRate = convertor.calculateRates(U_S_DOLLARS, BIT_COIN);
		System.out.println("Bitcion to dollar rait is said to be " + bitcoinToDollarRate);
		System.out.println("Dollar to bitcoin rait is said to be " + dollarToBitcoinRate);
		BigDecimal expected = new BigDecimal(1.00);
		BigDecimal actual = bitcoinToDollarRate.multiply(dollarToBitcoinRate);
		assertEquals(expected, actual.round(TEST_DELTA));
	}
}
