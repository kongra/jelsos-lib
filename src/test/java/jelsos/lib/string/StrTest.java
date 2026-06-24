package jelsos.lib.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.jdt.annotation.Nullable;
import org.junit.jupiter.api.Test;

class StrTest {

  static final @Nullable String NULL = null;

  static final String EMPTY = "";

  static final String BLANK = " \t\n\r";

  static final String NO_WHITESPACE = "abcd";

  static final String HAVING_WHITESPACE = BLANK + NO_WHITESPACE + BLANK;

  @Test
  void testStrip() {
    assertThat(NULL).isNull();
    assertThat(EMPTY).isEmpty();
    assertThat(BLANK).isBlank();

    @SuppressWarnings("null")
    var _ = assertThat(NO_WHITESPACE).isNotNull().isNotBlank().isNotEmpty();

    @SuppressWarnings("null")
    var _ = assertThat(HAVING_WHITESPACE).isNotNull().isNotBlank().isNotEmpty();

    assertThat(Str.strip(NULL)).isNull();

    @SuppressWarnings("null")
    var _ = assertThat(Str.strip(EMPTY)).isNotNull().isEqualTo("");

    @SuppressWarnings("null")
    var _ = assertThat(Str.strip(BLANK)).isNotNull().isEqualTo("");

    @SuppressWarnings("null")
    var _ = assertThat(Str.strip(NO_WHITESPACE)).isNotNull().isNotBlank().isNotEmpty()
        .isEqualTo(NO_WHITESPACE);

    @SuppressWarnings("null")
    var _ = assertThat(Str.strip(HAVING_WHITESPACE)).isNotNull().isNotBlank().isNotEmpty()
        .isEqualTo(NO_WHITESPACE).isNotEqualTo(HAVING_WHITESPACE);
  }

}
