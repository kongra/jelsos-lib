package jelsos.lib.logging;

import java.util.concurrent.Callable;

import jelsos.lib.Delay;
import jelsos.lib.DynVar;
import jelsos.lib.O;
import jelsos.lib.function.EntryConsumer;
import jelsos.lib.function.NnSupplier;
import jelsos.lib.string.NonBlank;
import jelsos.lib.string.Str;

@FunctionalInterface
public interface MDC {

  DynVar<Delay<MDC>> delayed = DynVar.newInstance();

  static void exec(NnSupplier<MDC> supplier, Runnable body) {
    delayed.exec(Delay.of(supplier), body);
  }

  static <E> E eval(NnSupplier<MDC> supplier, Callable<E> body) {
    return delayed.eval(Delay.of(supplier), body);
  }

  void forEach(EntryConsumer<NonBlank, NonBlank> action);

  default String asString() {
    final var buf = new StringBuilder("{");
    forEach((key, val, isLast) -> {
      buf.append(Str.wrapInQuotes(key.value()));
      buf.append(':');
      buf.append(Str.wrapInQuotes(val.value()));
      if (!isLast) {
        buf.append(",");
      }
    });

    return O.nn(buf.append('}').toString());
  }

}
