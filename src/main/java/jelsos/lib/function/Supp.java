// © 2026 Konrad Grzanek <kongra@gmail.com>
package jelsos.lib.function;

@FunctionalInterface
public interface Supp<T> extends java.util.function.Supplier<T> {

  @Override
  T get();

}
