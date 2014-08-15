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
  private static final String                  SUM_EXPR  = "1 + 2 + 3  + 4+ 5 + 6";

  @Test
  public void testSumExpression() {
    Optional<Double> result = evaluator.eval(SUM_EXPR);
    assertTrue(result.isPresent());
    assertTrue(String.format("expected 21 but was %s", result.get()), result
        .get().equals(21.0));
  }
}