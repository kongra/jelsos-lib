// © 2026 Konrad Grzanek <kongra@gmail.com>
package jelsos.lib.function;

import org.eclipse.jdt.annotation.NonNull;

@FunctionalInterface
public interface Supp<@NonNull T> extends java.util.function.Supplier<T> {

  @Override
  T get();

}
