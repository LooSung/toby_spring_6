package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
	private Long orderId;
	private String currency;
	private BigDecimal foreignCurrencyAmount;
	private BigDecimal exRate;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
		this.orderId = orderId;
		this.currency = currency;
		this.foreignCurrencyAmount = foreignCurrencyAmount;
		this.exRate = exRate;
		this.convertedAmount = convertedAmount;
		this.validUntil = validUntil;
	}

	// Factory Pattern (도메인 오브젝트 생성) : 테스트를 빠르게 하기 위해 만들기
	public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, LocalDateTime now) {
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = now.plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}

	// Factory Pattern (도메인 오브젝트 생성) : 테스트를 빠르게 하기 위해 만들기
	public boolean isValid(Clock clock) {
		return LocalDateTime.now(clock).isBefore(validUntil);
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getForeignCurrencyAmount() {
		return foreignCurrencyAmount;
	}

	public BigDecimal getExRate() {
		return exRate;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	@Override public String toString() {
		return "Payment{" + "orderId=" + orderId + ", currency='" + currency + '\'' + ", foreignCurrencyAmount=" + foreignCurrencyAmount + ", exRate=" + exRate + ", convertedAmount=" + convertedAmount + ", validUntil=" + validUntil + '}';
	}
}
