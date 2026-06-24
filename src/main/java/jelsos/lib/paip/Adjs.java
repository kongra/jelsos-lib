package jelsos.lib.paip;

import java.util.function.Function;

@FunctionalInterface
public interface Adjs<T> extends Function<T, Iterable<T>> {

  @Override
  Iterable<T> apply(T t);

}
