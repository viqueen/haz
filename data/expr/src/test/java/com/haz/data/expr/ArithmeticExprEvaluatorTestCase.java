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
  private static final String                  SUM_EXPR  = "3 * 2 + 4";

  @Test
  public void testSumExpression() {
    Optional<Double> result = evaluator.eval(SUM_EXPR);
    assertTrue(result.isPresent());
    assertTrue(String.format("expected 21 but was %s", result.get()), result
        .get().equals(10.0));
  }
}