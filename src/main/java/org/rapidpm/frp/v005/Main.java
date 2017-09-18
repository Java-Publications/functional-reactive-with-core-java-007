package org.rapidpm.frp.v005;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.memoizer.Memoizer;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class Main {

  private static final BiFunction<Integer, Integer, Integer> biFunctionA = (x , y) -> x * y;

  public static Function<Integer, Function<Integer, Integer>> create(
      BiFunction<Integer, Integer, Integer> biFunction) {
    return Memoizer.memoize(x -> Memoizer.memoize(y -> biFunction.apply(x , y)));
  }

  public static BiFunction<Integer, Integer, Integer> transform(
      Function<Integer, Function<Integer, Integer>> function) {
    return (a,b) -> function.apply(a).apply(b);
  }


  public static void main(String[] args) {

    final Function<Integer, Function<Integer, Integer>> function = create(biFunctionA);
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));

    System.out.println("memoizationBiFunction = " + transform(function).apply(2 , 3));

  }
}
