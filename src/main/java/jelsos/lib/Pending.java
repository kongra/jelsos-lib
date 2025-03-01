package jelsos.lib;

public interface Pending {

  boolean isPending();

  default boolean isRealized() {
    return !isPending();
  }

}
