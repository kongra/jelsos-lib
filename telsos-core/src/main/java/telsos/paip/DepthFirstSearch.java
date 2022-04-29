// © 2022 Konrad Grzanek <kongra@gmail.com>
package telsos.paip;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import io.vavr.Function1;
import io.vavr.control.Option;

public final class DepthFirstSearch<T> {

  @FunctionalInterface
  public interface Adjs<T> extends Function1<T, Iterable<T>> {}

  @FunctionalInterface
  public interface CarrierSupplier<T> extends Supplier<Deque<Iterator<T>>> {}

  public static <T> DepthFirstSearch<T> of(Adjs<T> adjs, Predicate<T> goal) {
    return new DepthFirstSearch<>(adjs, goal);
  }

  public Option<T> search(T start) {
    return search(start, LinkedList::new);
  }

  public Option<T> search(T start, CarrierSupplier<T> cs) {
    final var carrier = cs.get();
    carrier.addFirst(List.of(start).iterator());
    return searchImpl(carrier);
  }

  private Option<T> searchImpl(Deque<Iterator<T>> carrier) {
    while (!carrier.isEmpty()) {
      final var it = carrier.getFirst();
      if (!it.hasNext()) {
        carrier.removeFirst();
        continue;
      }

      final var e = it.next();
      if (goal.test(e))
        return Option.of(e);

      var children = adjs.apply(e);
      if (children != null) {
        var childrenIt = children.iterator();
        if (childrenIt.hasNext()) {
          carrier.addFirst(childrenIt);
        }
      }
    }

    return Option.none();
  }

  private final Adjs<T> adjs;

  private final Predicate<T> goal;

  private DepthFirstSearch(Adjs<T> adjs, Predicate<T> goal) {
    this.goal = goal;
    this.adjs = adjs;
  }
}
