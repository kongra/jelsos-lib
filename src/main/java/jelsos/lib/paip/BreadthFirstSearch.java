package jelsos.lib.paip;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import jelsos.lib.O;
import jelsos.lib.function.Supp;

public final class BreadthFirstSearch<T> {

  @FunctionalInterface
  public interface CarrierSupplier<T> extends Supp<Deque<Iterable<T>>> {}

  public static <T> BreadthFirstSearch<T> of(Adjs<T> adjs, Predicate<T> goal) {
    return new BreadthFirstSearch<>(adjs, goal);
  }

  public Optional<T> search(T start) {
    return search(start, LinkedList::new);
  }

  public Optional<T> search(T start, CarrierSupplier<T> cs) {
    final var carrier = cs.get();
    carrier.addFirst(O.nn(List.of(start)));
    return searchImpl(carrier);
  }

  private Optional<T> searchImpl(Deque<Iterable<T>> carrier) {
    while (!carrier.isEmpty()) {
      @SuppressWarnings("null")
      final var iterable = carrier.pollFirst();
      for (final T element : iterable) {
        if (goal.test(element))
          // We have a success
          return O.nn(Optional.of(element));

        final var it = adjs.apply(element).iterator();
        if (it.hasNext())
          carrier.addLast(() -> it);
      }
    }

    // No more iterables in the carrier - we didn't succeed
    return O.nn(Optional.empty());
  }

  private final Adjs<T> adjs;

  private final Predicate<T> goal;

  private BreadthFirstSearch(Adjs<T> adjs, Predicate<T> goal) {
    this.adjs = O.nn(adjs);
    this.goal = O.nn(goal);
  }
}
