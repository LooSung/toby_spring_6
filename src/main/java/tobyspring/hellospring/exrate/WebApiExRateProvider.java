package tobyspring.hellospring.exrate;

import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
	ApiTempalte apiTempalte = new ApiTempalte();

	@Override public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return apiTempalte.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
	}
}
