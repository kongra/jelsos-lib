package jelsos.lib.function;

@FunctionalInterface
public interface LongFunction<R> extends java.util.function.LongFunction<R> {

    @Override
    R apply(long value);
}
