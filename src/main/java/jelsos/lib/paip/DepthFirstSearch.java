package jelsos.lib.paip;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.eclipse.jdt.annotation.NonNull;
import jelsos.lib.O;
import jelsos.lib.Opt;
import jelsos.lib.function.Supp;

public final class DepthFirstSearch<@NonNull T> {

  @FunctionalInterface
  public interface CarrierSupplier<@NonNull T> extends Supp<Deque<Iterator<@NonNull T>>> {
  }

  public static <T> DepthFirstSearch<@NonNull T> of(Adjs<@NonNull T> adjs,
      Predicate<@NonNull T> goal) {
    return new DepthFirstSearch<>(adjs, goal);
  }

  public Opt<T> search(T start) {
    return search(start, LinkedList::new);
  }

  public Opt<T> search(T start, CarrierSupplier<T> cs) {
    var carrier = cs.get();
    carrier.addFirst(O.nn(O.nn(List.of(start)).iterator()));
    return searchImpl(carrier);
  }

  private Opt<T> searchImpl(Deque<Iterator<T>> carrier) {
    while (!carrier.isEmpty()) {
      @SuppressWarnings("null")
      var it = carrier.getFirst();
      if (!it.hasNext()) {
        // No elements in the first iterator in carrier, let's remove it.
        carrier.removeFirst();
        continue;
      }

      @SuppressWarnings("null")
      var element = O.nn(it.next());
      if (goal.test(element))
        // We have a success.
        return Opt.of(element);

      var iteratorOverChildren = adjs.apply(element).iterator();
      if (iteratorOverChildren.hasNext()) {
        carrier.addFirst(iteratorOverChildren);
      }
    }

    // No more iterables in the carrier - we didn't succeed.
    return Opt.empty();
  }

  private final Adjs<T> adjs;

  private final Predicate<T> goal;

  private DepthFirstSearch(Adjs<T> adjs, Predicate<T> goal) {
    this.adjs = O.nn(adjs);
    this.goal = O.nn(goal);
  }
}
