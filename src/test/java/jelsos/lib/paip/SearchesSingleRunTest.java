package jelsos.lib.paip;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class SearchesSingleRunTest {

  @Test
  void testBreadthFirstSearch() {
    assertThat(BreadthFirstSearchTest.search("a", "u")).isEqualTo(Optional.of("u"));
  }

  @Test
  void testDepthFirstSearch() {
    assertThat(DepthFirstSearchTest.search("a", "u")).isEqualTo(Optional.of("u"));
  }

}
