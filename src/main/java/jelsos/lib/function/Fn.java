package jelsos.lib.function;

import org.eclipse.jdt.annotation.NonNull;

@FunctionalInterface
public interface Fn<@NonNull T, @NonNull R> extends java.util.function.Function<T, R> {

    @Override
    R apply(T t);

    default <V> Fn<@NonNull V, @NonNull R> compose(Fn<? super V, ? extends T> before) {
        return (V v) -> apply(before.apply(v));
    }

    default <V> Fn<@NonNull T, @NonNull V> andThen(Fn<? super R, ? extends V> after) {
        return (T t) -> after.apply(apply(t));
    }

    static <T> Fn<@NonNull T, @NonNull T> identity() {
        return t -> t;
    }
}
