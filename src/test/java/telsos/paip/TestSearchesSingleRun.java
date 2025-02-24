package telsos.paip;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class TestSearchesSingleRun {

  @SuppressWarnings("static-method")
  @Test
  void testBreadthFirstSearch() {
    assertThat(TestBreadthFirstSearch.search("a", "u"))
        .isEqualTo(Optional.of("u"));
  }

  @SuppressWarnings("static-method")
  @Test
  void testDepthFirstSearch() {
    assertThat(TestDepthFirstSearch.search("a", "u"))
        .isEqualTo(Optional.of("u"));
  }

}
