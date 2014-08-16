/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec.binding;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.haz.data.codec.Factory;
import com.haz.data.codec.annotation.BindType;

/**
 * @author hasnaer
 *
 */
public class SelectionBinding extends AbstractBinding {

  public final List<Class<?>> types;
  public final String         keyExpr;

  public SelectionBinding(Field pField, Class<?>[] pTypes, String pKeyExpr) {
    super(pField);
    types = Arrays.asList(pTypes);
    keyExpr = pKeyExpr;
  }

  @Override
  public final Type type() {
    return Type.SELECTION;
  }

  /**
   * @param pExpandExpression
   * @return
   */
  public String resolveCodecURI(String pExpr) {
    return Factory
        .create(types.stream().filter(type -> 
        ((BindType)type.getDeclaredAnnotation(BindType.class)).key().equals(pExpr))
        .findFirst().get()).uris()
        .findFirst().get();
  }

}
