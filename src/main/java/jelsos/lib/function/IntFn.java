package jelsos.lib.function;

import java.util.function.IntFunction;

import org.eclipse.jdt.annotation.NonNull;

@FunctionalInterface
public interface IntFn<@NonNull R> extends IntFunction<R> {

  @Override
  R apply(int value);

}
