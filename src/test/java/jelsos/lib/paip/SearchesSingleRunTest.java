package jelsos.lib.paip;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import jelsos.lib.Opt;

class SearchesSingleRunTest {

  @Test
  void testBreadthFirstSearch() {
    assertThat(BreadthFirstSearchTest.search("a", "u")).isEqualTo(Opt.of("u"));
  }

  @Test
  void testDepthFirstSearch() {
    assertThat(DepthFirstSearchTest.search("a", "u")).isEqualTo(Opt.of("u"));
  }

}
