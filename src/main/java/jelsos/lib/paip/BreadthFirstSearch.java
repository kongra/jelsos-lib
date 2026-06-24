package jelsos.lib.paip;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.eclipse.jdt.annotation.NonNull;
import jelsos.lib.O;
import jelsos.lib.Opt;
import jelsos.lib.function.Supp;

public final class BreadthFirstSearch<@NonNull T> {

  @FunctionalInterface
  public interface CarrierSupplier<@NonNull T> extends Supp<Deque<Iterable<T>>> {
  }

  public static <T> BreadthFirstSearch<@NonNull T> of(Adjs<@NonNull T> adjs,
      Predicate<@NonNull T> goal) {
    return new BreadthFirstSearch<>(adjs, goal);
  }

  public Opt<T> search(T start) {
    return search(start, LinkedList::new);
  }

  public Opt<T> search(T start, CarrierSupplier<T> cs) {
    var carrier = cs.get();
    carrier.addFirst(O.nn(List.of(start)));
    return searchImpl(carrier);
  }

  private Opt<T> searchImpl(Deque<Iterable<T>> carrier) {
    while (!carrier.isEmpty()) {
      @SuppressWarnings("null")
      var iterable = carrier.pollFirst();
      for (final T element : iterable) {
        if (goal.test(element))
          // We have a success
          return Opt.of(element);

        var it = adjs.apply(element).iterator();
        if (it.hasNext())
          carrier.addLast(() -> it);
      }
    }

    // No more iterables in the carrier - we didn't succeed
    return Opt.empty();
  }

  private final Adjs<T> adjs;

  private final Predicate<T> goal;

  private BreadthFirstSearch(Adjs<T> adjs, Predicate<T> goal) {
    this.adjs = O.nn(adjs);
    this.goal = O.nn(goal);
  }
}
