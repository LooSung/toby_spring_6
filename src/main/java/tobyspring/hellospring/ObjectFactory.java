package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
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
		return new SimpleExRateProvider();
	}
}
