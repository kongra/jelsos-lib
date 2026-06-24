package jelsos.lib;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.function.Fn;
import jelsos.lib.function.Supp;

public sealed

interface Opt<@NonNull T> {

  record Some<@NonNull T>(T value)implements Opt<T>
  {
  }

  record None<@NonNull T>()implements Opt<T>
  {
  }

  None<?> NONE = new None<>();

  @SuppressWarnings("unchecked")
  static <T> Opt<@NonNull T> empty() {
    return (Opt<@NonNull T>) NONE;
  }

  static <T> Opt<@NonNull T> of(T value) {
    return new Some<>(Objects.requireNonNull(value));
  }

  static <T> Opt<@NonNull T> ofNullable(@Nullable T value) {
    return value == null ? empty() : new Some<>(value);
  }

  static <T> Opt<@NonNull T> from(Optional<T> optional) {
    return optional.isPresent() ? of(optional.get()) : empty();
  }

  default boolean isPresent() {
    return this instanceof Some;
  }

  default boolean isEmpty() {
    return this instanceof None;
  }

  default T get() {
    return switch (this) {
      case Some<T>(var v) -> v;
      case None<T> _ -> throw new NoSuchElementException();
    };
  }

  default T orElse(T other) {
    return switch (this) {
      case Some<T>(var v) -> v;
      case None<T> _ -> other;
    };
  }

  default T orElseGet(Supp<T> supplier) {
    return switch (this) {
      case Some<T>(var v) -> v;
      case None<T> _ -> supplier.get();
    };
  }

  default T orElseThrow() {return switch(this){case Some<T>(var v)->v;case None<T>_->throw new NoSuchElementException();};}

  default <X extends Throwable> T orElseThrow(Supp<X> exceptionSupplier) throws X {
    return switch (this) {
      case Some<T>(var v) -> v;
      case None<T> _ -> throw exceptionSupplier.get();
    };
  }

  default <U> Opt<@NonNull U> map(Fn<T, @NonNull U> mapper) {
    return switch (this) {
      case Some<T>(var v) -> Opt.of(mapper.apply(v));
      case None<T> _ -> empty();
    };
  }

  default <U> Opt<@NonNull U> flatMap(Fn<T, Opt<@NonNull U>> mapper) {
    return switch (this) {
      case Some<T>(var v) -> mapper.apply(v);
      case None<T> _ -> empty();
    };
  }

  default Opt<T> filter(Predicate<T> predicate) {
    return switch (this) {
      case Some<T>(var v) -> predicate.test(v) ? this : empty();
      case None<T> _ -> this;
    };
  }

  default Opt<@NonNull T> or(Supp<Opt<@NonNull T>> supplier) {
    return switch (this) {
      case Some<@NonNull T> _ -> this;
      case None<@NonNull T> _ -> supplier.get();
    };
  }

  default void ifPresent(Consumer<T> action) {
    if (this instanceof Some<T>(var v)) {
      action.accept(v);
    }
  }

  default void ifPresentOrElse(Consumer<T> action, Runnable emptyAction) {
    switch (this) {
      case Some<T>(var v) -> action.accept(v);
      case None<T> _ -> emptyAction.run();
    }
  }


  default Stream<T> stream() {
    return switch (this) {
      case Some<T>(var v) -> O.nn(Stream.of(v));
      case None<T> _ -> O.nn(Stream.empty());
    };
  }
}
