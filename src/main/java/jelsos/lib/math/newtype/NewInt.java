package jelsos.lib.math.newtype;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.O;
import jelsos.lib.Opt;
import jelsos.lib.newtype.AbstractNewtype;

public class NewInt extends AbstractNewtype<NewInt> {

  protected static <S extends NewInt> Opt<S> of(int n,
      IntPredicate pred,
      IntFunction<S> constr) {
    return pred.test(n)
        ? Opt.of(constr.apply(n))
        : Opt.empty();
  }

  protected static <S extends NewInt> Opt<S> ofNullable(
      @Nullable Integer n,
      IntPredicate pred,
      IntFunction<S> constr) {
    return null == n ? Opt.empty() : of(n, pred, constr);
  }

  private final int value;

  protected NewInt(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  @Override
  protected final int hash() {
    return value();
  }

  @Override
  protected final boolean isEqualTo(NewInt other) {
    return value() == other.value();
  }

  @Override
  public String toString() {
    return O.nn(String.valueOf(value()));
  }

}
