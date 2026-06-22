package jelsos.lib.util;

import jelsos.lib.Opt;
import jelsos.lib.newtype.Newtype;

public final class AscRange extends Newtype<LongPair> {

  public static Opt<AscRange> of(LongPair longPair) {
    return of(longPair, AscRange::isValid, AscRange::new);
  }

  public static Opt<AscRange> of(long startInclusive,
      long endExclusive) {
    return of(new LongPair(startInclusive, endExclusive), AscRange::isValid,
        AscRange::new);
  }

  public static boolean isValid(LongPair longPair) {
    return longPair.first() <= longPair.second();
  }

  private AscRange(LongPair value) {
    super(value);
  }

}