/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hasnaer
 *
 */
public class InfixExprEvaluator {

  private static final String NUM = "NUM";
  private static final String ALPHA = "ALPHA";
  private static final String OP = "OP";
  private static final String GROUP = "GROUP";
  private static final String TOKEN = "TOKEN";
  private static final String REST = "REST";

  public static final Pattern tokenPattern = Pattern
      .compile("\\s*(?<TOKEN>((?<ALPHA>[a-zA-Z]+)|(?<NUM>[0-9]+)|(?<OP>\\+|\\-|\\*|/)|(?<GROUP>\\(|\\)|\\[|\\])))(?<REST>.*)?");

  private static boolean hasHigherPrecedence(String pOp1, String pOp2) {
    switch (pOp1) {
      case "*":
      case "/":
        return pOp2.matches("+|-");
    }
    return false;
  }

  private static int compute(Integer pLeft, String pOperator, Integer pRight) {
    switch (pOperator) {
      case "+":
        return pLeft + pRight;
      case "-":
        return pLeft - pRight;
      case "*":
        return pLeft * pRight;
      default:
        return pLeft / pRight;
    }
  }

  public static Optional<Integer> eval(String pExpression) {
    Stack<Integer> values = new Stack<>();
    Stack<String> operators = new Stack<String>() {
      @Override
      public String push(String pItem) {
        if (!isEmpty() && hasHigherPrecedence(pItem, peek())) {
          String op = pop();
          Integer right = values.pop();
          Integer left = values.pop();
          values.push(compute(left, op, right));
        }
        return super.push(pItem);
      }
    };

    Matcher matcher = tokenPattern.matcher(pExpression.trim());
    while (matcher.matches()) {
      String token = matcher.group(TOKEN);
      if (matcher.group(NUM) != null) {
        values.push(Integer.parseInt(token));
      } else if (matcher.group(OP) != null) {
        operators.push(token);
      }
      String rest = matcher.group(REST);
      if (rest == null) {
        break;
      }
      matcher = tokenPattern.matcher(rest);
    }

    while (!operators.isEmpty()) {
      String op = operators.pop();
      Integer right = values.pop();
      Integer left = values.pop();
      values.push(compute(left, op, right));
    }

    if (values.size() == 1) {
      return Optional.of(values.pop());
    }
    return Optional.empty();
  }

}