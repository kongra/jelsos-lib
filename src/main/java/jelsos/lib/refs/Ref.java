package jelsos.lib.refs;

import org.eclipse.jdt.annotation.Nullable;

public final class Ref<T> {

  @SuppressWarnings("java:S1104")
  public @Nullable T value;

  public Ref() {}

  public Ref(T value) {
    this.value = value;
  }

}