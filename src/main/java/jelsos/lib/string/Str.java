package jelsos.lib.string;

import org.eclipse.jdt.annotation.Nullable;

public final class Str {

  public static @Nullable String strip(@Nullable String s) {
    return s == null ? null : s.strip();
  }

  public static String wrapInQuotes(String s) {
    return "\"" + s + "\"";
  }

  private Str() {}

}
