package jelsos.lib.math.newtype;

import java.util.function.LongPredicate;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.O;
import jelsos.lib.Opt;
import jelsos.lib.function.LongFn;
import jelsos.lib.newtype.AbstractNewtype;

public class NewLong extends AbstractNewtype<NewLong> {

  protected static <S extends NewLong> Opt<S> of(
      long n,
      LongPredicate pred,
      LongFn<S> constr) {

    return pred.test(n) ? Opt.of(constr.apply(n)) : Opt.empty();
  }

  protected static <S extends NewLong> Opt<S> ofNullable(
      @Nullable Long n,
      LongPredicate pred,
      LongFn<S> constr) {
    return null == n ? Opt.empty() : of(n, pred, constr);
  }

  private final long value;

  protected NewLong(long value) {
    this.value = value;
  }

  public long value() {
    return value;
  }

  @Override
  protected final int hash() {
    return Long.hashCode(value());
  }

  @Override
  protected final boolean isEqualTo(NewLong other) {
    return value() == other.value();
  }

  @Override
  public String toString() {
    return O.nn(String.valueOf(value()));
  }

}
