package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;

class PaymentServiceTest {
	Clock clock;

	@BeforeEach
	void beforeEach() {
		this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}

	@Test
	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	void convertedAmount() throws IOException {
		testAmount(valueOf(500), valueOf(5_000), clock);
		testAmount(valueOf(1000), valueOf(10_000), clock);
		testAmount(valueOf(3000), valueOf(30_000), clock);

		// 원화환산금액 유효기간 계산
		// Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

	@NonNull
	private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderSub(exRate), clock);

		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		// 환율정보 가져온다
		Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

		// 원화환산금액 계산
		Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
	}

	@Test
	void validUntil() throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderSub(valueOf(1_000)), clock);

		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		//valid untill이 prepare() 30분 뒤로 설정됫는가??
		LocalDateTime now = LocalDateTime.now(this.clock);
		LocalDateTime exceptedValidUntil = now.plusMinutes(30);

		Assertions.assertThat(payment.getValidUntil().equals(exceptedValidUntil));
	}
}