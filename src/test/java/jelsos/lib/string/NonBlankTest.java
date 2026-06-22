package jelsos.lib.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import jelsos.lib.Opt;

class NonBlankTest {

  @Test
  void testOf() {
    assertThat(NonBlank.of(StrTest.NO_WHITESPACE).get().value())
        .isEqualTo(StrTest.NO_WHITESPACE);

    assertThat(NonBlank.of(StrTest.HAVING_WHITESPACE).get().value())
        .isEqualTo(StrTest.HAVING_WHITESPACE);
  }

  @Test
  void testOptOf() {
    assertThat(NonBlank.of(StrTest.EMPTY)).isEqualTo(Opt.empty());
    assertThat(NonBlank.of(StrTest.BLANK)).isEqualTo(Opt.empty());

    assertThat(NonBlank.of(StrTest.NO_WHITESPACE)).isNotEqualTo(Opt.empty());
    assertThat(NonBlank.of(StrTest.NO_WHITESPACE).get().value())
        .isEqualTo(StrTest.NO_WHITESPACE);

    assertThat(NonBlank.of(StrTest.HAVING_WHITESPACE)).isNotEqualTo(Opt.empty());
    assertThat(NonBlank.of(StrTest.HAVING_WHITESPACE).get().value())
        .isEqualTo(StrTest.HAVING_WHITESPACE);
  }

  @Test
  void testEquality() {
    final var o1 = NonBlank.of(StrTest.NO_WHITESPACE);
    final var o11 = NonBlank.of(StrTest.NO_WHITESPACE);
    final var o2 = NonBlank.of(StrTest.HAVING_WHITESPACE);

    assertThat(o1)
        .isNotEqualTo(null)
        .isNotEqualTo(o2)
        .isEqualTo(o1)
        .isEqualTo(o11);
  }

  @Test
  void testHashCode() {
    final var h1 = NonBlank.of(StrTest.NO_WHITESPACE).hashCode();
    final var h11 = NonBlank.of(StrTest.NO_WHITESPACE).hashCode();
    final var h2 = NonBlank.of(StrTest.HAVING_WHITESPACE).hashCode();
    final var h22 = NonBlank.of(StrTest.HAVING_WHITESPACE).hashCode();

    assertThat(h1).isEqualTo(h11);
    assertThat(h2).isEqualTo(h22);
  }

}
