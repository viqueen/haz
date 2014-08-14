/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.Stack;

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
        if (!isEmpty() && hasHigherPrecedence(pItem, peek())) {
          T right = values.pop();
          T left = values.pop();
          values.push(pop().apply(left, right));
        }
        return super.push(pItem);
      }

      private boolean hasHigherPrecedence(Operator<T> pOp1, Operator<T> pOp2) {
        return pOp1.precedence() > pOp2.precedence();
      }
    };

    Optional<Entry<Token, String>> nextToken = nextToken(pExpression);
    while (nextToken.isPresent()) {
      Entry<Token, String> token = nextToken.get();
      if (token.getKey().isOperator()) {
        operators.push((Operator<T>) token.getKey());
      } else if (token.getKey().isValue()) {
        values.push(((Value<T>) token.getKey()).value);
      }
      nextToken = nextToken(token.getValue());
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
  protected abstract Optional<Entry<Token, String>> nextToken(String pExpression);

}