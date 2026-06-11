package jelsos.lib.typeclass.instances;

import jelsos.lib.O;
import jelsos.lib.typeclass.Bounded;

public record BoundsRecord<T>(T minBound, T maxBound)
    implements Bounded.Bounds<T> {

  public BoundsRecord {
    O.nn(minBound);
    O.nn(maxBound);
  }

}
