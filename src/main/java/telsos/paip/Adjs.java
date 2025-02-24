package telsos.paip;

import java.util.function.Function;

@FunctionalInterface
public interface Adjs<T> extends Function<T, Iterable<T>> {}