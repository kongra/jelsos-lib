package jelsos.lib.math.newtype;

import jelsos.lib.Opt;

public final class NatLong extends NewLong {

  public static Opt<NatLong> of(long n) {
    return of(n, NatLong::isNat, NatLong::new);
  }

  public static Opt<NatLong> ofNullable(Long n) {
    return ofNullable(n, NatLong::isNat, NatLong::new);
  }

  public static boolean isNat(long n) {
    return n >= 0;
  }

  private NatLong(long value) {
    super(value);
  }

}
