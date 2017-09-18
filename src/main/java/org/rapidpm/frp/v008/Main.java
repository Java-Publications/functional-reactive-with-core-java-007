package org.rapidpm.frp.v008;

import java.util.Objects;
import java.util.function.Function;

import org.rapidpm.frp.memoizer.Memoizer;

/**
 *
 */
public class Main {


  @FunctionalInterface
  public interface TriFunction<T1, T2, T3, R> {
    R apply(T1 t1 , T2 t2 , T3 t3);

    default <V> TriFunction<T1, T2, T3, V> andThen(Function<? super R, ? extends V> after) {
      Objects.requireNonNull(after);
      return (T1 t1 , T2 t2 , T3 t3) -> after.apply(apply(t1 , t2 , t3));
    }
  }

  public static <A, B, C, R> TriFunction<A, B, C, R> memoize(TriFunction<A, B, C, R> f) {
    Function<A, Function<B, Function<C,R>>> cf
        = Memoizer.memoize(
        x -> Memoizer.memoize(
            y -> Memoizer.memoize(
                z -> f.apply(x , y , z))));
    return (a , b, c) -> cf.apply(a).apply(b).apply(c);
  }

}
