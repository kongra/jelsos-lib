package jelsos.lib.ex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ErrTest {

  @Test
  void testMessage() {
    final var err = new Err.Message("something went wrong");
    assertThat(err.text()).isEqualTo("something went wrong");
  }

  @Test
  void testFailure() {
    final var cause = new Impossible("oops");
    final var err = new Err.Failure(cause);
    assertThat(err.ex()).isSameAs(cause);
  }

}
