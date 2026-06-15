package jelsos.lib.ex;

import java.util.concurrent.Callable;

import jelsos.lib.function.Supp;

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

  public static Supp<ExInfo> info(String message, Object data) {
    return () -> new ExInfo(message, data);
  }

  public static Supp<ExInfo> info(
      Supp<String> messageSupplier,
      Object data) {
    return () -> new ExInfo(messageSupplier.get(), data);
  }

  public static Supp<ExInfo> info(String message,
      Supp<Object> dataSupplier) {
    return () -> new ExInfo(message, dataSupplier.get());
  }

  public static Supp<ExInfo> info(
      Supp<String> messageSupplier,
      Supp<Object> dataSupplier) {
    return () -> new ExInfo(messageSupplier.get(), dataSupplier.get());
  }

  public static Supp<Invalid> invalid(String message, Object what) {
    return () -> new Invalid(message, what);
  }

  public static Supp<Invalid> invalid(Supp<String> messageSupplier,
      Object what) {
    return () -> new Invalid(messageSupplier.get(), what);
  }

  public static Supp<Invalid> invalid(
      String message,
      Supp<Object> whatSupplier) {
    return () -> new Invalid(message, whatSupplier.get());
  }

  public static Supp<Invalid> invalid(Supp<String> messageSupplier,
      Supp<Object> whatSupplier) {
    return () -> new Invalid(messageSupplier.get(), whatSupplier.get());
  }

  public static Supp<Impossible> impossible(String message) {
    return () -> new Impossible(message);
  }

  public static Supp<Impossible> impossible(
      Supp<String> messageSupplier) {
    return () -> new Impossible(messageSupplier.get());
  }

  @SuppressWarnings("unchecked")
  private static <X extends Throwable> void sneakyThrow0(Throwable t) throws X {
    throw (X) t;
  }

  private Ex() {}

}
