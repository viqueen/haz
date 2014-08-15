/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

/**
 * @author hasnaer
 *
 */
public class ArithmeticExprEvaluatorTestCase {

  private static final ArithmeticExprEvaluator evaluator = new ArithmeticExprEvaluator();
  private static final String                  EXPR_ONE  = "3 * 2 + 4 * 2";
  private static final String                  EXPR_TWO  = "3 * (4 + 1)";

  @Test
  public void testSumExpression() {
    Optional<Double> resultOne = evaluator.eval(EXPR_ONE);
    assertTrue(resultOne.isPresent());
    assertTrue(String.format("expected 21 but was %s", resultOne.get()), resultOne
        .get().equals(14.0));

    Optional<Double> resultTwo = evaluator.eval(EXPR_TWO);
    assertTrue(resultTwo.isPresent());
    assertTrue(String.format("expected 21 but was %s", resultTwo.get()), resultTwo
        .get().equals(15.0));

  }
}