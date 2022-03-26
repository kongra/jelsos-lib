package telsos.pprint;

import static telsos.Ch.chNat;

import java.util.LinkedList;

public class TreePrinter<T> {

  @FunctionalInterface
  public interface Adjs<T> {
    Iterable<T> apply(T obj);
  }

  @FunctionalInterface
  public interface Repr<T> {
    String apply(T obj);
  }

  @FunctionalInterface
  public interface Worker {
    void work(String repr);
  }

  public static <T> TreePrinter<T> of(Adjs<T> adjs, Repr<T> repr) {
    return new TreePrinter<>(adjs, repr);
  }

  public void pprint(T node, int depth, Worker worker) {
    chNat(depth);
    var lastChildInfos = new LinkedList<Boolean>();
    lastChildInfos.add(true);
    impl(node, depth, 0, true, lastChildInfos, worker);
  }

  public void pprint(T node, Worker worker) {
    pprint(node, Integer.MAX_VALUE, worker);
  }

  private void impl(T node, int depth, int level, boolean isFirst,
      LinkedList<Boolean> lastChildInfos, Worker worker) {

    var s = repr.apply(node);
    var pfx = isFirst ? EMPTY : EOL;
    var r = level == 0 ? pfx + s : pfx + indent(lastChildInfos) + s;

    worker.work(r);

    if (level != depth) {
      var children = adjs.apply(node);
      var it = children.iterator();

      while (it.hasNext()) {
        var child = it.next();
        var isLast = !it.hasNext();

        lastChildInfos.addFirst(isLast);
        impl(child, depth, level + 1, false, lastChildInfos, worker);
        lastChildInfos.removeFirst();
      }
    }
  }

  private static String indent(LinkedList<Boolean> lastChildInfos) {
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
      var info = iter.previous();
      prefix.append(Boolean.TRUE.equals(info) ? EMPTY_INDENT : INDENT);
      n--;
    }

    return prefix.append(suffix).toString();
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
