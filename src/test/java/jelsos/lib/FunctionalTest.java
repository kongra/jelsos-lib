package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import jelsos.lib.function.Fn;

class FunctionalTest {

  @Test
  void testFunctionApplication() {
    final Fn<Double, Double> square = x -> x * x;
    final double y = square.apply(5.0);
    assertThat(y).isEqualTo(25);
  }

}
