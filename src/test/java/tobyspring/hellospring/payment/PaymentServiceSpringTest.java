package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

	@Autowired PaymentService paymentService;
	@Autowired Clock clock;

	@Test
	void convertedAmount() {
		// exRate : 1000
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
		// 환율정보 가져온다
		Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
		// 원화환산금액 계산
		Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
	}

	@Test
	void validUntil() {
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		//valid untill이 prepare() 30분 뒤로 설정됫는가??
		LocalDateTime now = LocalDateTime.now(this.clock);
		LocalDateTime exceptedValidUntil = now.plusMinutes(30);

		Assertions.assertThat(payment.getValidUntil().equals(exceptedValidUntil));
	}
}