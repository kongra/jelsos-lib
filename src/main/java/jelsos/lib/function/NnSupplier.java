// © 2026 Konrad Grzanek <kongra@gmail.com>
package jelsos.lib.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface NnSupplier<T> extends Supplier<T> {

  @Override
  T get();

}
