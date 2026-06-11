package jelsos.lib.ex;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import jelsos.lib.function.NnSupplier;

public final class Ex {

  public static <T> T evalUnchecked(Callable<T> body) {
    try {
      return body.call();
    } catch (final Exception e) {
      return rethrow(e);
    }
  }

  public static <T> T rethrow(Throwable t) {
    sneakyThrow0(t);
    throw new Impossible();
  }

  public static NnSupplier<ExInfo> info(String message) {
    return () -> new ExInfo(message);
  }

  public static NnSupplier<ExInfo> info(String message, Object data) {
    return () -> new ExInfo(message, data);
  }

  public static NnSupplier<ExInfo> info(NnSupplier<String> messageSupplier) {
    return () -> new ExInfo(messageSupplier.get());
  }

  public static NnSupplier<ExInfo> info(
      NnSupplier<String> messageSupplier,
      Object data) {
    return () -> new ExInfo(messageSupplier.get(), data);
  }

  public static NnSupplier<ExInfo> info(String message,
      Supplier<Object> dataSupplier) {
    return () -> new ExInfo(message, dataSupplier.get());
  }

  public static NnSupplier<ExInfo> info(
      NnSupplier<String> messageSupplier,
      NnSupplier<Object> dataSupplier) {
    return () -> new ExInfo(messageSupplier.get(), dataSupplier.get());
  }

  public static NnSupplier<Invalid> invalid(String message, Object what) {
    return () -> new Invalid(message, what);
  }

  public static NnSupplier<Invalid> invalid(NnSupplier<String> messageSupplier,
      Object what) {
    return () -> new Invalid(messageSupplier.get(), what);
  }

  public static NnSupplier<Invalid> invalid(
      String message,
      NnSupplier<Object> whatSupplier) {
    return () -> new Invalid(message, whatSupplier.get());
  }

  public static Supplier<Invalid> invalid(NnSupplier<String> messageSupplier,
      NnSupplier<Object> whatSupplier) {
    return () -> new Invalid(messageSupplier.get(), whatSupplier.get());
  }

  public static NnSupplier<Impossible> impossible(String message) {
    return () -> new Impossible(message);
  }

  public static NnSupplier<Impossible> impossible(
      NnSupplier<String> messageSupplier) {
    return () -> new Impossible(messageSupplier.get());
  }

  @SuppressWarnings("unchecked")
  private static <X extends Throwable> void sneakyThrow0(Throwable t) throws X {
    throw (X) t;
  }

  private Ex() {}

}
