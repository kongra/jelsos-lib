package jelsos.lib.math;

import java.util.Optional;

import org.eclipse.jdt.annotation.Nullable;

import jelsos.lib.O;
import jelsos.lib.function.Supp;

public enum Kleene {

  TRUE, FALSE, UNKNOWN;

  public static Kleene fromBoolean(boolean b) {
    return b ? TRUE : FALSE;
  }

  public static Kleene fromBoolean(@Nullable Boolean b) {
    return b == null ? UNKNOWN : fromBoolean(b.booleanValue());
  }

  public static Optional<Kleene> parseString(String s) {
    return O.nn(switch (s) {
      case "true", "TRUE"       -> Optional.of(TRUE);
      case "false", "FALSE"     -> Optional.of(FALSE);
      case "unknown", "UNKNOWN" -> Optional.of(UNKNOWN);
      default                   -> Optional.empty();
    });
  }

  public static Kleene not(Kleene k) {
    return switch (k) {
      case TRUE    -> FALSE;
      case FALSE   -> TRUE;
      case UNKNOWN -> UNKNOWN;
    };
  }

  public static Kleene not(Supp<Kleene> supplier) {
    return not(supplier.get());
  }

  public Kleene negate() {
    return not(this);
  }

  public static Kleene and(Kleene a, Supp<Kleene> b) {
    return switch (a) {
      case TRUE    -> b.get();
      case FALSE   -> FALSE;
      case UNKNOWN -> b.get() == FALSE ? FALSE : UNKNOWN;
    };
  }

  public Kleene and(Supp<Kleene> b) {
    return and(this, b);
  }

  public static Kleene or(Kleene a, Supp<Kleene> b) {
    return switch (a) {
      case TRUE    -> TRUE;
      case FALSE   -> b.get();
      case UNKNOWN -> b.get() == TRUE ? TRUE : UNKNOWN;
    };
  }

  public Kleene or(Supp<Kleene> b) {
    return or(this, b);
  }

  public static Kleene xor(Kleene a, Supp<Kleene> b) {
    return switch (a) {
      case TRUE    -> not(b);
      case FALSE   -> b.get();
      case UNKNOWN -> UNKNOWN;
    };
  }

  public Kleene xor(Supp<Kleene> b) {
    return xor(this, b);
  }

  @FunctionalInterface
  public interface Predicate<T> {

    Kleene test(T t);

    default Predicate<T> and(Predicate<T> other) {
      return t -> Kleene.and(test(t), () -> other.test(t));
    }

    default Predicate<T> or(Predicate<T> other) {
      return t -> Kleene.or(test(t), () -> other.test(t));
    }

    default Predicate<T> negate() {
      return t -> Kleene.not(test(t));
    }

    default Predicate<T> xor(Predicate<T> other) {
      return t -> Kleene.xor(test(t), () -> other.test(t));
    }
  }
}
