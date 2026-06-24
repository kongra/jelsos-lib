package jelsos.lib.newtype;

import java.util.function.Predicate;

import org.eclipse.jdt.annotation.NonNull;

import jelsos.lib.O;
import jelsos.lib.Opt;
import jelsos.lib.function.Fn;

public class Newtype<@NonNull T> extends AbstractNewtype<Newtype<T>> {

  protected static <T, S extends Newtype<@NonNull T>> Opt<S> of(@NonNull T t,
      Predicate<T> pred, Fn<@NonNull T, S> constr) {

    return pred.test(t) ? Opt.of(constr.apply(t)) : Opt.empty();
  }

  private final T value;

  protected Newtype(T value) {
    this.value = value;
  }

  public @NonNull T value() {
    return value;
  }

  @Override
  protected final int hash() {
    return value().hashCode();
  }

  @Override
  protected final boolean isEqualTo(Newtype<T> other) {
    return value().equals(other.value);
  }

  @Override
  public String toString() {
    return O.nn(value().toString());
  }

}
