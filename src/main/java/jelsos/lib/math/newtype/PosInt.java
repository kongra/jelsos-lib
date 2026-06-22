package jelsos.lib.math.newtype;

import jelsos.lib.Opt;

public final class PosInt extends NewInt {

  public static Opt<PosInt> of(int n) {
    return of(n, PosInt::isPos, PosInt::new);
  }

  public static Opt<PosInt> ofNullable(Integer n) {
    return ofNullable(n, PosInt::isPos, PosInt::new);
  }

  public static boolean isPos(int n) {
    return n > 0;
  }

  private PosInt(int value) {
    super(value);
  }

}
