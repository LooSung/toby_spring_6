package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;

public class ExRateProviderSub implements ExRateProvider {
	private BigDecimal exRate;

	public BigDecimal getExRate() {
		return exRate;
	}

	public void setExRate(BigDecimal exRate) {
		this.exRate = exRate;
	}

	public ExRateProviderSub(BigDecimal exRate) {
		this.exRate = exRate;
	}

	@Override
	public BigDecimal getExRate(String currencyCode) {
		return exRate;
	}
}
