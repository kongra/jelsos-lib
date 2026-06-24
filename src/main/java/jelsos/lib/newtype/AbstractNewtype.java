package jelsos.lib.newtype;

import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractNewtype<T extends AbstractNewtype<T>> {

  protected abstract int hash();

  protected abstract boolean isEqualTo(T other);

  @Override
  public final int hashCode() {
    return 31 * 17 + hash();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final boolean equals(@Nullable Object obj) {
    return this == obj
        || obj != null && this.getClass() == obj.getClass() && isEqualTo((T) obj);
  }

}
