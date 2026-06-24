package jelsos.lib;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DynVarTest {

  static final ScopedValue<String> SCOPED_VALUE = O.nn(ScopedValue.newInstance());

  DynVar<String> dynVar = DynVar.of(SCOPED_VALUE);

  @Test
  void testExec() {
    assertThat(dynVar.get()).isEqualTo(Opt.empty());

    dynVar.exec("value-1", () -> {
      assertThat(dynVar.get().orElseThrow()).isEqualTo("value-1");

      dynVar.exec("value-2", () -> {
        assertThat(dynVar.get().orElseThrow()).isEqualTo("value-2");
      });

      assertThat(dynVar.get().orElseThrow()).isEqualTo("value-1");
    });
  }

  @Test
  void testEval() {
    assertThat(dynVar.get()).isEqualTo(Opt.empty());

    var result = dynVar.eval("value-1", () -> {
      assertThat(dynVar.get().orElseThrow()).isEqualTo("value-1");

      return dynVar.eval("value-2", () -> {
        assertThat(dynVar.get().orElseThrow()).isEqualTo("value-2");
        return "result";
      });

    });

    assertThat(result).isEqualTo("result");
  }

  @Test
  void testGet() {
    assertThat(dynVar.get()).isEqualTo(Opt.empty());
    assertThat(dynVar.get("default-value")).isEqualTo("default-value");
    assertThat(dynVar.get(() -> "supplied-default-value"))
        .isEqualTo("supplied-default-value");
  }

}
