package jelsos.lib.logging;

import jelsos.lib.O;

public final class Logs {

  public static System.Logger get(Class<?> c) {
    return O.nn(System.getLogger(c.getName()));
  }

  private Logs() {}

}
