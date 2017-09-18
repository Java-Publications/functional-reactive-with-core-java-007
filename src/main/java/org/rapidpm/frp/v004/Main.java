package org.rapidpm.frp.v004;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.rapidpm.frp.memoizer.Memoizer;

/**
 *
 */
public class Main {

  private static final BiFunction<Integer, Integer, Integer> biFunctionA = (x , y) -> x * y;

  public static Function<Integer, Function<Integer, Integer>> create(
      BiFunction<Integer, Integer, Integer> biFunction) {
    return Memoizer.memoize(x -> Memoizer.memoize(y -> biFunction.apply(x , y)));
  }


  public static void main(String[] args) {

    final Function<Integer, Function<Integer, Integer>> function = create(biFunctionA);
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));

  }
}
