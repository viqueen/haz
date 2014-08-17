/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec.binding;

import java.lang.reflect.Field;

/**
 * @author hasnaer
 *
 */
public class SimpleBinding extends AbstractBinding {

  public final String codecURI;
  public final String count;
  public final String subCodecURI;
  
  public SimpleBinding (Field pField, String pCodecURI, String pCount, String pSubCodecURI) {
    super(pField);
    codecURI = pCodecURI;
    count = pCount;
    subCodecURI = pSubCodecURI;
  }
  
  @Override
  public final Type type() {
    return Type.SIMPLE;
  }
}
