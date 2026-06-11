// © 2025 Konrad Grzanek <kongra@gmail.com>
package jelsos.lib;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public final class O {

  public static <T> @NonNull T nn(T obj) {
    return Objects.requireNonNull(obj);
  }

  public static <T> @NonNull T nn(T obj, String message) {
    return Objects.requireNonNull(obj, message);
  }

  public static boolean isNull(@Nullable Object obj) {
    return obj == null;
  }

  public static boolean isSome(@Nullable Object obj) {
    return !isNull(obj);
  }

  public static <T> T or(@Nullable T obj, T other) {
    return obj != null ? obj : other;
  }

  @SafeVarargs
  public static <T> @Nullable T or(@Nullable T obj, @Nullable T other, T... others) {
    if (obj != null)
      return obj;

    if (other != null)
      return other;

    for (final T e : others) {
      if (e != null)
        return e;
    }

    return null;
  }

  public static <T> T or(@Nullable T obj, Supplier<T> orElse) {
    return obj != null ? obj : orElse.get();
  }

  private O() {}
}
