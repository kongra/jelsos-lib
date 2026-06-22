package jelsos.lib.string;

import jelsos.lib.Opt;
import jelsos.lib.newtype.Newtype;

public final class NonBlank extends Newtype<String> {

  public static Opt<NonBlank> of(String s) {
    return of(s, NonBlank::isNonBlank, NonBlank::new);
  }

  public static boolean isNonBlank(String s) {
    return !s.isBlank();
  }

  private NonBlank(String value) {
    super(value);
  }

}
