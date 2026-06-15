package jelsos.lib.ex;

import java.util.concurrent.Callable;

import jelsos.lib.function.Supplier;

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

  public static Supplier<ExInfo> info(String message, Object data) {
    return () -> new ExInfo(message, data);
  }

  public static Supplier<ExInfo> info(
      Supplier<String> messageSupplier,
      Object data) {
    return () -> new ExInfo(messageSupplier.get(), data);
  }

  public static Supplier<ExInfo> info(String message,
      Supplier<Object> dataSupplier) {
    return () -> new ExInfo(message, dataSupplier.get());
  }

  public static Supplier<ExInfo> info(
      Supplier<String> messageSupplier,
      Supplier<Object> dataSupplier) {
    return () -> new ExInfo(messageSupplier.get(), dataSupplier.get());
  }

  public static Supplier<Invalid> invalid(String message, Object what) {
    return () -> new Invalid(message, what);
  }

  public static Supplier<Invalid> invalid(Supplier<String> messageSupplier,
      Object what) {
    return () -> new Invalid(messageSupplier.get(), what);
  }

  public static Supplier<Invalid> invalid(
      String message,
      Supplier<Object> whatSupplier) {
    return () -> new Invalid(message, whatSupplier.get());
  }

  public static Supplier<Invalid> invalid(Supplier<String> messageSupplier,
      Supplier<Object> whatSupplier) {
    return () -> new Invalid(messageSupplier.get(), whatSupplier.get());
  }

  public static Supplier<Impossible> impossible(String message) {
    return () -> new Impossible(message);
  }

  public static Supplier<Impossible> impossible(
      Supplier<String> messageSupplier) {
    return () -> new Impossible(messageSupplier.get());
  }

  @SuppressWarnings("unchecked")
  private static <X extends Throwable> void sneakyThrow0(Throwable t) throws X {
    throw (X) t;
  }

  private Ex() {}

}
