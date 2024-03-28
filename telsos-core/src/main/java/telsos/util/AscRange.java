// © 2024 Konrad Grzanek <kongra@gmail.com>
package telsos.util;

import java.util.Optional;

import telsos.newtype.Newtype;

public final class AscRange extends Newtype<LongPair> {

  public static Optional<AscRange> of(LongPair longPair) {
    return of(longPair, AscRange::isValid, AscRange::new);
  }

  public static Optional<AscRange> of(long startInclusive,
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