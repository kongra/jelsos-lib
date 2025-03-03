package jelsos.lib.typeclass.instances;

import java.util.Objects;

import jelsos.lib.typeclass.Bounded;

public record BoundsRecord<T>(T minBound, T maxBound)
    implements Bounded.Bounds<T> {

  public BoundsRecord {
    Objects.requireNonNull(minBound);
    Objects.requireNonNull(maxBound);
  }

}
