/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.legacy.data.expr;

import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

import org.junit.Test;

import com.haz.legacy.data.expr.InfixExprEvaluator;

/**
 * @author hasnaer
 *
 */
public class InfixExprEvaluatorTestCase {

  private static final String EMPTY = "";
  private static final String ALPHA = "alPHa";
  private static final String NUM = "090234";
  private static final String[] OPERATORS = new String[] { "+", "-", "*", "/" };
  private static final String[] GROUPING = new String[] { "(", ")", "[", "]" };
  private static final String SUM_EXPR = "3 + 8 + 19";
  
  
  @Test
  public void testEmptyStringNotMatch() {
    Matcher matcher = InfixExprEvaluator.tokenPattern.matcher(EMPTY);
    assertTrue(!matcher.matches());
  }

  @Test
  public void testTokenPatternOnAlpha() {
    Matcher matcher = InfixExprEvaluator.tokenPattern.matcher(ALPHA);
    assertTrue(matcher.matches());
    assertEquals(ALPHA, matcher.group("TOKEN"));
    assertEquals(ALPHA, matcher.group("ALPHA"));
    assertEquals(null, matcher.group("NUM"));
    assertEquals(null, matcher.group("OP"));
    assertEquals(null, matcher.group("GROUP"));
  }

  
  @Test
  public void testTokenPatternOnNum() {
    Matcher matcher = InfixExprEvaluator.tokenPattern.matcher(NUM);
    assertTrue(matcher.matches());
    assertEquals(NUM, matcher.group("TOKEN"));
    assertEquals(NUM, matcher.group("NUM"));
    assertEquals(null, matcher.group("ALPHA"));
    assertEquals(null, matcher.group("OP"));
    assertEquals(null, matcher.group("GROUP"));
  }

  @Test
  public void testTokenPatternOnOperators() {
    for (String OP : OPERATORS) {
      Matcher matcher = InfixExprEvaluator.tokenPattern.matcher(OP);
      assertTrue(matcher.matches());
      assertEquals(OP, matcher.group("TOKEN"));
      assertEquals(OP, matcher.group("OP"));
      assertEquals(null, matcher.group("ALPHA"));
      assertEquals(null, matcher.group("NUM"));
      assertEquals(null, matcher.group("GROUP"));
    }
  }
  
  @Test
  public void testTokenPatternOnGroups() {
    for (String GROUP : GROUPING) {
      Matcher matcher = InfixExprEvaluator.tokenPattern.matcher(GROUP);
      assertTrue(matcher.matches());
      assertEquals(GROUP, matcher.group("TOKEN"));
      assertEquals(GROUP, matcher.group("GROUP"));
      assertEquals(null, matcher.group("ALPHA"));
      assertEquals(null, matcher.group("NUM"));
      assertEquals(null, matcher.group("OP"));
    }
  }
  
  @Test
  public void testEvalSum () {
    Optional<Integer> result = InfixExprEvaluator.eval(SUM_EXPR);
    assertTrue(result.isPresent());
    assertTrue(result.get().equals(30));
  }
}