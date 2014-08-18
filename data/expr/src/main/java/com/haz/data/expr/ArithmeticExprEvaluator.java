/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author hasnaer
 *
 */
public class ArithmeticExprEvaluator extends InfixExpressionEvaluator<Double> {

  private static final String  NUM          = "NUM";
  private static final String  OP           = "OP";
  private static final String  GROUP        = "GROUP";
  private static final String  TOKEN        = "TOKEN";
  private static final String  REST         = "REST";

  private static final Pattern tokenPattern = Pattern
                                                .compile("\\s*(?<TOKEN>((?<ALPHA>[a-zA-Z]+)"
                                                    + "|(?<NUM>[0-9]+)"
                                                    + "|(?<OP>\\+|\\-|\\*|/)"
                                                    + "|(?<GROUP>\\(|\\)|\\[|\\])))"
                                                    + "(?<REST>.*)?");

  @Override
  protected Optional<Pair<Token, String>> nextToken(String pExpression) {
    Matcher matcher = tokenPattern.matcher(pExpression);
    if (matcher.matches()) {
      String token = matcher.group(TOKEN);
      String rest = matcher.group(REST);
      if (matcher.group(NUM) != null) {
        return Optional.of(ImmutablePair.of(
            new Value<Double>(Double.parseDouble(token)), rest));
      } else if (matcher.group(OP) != null) {
        return Optional.of(ImmutablePair.of(
            ArithmeticOperator.fromString(token), rest));
      } else if (matcher.group(GROUP) != null) {
        return Optional.of(ImmutablePair.of(
            ArithmeticGrouping.fromString(token), rest));
      }
    }
    return Optional.empty();
  }

  enum ArithmeticGrouping implements Grouping {
    LEFT_PAREN(3, true), RIGHT_PAREN(3, false);
    final int     precedence;
    final boolean opening;

    ArithmeticGrouping(int pPrecedence, boolean pOpening) {
      precedence = pPrecedence;
      opening = pOpening;
    }

    @Override
    public int precedence() {
      return precedence;
    }

    @Override
    public boolean opening() {
      return opening;
    }

    static ArithmeticGrouping fromString(String pExpr) {
      switch (pExpr) {
        case "(":
          return LEFT_PAREN;
        case ")":
        default:
          return RIGHT_PAREN;
      }
    }
  }

  enum ArithmeticOperator implements BinaryOperator<Double> {
    ADD(1) {
      @Override
      public Double apply(Double pLeft, Double pRight) {
        return pLeft + pRight;
      }
    },
    SUBTRACT(1) {
      @Override
      public Double apply(Double pLeft, Double pRight) {
        return pLeft - pRight;
      }
    },
    MULTIPLY(2) {
      @Override
      public Double apply(Double pLeft, Double pRight) {
        return pLeft * pRight;
      }
    },
    DIVIDE(2) {
      @Override
      public Double apply(Double pLeft, Double pRight) {
        return pLeft / pRight;
      }
    };

    ArithmeticOperator(int pPrecedence) {
      precedence = pPrecedence;
    }

    final int precedence;

    @Override
    public int precedence() {
      return precedence;
    }

    public static ArithmeticOperator fromString(String pExpr) {
      switch (pExpr) {
        case "+":
          return ADD;
        case "-":
          return SUBTRACT;
        case "*":
          return MULTIPLY;
        case "/":
        default:
          return DIVIDE;
      }
    }
  }
}