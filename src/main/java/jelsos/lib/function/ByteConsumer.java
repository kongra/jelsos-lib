package jelsos.lib.function;

@FunctionalInterface
public interface ByteConsumer {

  void accept(byte value);

  default ByteConsumer andThen(ByteConsumer after) {
    return t -> {
      accept(t);
      after.accept(t);
    };
  }

}
