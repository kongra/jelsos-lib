package jelsos.lib.paip;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import jelsos.lib.O;
import jelsos.lib.function.Supp;

public final class DepthFirstSearch<T> {

  @FunctionalInterface
  public interface CarrierSupplier<T> extends Supp<Deque<Iterator<T>>> {
  }

  public static <T> DepthFirstSearch<T> of(Adjs<T> adjs, Predicate<T> goal) {
    return new DepthFirstSearch<>(adjs, goal);
  }

  public Optional<T> search(T start) {
    return search(start, LinkedList::new);
  }

  public Optional<T> search(T start, CarrierSupplier<T> cs) {
    var carrier = cs.get();
    carrier.addFirst(O.nn(O.nn(List.of(start)).iterator()));
    return searchImpl(carrier);
  }

  private Optional<T> searchImpl(Deque<Iterator<T>> carrier) {
    while (!carrier.isEmpty()) {
      @SuppressWarnings("null")
      var it = carrier.getFirst();
      if (!it.hasNext()) {
        // No elements in the first iterator in carrier, let's remove it.
        carrier.removeFirst();
        continue;
      }

      var element = it.next();
      if (goal.test(element))
        // We have a success.
        return O.nn(Optional.of(element));

      var iteratorOverChildren = adjs.apply(element).iterator();
      if (iteratorOverChildren.hasNext()) {
        carrier.addFirst(iteratorOverChildren);
      }
    }

    // No more iterables in the carrier - we didn't succeed.
    return O.nn(Optional.empty());
  }

  private final Adjs<T> adjs;

  private final Predicate<T> goal;

  private DepthFirstSearch(Adjs<T> adjs, Predicate<T> goal) {
    this.adjs = O.nn(adjs);
    this.goal = O.nn(goal);
  }
}
