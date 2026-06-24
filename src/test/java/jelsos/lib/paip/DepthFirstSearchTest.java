package jelsos.lib.paip;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;

import org.junit.jupiter.api.Test;

import jelsos.lib.Opt;

public class DepthFirstSearchTest {

  public static Opt<String> search(String start, String goal) {
    return DepthFirstSearch.of(BreadthFirstSearchTest::children, goal::equals)
        .search(start);
  }

  public static Opt<String> searchWithArray(String start, String goal) {
    return DepthFirstSearch.of(BreadthFirstSearchTest::children, goal::equals)
        .search(start, ArrayDeque::new);
  }

  public static Opt<String> search1(String start, String goal) {
    return DepthFirstSearch.of(BreadthFirstSearchTest::children1, goal::equals)
        .search(start);
  }

  public static Opt<String> searchWithArray1(String start, String goal) {
    return DepthFirstSearch.of(BreadthFirstSearchTest::children1, goal::equals)
        .search(start, ArrayDeque::new);
  }

  @SuppressWarnings("static-method")
  @Test
  final void testSearch() {
    assertThat(search("a", "a")).isEqualTo(Opt.of("a"));
    assertThat(search("a", "k")).isEqualTo(Opt.of("k"));
    assertThat(search("k", "k")).isEqualTo(Opt.of("k"));

    assertThat(search("d", "a")).isEqualTo(Opt.empty());
    assertThat(search("www", "a")).isEqualTo(Opt.empty());
    assertThat(search("a", "www")).isEqualTo(Opt.empty());
  }

  @SuppressWarnings("static-method")
  @Test
  final void testSearch1() {
    assertThat(search1("a", "a")).isEqualTo(Opt.of("a"));
    assertThat(search1("a", "k")).isEqualTo(Opt.of("k"));
    assertThat(search1("k", "k")).isEqualTo(Opt.of("k"));

    assertThat(search1("d", "a")).isEqualTo(Opt.empty());
    assertThat(search1("www", "a")).isEqualTo(Opt.empty());
    assertThat(search1("a", "www")).isEqualTo(Opt.empty());
  }

  @SuppressWarnings("static-method")
  @Test
  final void testSearchWithArray() {
    assertThat(searchWithArray("a", "a")).isEqualTo(Opt.of("a"));
    assertThat(searchWithArray("a", "k")).isEqualTo(Opt.of("k"));
    assertThat(searchWithArray("k", "k")).isEqualTo(Opt.of("k"));

    assertThat(searchWithArray("d", "a")).isEqualTo(Opt.empty());
    assertThat(searchWithArray("www", "a")).isEqualTo(Opt.empty());
    assertThat(searchWithArray("a", "www")).isEqualTo(Opt.empty());
  }

  @SuppressWarnings("static-method")
  @Test
  final void testSearchWithArray1() {
    assertThat(searchWithArray1("a", "a")).isEqualTo(Opt.of("a"));
    assertThat(searchWithArray1("a", "k")).isEqualTo(Opt.of("k"));
    assertThat(searchWithArray1("k", "k")).isEqualTo(Opt.of("k"));

    assertThat(searchWithArray1("d", "a")).isEqualTo(Opt.empty());
    assertThat(searchWithArray1("www", "a")).isEqualTo(Opt.empty());
    assertThat(searchWithArray1("a", "www")).isEqualTo(Opt.empty());
  }

}
