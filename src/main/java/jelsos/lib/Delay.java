package jelsos.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.ex.Ex;
import jelsos.lib.function.Deref;

public final class Delay<T> implements Deref<T> {

  public static <T> Delay<T> of(Supplier<T> supplier) {
    return new Delay<>(supplier);
  }

  @Override
  public T deref() {
    if (lock != null) {
      realize();
    }

    if (exception != null)
      return Ex.rethrow(O.nn(exception));

    return O.nn(value);
  }

  public boolean isRealized() {
    return lock == null;
  }

  @SuppressWarnings("java:S3077")
  private volatile @Nullable Lock lock;

  private @Nullable Supplier<T> supplier;

  private @Nullable T value;

  private @Nullable Exception exception;

  private Delay(Supplier<T> supplier) {
    lock = new ReentrantLock();
    this.supplier = O.nn(supplier);
  }

  private void realize() {
    final var l = lock;
    if (l != null) {
      l.lock();
      try {
        if (supplier != null) {
          try {
            value = supplier.get();
          } catch (final Exception e) {
            exception = e;
          }
          supplier = null;
          lock = null;
        }
      } finally {
        l.unlock();
      }
    }
  }
}
