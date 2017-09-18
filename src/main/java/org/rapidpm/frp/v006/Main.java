package org.rapidpm.frp.v006;

import java.util.function.BiFunction;
import java.util.function.Function;

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

//  public static BiFunction<Integer, Integer, Integer> memoize(BiFunction<Integer, Integer, Integer> f){
//    return transform(create(biFunctionA));
//  }

  public static BiFunction<Integer, Integer, Integer> memoize(BiFunction<Integer, Integer, Integer> f){
    Function<Integer, Function<Integer, Integer>> cf
        = Memoizer.memoize(x -> Memoizer.memoize(y -> f.apply(x , y)));
    return (a,b) -> cf.apply(a).apply(b);
  }

  public static void main(String[] args) {
    final Integer a = transform(create(biFunctionA)).apply(2 , 3);
    final Integer b = memoize(biFunctionA).apply(2 , 3);
  }
}
