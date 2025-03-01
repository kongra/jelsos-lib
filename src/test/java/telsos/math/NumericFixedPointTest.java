package telsos.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import telsos.typeclass.Enum;
import telsos.typeclass.Num;
import telsos.typeclass.Ord;
import telsos.typeclass.instances.BigDecimalInstances;

class NumericFixedPointTest {

  static class BigDecimalFixedPoint implements NumericFixedPoint<BigDecimal> {

    final Num<BigDecimal> num;

    BigDecimalFixedPoint(MathContext mc) {
      num = BigDecimalInstances.numWith(mc);
    }

    @Override
    public Num<BigDecimal> num() {
      return num;
    }

    @Override
    public Ord<BigDecimal> ord() {
      return BigDecimalInstances.ORD;
    }

    @Override
    public Enum<BigDecimal> enm() {
      return BigDecimalInstances.ENUM;
    }
  }

  @SuppressWarnings("static-method")
  @Test
  void testEvalUnaryOperatorOfTT() {
    final var mc = new MathContext(100, RoundingMode.HALF_EVEN);
    final var fixedPoint = new BigDecimalFixedPoint(mc);
    final var enm = fixedPoint.enm();
    final HeronSqrt<BigDecimal> sqrt = () -> fixedPoint;
    final var value = sqrt.eval(enm.fromInt(2).orElseThrow());

    assertThat(value.toString()).startsWith("1.4142135623730950488016896");
  }

}
