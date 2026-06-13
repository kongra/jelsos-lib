// © 2026 Konrad Grzanek <kongra@gmail.com>
package jelsos.lib.function;

@FunctionalInterface
public interface Supplier<T> extends java.util.function.Supplier<T> {

  @Override
  T get();

}
