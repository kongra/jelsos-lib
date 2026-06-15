package jelsos.lib.function;

@FunctionalInterface
public interface Fn<T, R> extends java.util.function.Function<T, R> {

    @Override
    R apply(T t);

    default <V> Fn<V, R> compose(Fn<? super V, ? extends T> before) {
        return (V v) -> apply(before.apply(v));
    }

    default <V> Fn<T, V> andThen(Fn<? super R, ? extends V> after) {
        return (T t) -> after.apply(apply(t));
    }

    static <T> Fn<T, T> identity() {
        return t -> t;
    }
}
