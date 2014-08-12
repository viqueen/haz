/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public class Context {

  private final Context parent;
  private final Map<String, Object> entries;

  public Context() {
    this(null);
  }

  public Context(Context pParent) {
    parent = pParent;
    entries = new HashMap<>();
  }

  public Optional<Context> parent() {
    return parent != null ? Optional.of(parent) : Optional.empty();
  }

  public void put(String pKey, Object pValue) {
    entries.put(pKey, pValue);
  }

  public Optional<?> get(String pKey) {
    Object data = entries.get(pKey);
    return data != null ? Optional.of(data) : Optional.empty();
  }
}