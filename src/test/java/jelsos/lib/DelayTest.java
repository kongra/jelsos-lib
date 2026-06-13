package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DelayTest {

  static final String TEST_VALUE = "Hello, World!";

  AtomicInteger supplierCallsCount = new AtomicInteger(0);

  Delay<String> delay = Delay.of(() -> {
      supplierCallsCount.incrementAndGet();
      return TEST_VALUE;
    });

  Delay<String> delayThrowing = Delay.of(() -> {
    throw new RuntimeException("Test exception");
  });

  @BeforeEach
  void setUp() {
    supplierCallsCount = new AtomicInteger(0);
    delay = Delay.of(() -> {
      supplierCallsCount.incrementAndGet();
      return TEST_VALUE;
    });

    delayThrowing = Delay.of(() -> {
      throw new RuntimeException("Test exception");
    });
  }

  @Test
  void testGet() {
    final var d = O.nn(this.delay);
    assertThat(d.isRealized()).isFalse();
    final var s1 = d.deref();
    assertThat(s1).isEqualTo(TEST_VALUE);
    assertThat(d.isRealized()).isTrue();

    final var s2 = d.deref();
    assertThat(s2).isEqualTo(TEST_VALUE);

    assertThat(supplierCallsCount.get()).isEqualTo(1);
  }

  @Test
   @SuppressWarnings({"null"})
  void testGetFailing() {
    assertThat(delayThrowing.isRealized()).isFalse();

    assertThatThrownBy(() -> delayThrowing.deref())
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Test exception");

    assertThat(delayThrowing.isRealized()).isTrue();
    assertThatThrownBy(() -> delayThrowing.deref())
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Test exception");
  }
}
