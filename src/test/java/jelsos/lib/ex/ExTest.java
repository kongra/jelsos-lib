package jelsos.lib.ex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExTest {

  @Test
  void testFail() {
    final var cause = new IOException("boom");
    assertThatThrownBy(() -> Ex.evalUnchecked(() -> { throw cause; }))
        .isSameAs(cause);
  }

  @Test
  void testEvalUncheckedSuccess() {
    assertThat(Ex.evalUnchecked(() -> 42)).isEqualTo(42);
  }

  @Test
  void testInfoSupplier() {
    final var ex = Ex.info("msg", "data").get();
    assertThat(ex.getMessage()).isEqualTo("msg");
    assertThat(ex.getData()).isEqualTo("data");
  }

  @Test
  void testInvalidSupplier() {
    final var ex = Ex.invalid("bad input", 99).get();
    assertThat(ex.getMessage()).isEqualTo("bad input");
    assertThat(ex.getWhat()).isEqualTo(99);
  }

  @Test
  void testImpossibleSupplier() {
    final var ex = Ex.impossible("should not happen").get();
    assertThat(ex.getMessage()).isEqualTo("should not happen");
  }

}
