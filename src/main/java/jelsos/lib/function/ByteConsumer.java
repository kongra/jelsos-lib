package jelsos.lib.function;

import jelsos.lib.O;

@FunctionalInterface
public interface ByteConsumer {

  void accept(byte value);

  default ByteConsumer andThen(ByteConsumer after) {
    O.nn(after);
    return t -> {
      accept(t);
      after.accept(t);
    };
  }

}
