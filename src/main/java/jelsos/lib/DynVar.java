package jelsos.lib;

import java.util.concurrent.Callable;

import org.eclipse.jdt.annotation.NonNull;

import jelsos.lib.ex.Ex;
import jelsos.lib.function.Deref;
import jelsos.lib.function.Supp;

public final class DynVar<@NonNull T> implements Deref<Opt<T>> {

  @SuppressWarnings("null")
  public static <@NonNull T> DynVar<T> newInstance() {
    return of(ScopedValue.newInstance());
  }

  public static <@NonNull T> DynVar<T> of(ScopedValue<T> scopedValue) {
    return new DynVar<>(scopedValue);
  }

  public void exec(T value, Runnable body) {
    ScopedValue.where(scopedValue, value).run(body);
  }

  public <E> E eval(T value, Callable<E> body) {
    return Ex.evalUnchecked(() -> ScopedValue.where(scopedValue, value).call(body::call));
  }

  @SuppressWarnings("null")
  public T get(T defaultValue) {
    return scopedValue.orElse(defaultValue);
  }

  public T get(Supp<T> defaultValueSupplier) {
    return deref().orElseGet(defaultValueSupplier);
  }

  @SuppressWarnings("null")
  @Override
  public Opt<T> deref() {
    return scopedValue.isBound() ? Opt.of(scopedValue.get()) : Opt.empty();
  }

  private final ScopedValue<T> scopedValue;

  private DynVar(ScopedValue<T> scopedValue) {
    this.scopedValue = scopedValue;
  }
}
