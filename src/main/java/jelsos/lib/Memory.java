package jelsos.lib;

public final class Memory {

  public static String room() {
    var rt = Runtime.getRuntime();
    var free = rt.freeMemory();
    var total = rt.totalMemory();
    var mx = rt.maxMemory();
    var used = total - free;
    var digits = 2;

    return O.nn("Used: %s | Free: %s | Total :%s | Max: %s".formatted(
        HumanReadable.bytes(used, digits), HumanReadable.bytes(free, digits),
        HumanReadable.bytes(total, digits), HumanReadable.bytes(mx, digits)));
  }

  private Memory() {}

}
