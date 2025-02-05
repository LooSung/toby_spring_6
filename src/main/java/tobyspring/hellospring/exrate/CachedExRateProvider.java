package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
	private final ExRateProvider target;

	private BigDecimal cachedExRate;

	private LocalDateTime cachedExpiredTime;

	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) {
		if (cachedExRate == null || cachedExpiredTime.isBefore(LocalDateTime.now())) {
			cachedExRate = target.getExRate(currency);
			cachedExpiredTime = LocalDateTime.now().plusSeconds(3);

			System.out.println("Cache Updated");
		}

		return cachedExRate;
	}
}
