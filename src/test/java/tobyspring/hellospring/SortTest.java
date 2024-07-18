package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
	Sort sort;

	// Give - 준비
	@BeforeEach
	void beforeEach() {
		sort = new Sort();
	}

	@Test
	void sort() {
		// When - 실행
		List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

		// Then - 검증
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
	}

	@Test
	void sort3Items() {
		// When - 실행
		List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

		// Then - 검증
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
	}

	@Test
	void sortAlreadySorted() {
		// When - 실행
		List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

		// Then - 검증
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
	}
}
