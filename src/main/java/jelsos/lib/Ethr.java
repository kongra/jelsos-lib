package jelsos.lib;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;

import jelsos.lib.ex.Err;
import jelsos.lib.ex.Ex;
import jelsos.lib.function.Fn;
import jelsos.lib.function.Supp;

public sealed

interface Ethr<@NonNull R> {

  record Ok<@NonNull R>(R value)implements Ethr<R>
  {
  }

  record Fail<@NonNull R>(Err err)implements Ethr<R>
  {
  }

  static <R> Ethr<@NonNull R> ok(@NonNull R value) {
    return new Ok<>(value);
  }

  static <R> Ethr<@NonNull R> fail(Err err) {
    return new Fail<>(err);
  }

  static <R> Ethr<@NonNull R> fail(String message) {
    return new Fail<>(new Err.Message(message));
  }

  static <R> Ethr<@NonNull R> fail(RuntimeException ex) {
    return new Fail<>(new Err.Failure(ex));
  }

  default boolean isOk() {
    return this instanceof Ok;
  }

  default boolean isFail() {
    return this instanceof Fail;
  }

  default R value() {
    return switch (this) {
      case Ok<R>(var v) -> v;
      case Fail<R> _ -> throw new NoSuchElementException();
    };
  }

  default Err err() {
    return switch (this) {
      case Ok<R>(_) -> throw new NoSuchElementException();
      case Fail<R>(var e) -> e;
    };
  }

  default <U> Ethr<@NonNull U> map(Fn<R, @NonNull U> f) {
    return switch (this) {
      case Ok<R>(var v) -> ok(f.apply(v));
      case Fail<R>(var e) -> Ethr.<U>fail(e);
    };
  }

  default <U> Ethr<@NonNull U> flatMap(Fn<R, Ethr<@NonNull U>> f) {
    return switch (this) {
      case Ok<R>(var v) -> f.apply(v);
      case Fail<R>(var e) -> Ethr.<U>fail(e);
    };
  }

  default Ethr<R> mapErr(Fn<Err, Err> f) {
    return switch (this) {
      case Ok<R> _ -> this;
      case Fail<R>(var e) -> fail(f.apply(e));
    };
  }

  default R orElse(R other) {
    return switch (this) {
      case Ok<R>(var v) -> v;
      case Fail<R> _ -> other;
    };
  }

  default R orElseGet(Supp<R> supplier) {
    return switch (this) {
      case Ok<R>(var v) -> v;
      case Fail<R> _ -> supplier.get();
    };
  }

  default R orElseThrow() {
    return switch (this) {
      case Ok<R>(var value) -> value;
      case Fail<R>(Err.Failure(var ex)) -> Ex.rethrow(ex);
      case Fail<R>(Err.Message(var text)) -> throw new NoSuchElementException(text);};}

  default <U> U fold(Fn<Err, @NonNull U> onFail, Fn<R, @NonNull U> onOk) {
    return switch (this) {
      case Ok<R>(var v) -> onOk.apply(v);
      case Fail<R>(var e) -> onFail.apply(e);
    };
  }
}
