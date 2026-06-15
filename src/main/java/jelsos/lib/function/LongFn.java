package jelsos.lib.function;

@FunctionalInterface
public interface LongFn<R> extends java.util.function.LongFunction<R> {

    @Override
    R apply(long value);
}
