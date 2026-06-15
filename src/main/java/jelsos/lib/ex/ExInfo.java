package jelsos.lib.ex;

import jelsos.lib.O;

public final class ExInfo extends RuntimeException {

  @java.io.Serial
  private static final long serialVersionUID = 1L;

  private final transient Object data;

  public ExInfo(Throwable cause, Object data) {
    super(cause);
    this.data = data;
  }

  public ExInfo(String message, Object data) {
    super(message);
    this.data = data;
  }

  public ExInfo(String message, Throwable cause, Object data) {
    super(message, cause);
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  @Override
  public String toString() {
    return O.nn("ExInfo [getData()=%s, super.toString()=%s]"
        .formatted(getData(), super.toString()));
  }

}
