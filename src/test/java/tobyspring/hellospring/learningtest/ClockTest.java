package tobyspring.hellospring.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
	@Test
	void clock() {
		Clock clock = Clock.systemDefaultZone();

		LocalDateTime dateTime1 = LocalDateTime.now(clock);
		LocalDateTime dateTime2 = LocalDateTime.now(clock);

		Assertions.assertThat(dateTime2).isAfter(dateTime1);
	}

	// Clock을 Test에서 사용할 때 내가 원하는 시간을 지어해서 현재 시간을 가져오개 할 수 있는가?
	@Test
	void fixedClock() {
		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

		LocalDateTime dateTime1 = LocalDateTime.now(clock);
		LocalDateTime dateTime2 = LocalDateTime.now(clock);
		LocalDateTime dateTime3 = LocalDateTime.now(clock).plusHours(1);

		Assertions.assertThat(dateTime1).isAfter(dateTime2);
		Assertions.assertThat(dateTime3).isAfter(dateTime1.plusHours(1));
	}
}
