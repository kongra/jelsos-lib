package jelsos.lib.ex;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.O;

public final class Invalid extends RuntimeException {

  @java.io.Serial
  private static final long serialVersionUID = 1L;

  private final transient Object what;

  public Invalid(String message, Object what) {
    this(message, null, what);
  }

  public Invalid(Throwable cause, Object what) {
    this(null, cause, what);
  }

  public Invalid(
      @Nullable String message,
      @Nullable Throwable cause,
      Object what) {

    final var enableSuppression = false;
    final var writableStackTrace = false;
    super(message, cause, enableSuppression, writableStackTrace);
    this.what = what;
  }

  public Object getWhat() {
    return what;
  }

  @Override
  public String toString() {
    return O.nn("Invalid [getWhat()=%s, super.toString()=%s]"
        .formatted(getWhat(), super.toString()));
  }

}
