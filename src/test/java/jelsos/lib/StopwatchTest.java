package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StopwatchTest {

  Stopwatch timer = Stopwatch.start();

  @BeforeEach
  void setUp() {
    timer = Stopwatch.start();
  }

  // @SuppressWarnings("null")
  @Test
  void testElapsedNanosecs() {
    var t1 = timer.elapsedNanosecs();
    var t2 = timer.elapsedNanosecs();

    assertThat(t1).isNotNegative();
    assertThat(t2).isNotNegative().isGreaterThan(t1);
  }

  // @SuppressWarnings("null")
  @Test
  void testElapsedMsecs() {
    var msecs = timer.elapsedMsecs();
    var nanos = timer.elapsedNanosecs();

    assertThat(msecs).isNotNegative();
    assertThat(nanos).isNotNegative();
    assertThat((double) nanos).isGreaterThan(msecs);

    System.out.println("msecs = " + msecs);
    System.out.println("nanos = " + nanos);

    var t1 = timer.elapsedMsecs();
    var t2 = timer.elapsedMsecs();

    assertThat(t1).isNotNegative();
    assertThat(t2).isNotNegative().isGreaterThan(t1);
  }

  // @SuppressWarnings("null")
  @Test
  void testElapstr() {
    var s1 = timer.elapstr();
    var s2 = timer.toString();

    assertThat(s1).isNotNull().isNotEmpty().isNotBlank();

    assertThat(s2).isNotNull().isNotEmpty().isNotBlank();

    assertThat(s1).isNotEqualTo(s2);

    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);
  }

}
