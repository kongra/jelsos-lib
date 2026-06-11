package jelsos.lib.ex;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.O;

public final class ExInfo extends RuntimeException {

  @java.io.Serial
  private static final long serialVersionUID = 1L;

  private final transient @Nullable Object data;

  public ExInfo() {
    data = null;
  }

  public ExInfo(String message) {
    this(message, (Object) null);
  }

  public ExInfo(Throwable cause) {
    super(cause);
    data = null;
  }

  public ExInfo(String message, @Nullable Object data) {
    super(message);
    this.data = data;
  }

  public ExInfo(String message, Throwable cause) {
    this(message, cause, null);
  }

  public ExInfo(String message, Throwable cause, @Nullable Object data) {
    super(message, cause);
    this.data = data;
  }

  public @Nullable Object getData() {
    return data;
  }

  @Override
  public String toString() {
    return O.nn("ExInfo [getData()=%s, super.toString()=%s]"
        .formatted(getData(), super.toString()));
  }

}
