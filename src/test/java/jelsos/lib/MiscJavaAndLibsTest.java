package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

class MiscJavaAndLibsTest {

  @Test
  void testNaN2IntConversion() {
    var x = Math.sqrt(-5);
    assertThat(x).isNaN();

    var n = (int) x;
    assertThat(n).isZero();
  }

  @Test
  void testRandomIntsGeneration() {
    var random = new SecureRandom();
    var bound = 100;
    var n = random.nextInt(bound);
    assertThat(n).isBetween(0, bound - 1);
  }

}
