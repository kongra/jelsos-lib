package jelsos.lib.math.newtype;

import jelsos.lib.Opt;

public final class PosLong extends NewLong {

  public static Opt<PosLong> of(long n) {
    return of(n, PosLong::isPos, PosLong::new);
  }

  public static Opt<PosLong> ofNullable(Long n) {
    return ofNullable(n, PosLong::isPos, PosLong::new);
  }

  public static boolean isPos(long n) {
    return n > 0;
  }

  private PosLong(long value) {
    super(value);
  }

}