package org.rapidpm.frp.v002;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 *
 */
public class Main {

  private static Function<Integer, Integer> squareFunction = x -> {
    System.out.println("In function");
    return x * x;
  };


  public static class Memoizer<T,U> {
    private final Map<T,U> memoizationCache = new ConcurrentHashMap<>();

    private Function<T, U> doMemoize(final Function<T, U> function) {
      return input -> memoizationCache.computeIfAbsent(input, function);
    }
    public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
      return new Memoizer<T, U>().doMemoize(function);
    }

  }


  public static void main(String[] args) {

    final Function<Integer, Integer> f = Memoizer.memoize(squareFunction);
    final Integer a = f.apply(2);
    final Integer b = f.apply(2);
  }

}
