package org.rapidpm.frp.v001;

import java.util.function.Function;

/**
 *
 */
public class Main {



  private static Function<Integer, Integer> squareFunction = x -> {
    System.out.println("In function");
    return x * x;
  };

  public static void main(String[] args) {

    final Integer a = squareFunction.apply(2);
    final Integer b = squareFunction.apply(2);
  }

}
