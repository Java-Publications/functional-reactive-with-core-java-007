package org.rapidpm.frp.v003;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.rapidpm.frp.memoizer.Memoizer;

/**
 *
 */
public class Main {


  private static final BiFunction<Integer, Integer, Integer> biFunctionA = (x , y) -> x * y;

  private static final Function<Integer, Function<Integer, Integer>> biFunctionB = x -> y -> x * y;


  public static void main(String[] args) {

    final Integer a = biFunctionA.apply(2 , 2);
    final Integer b = biFunctionB.apply(2).apply(2);

    Function<Integer, Function<Integer,Integer>> memoizationFunction
        = Memoizer.memoize(x -> Memoizer.memoize(y -> x * y));

  }


}
