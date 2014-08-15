/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import java.util.Optional;
import java.util.Stack;

import org.apache.commons.lang3.tuple.Pair;

import com.haz.data.expr.Token.Type;

/**
 * @author hasnaer
 *
 */
public abstract class InfixExpressionEvaluator<T> {

  @SuppressWarnings({ "unchecked", "serial" })
  public Optional<T> eval(String pExpression) {
    Stack<T> values = new Stack<>();
    Stack<Operator<T>> operators = new Stack<Operator<T>>() {
      @Override
      public Operator<T> push(Operator<T> pItem) {
        if (!isEmpty() && peek().type() != Type.GROUPING
            && pItem.precedence() < peek().precedence()) {
          T right = values.pop();
          T left = values.pop();
          values.push(pop().apply(left, right));
        }
        return super.push(pItem);
      }
    };

    Optional<Pair<Token, String>> nextToken = nextToken(pExpression);
    while (nextToken.isPresent()) {
      Pair<Token, String> token = nextToken.get();
      switch (token.getLeft().type()) {
        case VALUE:
          values.push(((Value<T>) token.getLeft()).value);
          break;
        case OPERATOR:
          operators.push((Operator<T>) token.getLeft());
          break;
        case GROUPING:
          Grouping grouping = (Grouping) token.getLeft();
          if (grouping.opening()) {
            operators.push(grouping);
          } else {
            Operator<T> op = operators.pop();
            while (op.type() != Type.GROUPING) {
              T right = values.pop();
              T left = values.pop();
              values.push(op.apply(left, right));
              op = operators.pop();
            }
          }
          break;
      }
      nextToken = nextToken(token.getRight());
    }

    while (!operators.isEmpty()) {
      T right = values.pop();
      T left = values.pop();
      values.push(operators.pop().apply(left, right));
    }

    if (values.size() == 1) {
      return Optional.of(values.pop());
    }

    return Optional.empty();
  }

  /**
   * 
   * @param pExpression
   *          expression to extract a token from
   * @return a pair with left as extracted token, and right as the rest of the
   *         input expression
   */
  protected abstract Optional<Pair<Token, String>> nextToken(String pExpression);

}