package jelsos.lib.bench;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import jelsos.lib.paip.BreadthFirstSearchTest;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchBreadthFirstSearch {

  @Benchmark
  public static void benchaa(Blackhole blackhole) {
    var v = BreadthFirstSearchTest.search1("a", "a");
    blackhole.consume(v);
  }

  @Benchmark
  public static void benchau(Blackhole blackhole) {
    var v = BreadthFirstSearchTest.search1("a", "u");
    blackhole.consume(v);
  }

  @Benchmark
  public static void benchax(Blackhole blackhole) {
    var v = BreadthFirstSearchTest.search1("a", "x");
    blackhole.consume(v);
  }

}
