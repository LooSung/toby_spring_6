package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.exrate.SimpleExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderSub;
import tobyspring.hellospring.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cahcedExRateProvider());
	}

	// Decorator Pattern을 이용한 Cahce 기능 추가
	@Bean
	public ExRateProvider cahcedExRateProvider() {
		return new CachedExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new ExRateProviderSub(BigDecimal.valueOf(1_000));
	}
}
