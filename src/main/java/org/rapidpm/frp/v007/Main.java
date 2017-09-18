package org.rapidpm.frp.v007;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.rapidpm.frp.memoizer.Memoizer;

/**
 *
 */
public class Main {

  private static final BiFunction<Integer, Integer, Integer> biFunctionA = (x , y) -> x * y;


  public static <A,B,R> BiFunction<A, B, R> memoize(BiFunction<A, B, R> f) {
    Function<A, Function<B, R>> cf
        = Memoizer.memoize(x -> Memoizer.memoize(y -> f.apply(x , y)));
    return (a , b) -> cf.apply(a).apply(b);
  }

  public static void main(String[] args) {
    final Integer a = memoize(biFunctionA).apply(2 , 3);
    final Integer b = Main.<Integer,Integer,Integer>memoize((x , y) -> x * y).apply(2 , 3);

    Main.<String, String, Integer>memoize((x,y) -> Integer.valueOf(a) * Integer.valueOf(b));
  }

}
