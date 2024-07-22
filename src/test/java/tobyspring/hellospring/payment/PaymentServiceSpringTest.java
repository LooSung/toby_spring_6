package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.ObjectFactory;
import tobyspring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

	@Autowired
	PaymentService	paymentService;

	@Autowired
	ExRateProviderSub exRateProviderSub;

	@Test
	void convertedAmount() throws IOException {
		// exRate : 1000
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
		// 환율정보 가져온다
		Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
		// 원화환산금액 계산
		Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

		// exRate : 500
		exRateProviderSub.setExRate(valueOf(500));
		Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);
		// 환율정보 가져온다
		Assertions.assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
		// 원화환산금액 계산
		Assertions.assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));

		// 원화환산금액 유효기간 계산
		// Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

	@NonNull
	private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderSub(exRate));


	}
}