package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import jelsos.lib.ex.Err;
import jelsos.lib.ex.Impossible;

class EthrTest {

  @Test
  void testOkConstruction() {
    final var ethr = Ethr.ok("hello");
    assertThat(ethr.isOk()).isTrue();
    assertThat(ethr.isFail()).isFalse();
    assertThat(ethr.value()).isEqualTo("hello");
  }

  @Test
  void testFailWithMessage() {
    final var ethr = Ethr.fail("something went wrong");
    assertThat(ethr.isFail()).isTrue();
    assertThat(ethr.isOk()).isFalse();
    assertThat(ethr.err()).isEqualTo(new Err.Message("something went wrong"));
  }

  @Test
  void testFailWithException() {
    final var cause = new Impossible("oops");
    final var ethr = Ethr.fail(cause);
    assertThat(ethr.isFail()).isTrue();
    assertThat(ethr.err()).isEqualTo(new Err.Failure(cause));
  }

  @Test
  void testMapOk() {
    assertThat(Ethr.ok(2).map(n -> n * 3).value()).isEqualTo(6);
  }

  @Test
  void testMapFailPropagates() {
    final Ethr<Integer> result = Ethr.<Integer>fail("error").map(n -> n * 3);
    assertThat(result.isFail()).isTrue();
    assertThat(result.err()).isEqualTo(new Err.Message("error"));
  }

  @Test
  void testFlatMapOk() {
    assertThat(Ethr.ok(2).flatMap(n -> Ethr.ok(n * 3)).value()).isEqualTo(6);
  }

  @Test
  void testFlatMapFailPropagates() {
    final Ethr<Integer> result = Ethr.<Integer>fail("error").flatMap(n -> Ethr.ok(n * 3));
    assertThat(result.isFail()).isTrue();
    assertThat(result.err()).isEqualTo(new Err.Message("error"));
  }

  @Test
  void testMapErr() {
    final Ethr<Integer> result = Ethr.<Integer>fail("original")
        .mapErr(_ -> new Err.Message("wrapped"));
    assertThat(result.err()).isEqualTo(new Err.Message("wrapped"));
  }

  @Test
  void testMapErrIgnoredOnOk() {
    final var result = Ethr.ok(42).mapErr(_ -> new Err.Message("ignored"));
    assertThat(result.value()).isEqualTo(42);
  }

  @Test
  void testOrElseOk() {
    assertThat(Ethr.ok(42).orElse(0)).isEqualTo(42);
  }

  @Test
  void testOrElseFail() {
    assertThat(Ethr.<Integer>fail("err").orElse(0)).isZero();
  }

  @Test
  void testOrElseThrowOk() {
    assertThat(Ethr.ok(42).orElseThrow()).isEqualTo(42);
  }

  @Test
  void testOrElseThrowFailureRethrows() {
    final var cause = new Impossible("oops");
    final var ethr = Ethr.fail(cause);
    assertThatThrownBy(ethr::orElseThrow).isSameAs(cause);
  }

  @Test
  void testOrElseThrowMessageThrowsNoSuchElement() {
    final Ethr<Void> ethr = Ethr.fail("bad");
    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(ethr::orElseThrow)
        .withMessage("bad");
  }

  @Test
  void testFoldOk() {
    final int result = Ethr.ok(42).fold(_ -> -1, n -> n + 1);
    assertThat(result).isEqualTo(43);
  }

  @Test
  void testFoldFail() {
    final int result = Ethr.<Integer>fail("err").fold(_ -> -1, n -> n + 1);
    assertThat(result).isEqualTo(-1);
  }

}
