/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

import java.util.Optional;
import java.util.Stack;

import org.apache.commons.lang3.tuple.Pair;

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
        if (!isEmpty() && !hasHigherPrecedence(pItem, peek())) {
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