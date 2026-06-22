package jelsos.lib.math.newtype;

import jelsos.lib.Opt;

public final class NatInt extends NewInt {

  public static Opt<NatInt> of(int n) {
    return of(n, NatInt::isNat, NatInt::new);
  }

  public static Opt<NatInt> ofNullable(Integer n) {
    return ofNullable(n, NatInt::isNat, NatInt::new);
  }

  public static boolean isNat(int n) {
    return n >= 0;
  }

  private NatInt(int value) {
    super(value);
  }

}
