package jelsos.lib.util;

import java.util.Iterator;

import jelsos.lib.O;

public final class NonDestructiveIterable<T> implements Iterable<T> {

  public static <T> NonDestructiveIterable<T> of(Iterable<T> iterable) {
    O.nn(iterable);
    if (iterable instanceof final NonDestructiveIterable<T> it)
      return it;

    return new NonDestructiveIterable<>(iterable);
  }

  private final Iterable<T> iterable;

  private NonDestructiveIterable(Iterable<T> iterable) {
    this.iterable = iterable;
  }

  @Override
  public Iterator<T> iterator() {
    return NonDestructiveIterator.of(O.nn(iterable.iterator()));
  }

}
