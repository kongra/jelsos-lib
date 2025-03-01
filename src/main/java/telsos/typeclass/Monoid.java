package telsos.typeclass;

public interface Monoid<T> extends Semigroup<T> {

  T mempty();

  default T mappend(T x, T y) {
    return sconcat(x, y);
  }

}
