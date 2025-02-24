package telsos.typeclass;

@FunctionalInterface
public interface Semigroup<T> {

  T sconcat(T x, T y);

}
