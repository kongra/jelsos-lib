package jelsos.lib.pprint;

import java.util.LinkedList;
import java.util.function.Consumer;

import org.eclipse.jdt.annotation.NonNull;

import jelsos.lib.O;
import jelsos.lib.ex.Impossible;
import jelsos.lib.function.Fn;
import jelsos.lib.math.newtype.NatInt;

public final class TreePrinter<@NonNull T> {

  @FunctionalInterface
  public interface Adjs<@NonNull T> extends Fn<T, Iterable<T>> {
  }

  @FunctionalInterface
  public interface Repr<@NonNull T> extends Fn<T, String> {
  }

  @FunctionalInterface
  public interface Worker extends Consumer<String> {
  }

  public static <T> TreePrinter<@NonNull T> of(Adjs<@NonNull T> adjs,
      Repr<@NonNull T> repr) {
    return new TreePrinter<>(adjs, repr);
  }

  public void print(T node, NatInt depth, Worker worker) {
    var lastChildInfos = new LinkedList<Boolean>();
    lastChildInfos.add(true);
    impl(node, depth.value(), 0, true, lastChildInfos, worker);
  }

  public void print(T node, Worker worker) {
    var depth = NatInt.of(Integer.MAX_VALUE).orElseThrow(Impossible::new);
    print(node, depth, worker);
  }


  private void impl(T node, int depth, int level, boolean isFirst,
      LinkedList<Boolean> lastChildInfos, Worker worker) {

    var s = repr.apply(node);
    var pfx = isFirst ? EMPTY : EOL;
    var r = level == 0 ? pfx + s : pfx + indent(lastChildInfos) + s;

    worker.accept(r);

    if (level != depth) {
      var children = adjs.apply(node);
      var it = children.iterator();

      while (it.hasNext()) {
        @SuppressWarnings("null")
        var child = it.next();
        var isLast = !it.hasNext();

        lastChildInfos.addFirst(isLast);
        impl(child, depth, level + 1, false, lastChildInfos, worker);
        lastChildInfos.removeFirst();
      }
    }
  }

  private static String indent(LinkedList<Boolean> lastChildInfos) {
    @SuppressWarnings("null")
    var isLast = lastChildInfos.getFirst();
    // lastChildInfos is never empty!

    var suffix = Boolean.TRUE.equals(isLast) ? FOR_LAST_CHILD : FOR_CHILD;
    var prefix = new StringBuilder();

    var n = lastChildInfos.size();

    var iter = lastChildInfos.listIterator(n);

    // We skip the last one
    iter.previous();
    n--;

    while (iter.hasPrevious() && n > 1) {
      @SuppressWarnings("null")
      var info = iter.previous();
      prefix.append(Boolean.TRUE.equals(info) ? EMPTY_INDENT : INDENT);
      n--;
    }

    return O.nn(prefix.append(suffix).toString());
  }

  private final Adjs<T> adjs;

  private final Repr<T> repr;

  private TreePrinter(Adjs<T> adjs, Repr<T> repr) {
    this.adjs = adjs;
    this.repr = repr;
  }

  private static final String INDENT = "│   ";
  private static final String EMPTY_INDENT = "    ";
  private static final String FOR_CHILD = "├── ";
  private static final String FOR_LAST_CHILD = "└── ";
  private static final String EOL = "\n";
  private static final String EMPTY = "";

}
