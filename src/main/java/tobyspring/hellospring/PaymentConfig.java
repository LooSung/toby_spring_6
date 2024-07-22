package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.SimpleExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cahcedExRateProvider(), clock());
	}

	// Decorator Pattern을 이용한 Cahce 기능 추가
	@Bean
	public ExRateProvider cahcedExRateProvider() {
		return new CachedExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new SimpleExRateProvider();
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
