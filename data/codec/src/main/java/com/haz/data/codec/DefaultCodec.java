/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class DefaultCodec<T> extends AbstractCodec<T> {

  private List<Binding> bindings;

  public DefaultCodec(String pURI, Class<T> pType) {
    super(pURI, pType);
    bindings = new ArrayList<>();
    loadBindings(type);
  }

  private void loadBindings(Class<?> pType) {
    if (pType != null) {
      loadBindings(pType.getSuperclass());
      for (Field field : pType.getDeclaredFields()) {
        Annotation binding = field.getDeclaredAnnotation(Bind.class);
        if (binding != null) {
          loadBinding((Bind) binding, field);
        }
      }
    }
  }

  private void loadBinding(Bind pBinding, Field pField) {
    String bindingURI = null;
    if (!pBinding.codec().isEmpty()) {
      bindingURI = pBinding.codec();
    } else {
      bindingURI = Factory.create(pField.getType()).uris().findFirst().get();
    }
    bindings.add(new Binding(pField, bindingURI, pBinding.count()));
  }

  @Override
  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException {
    try {
      T result = type.newInstance();
      for (Binding binding : bindings) {
        Codec<?> bindingCodec = Factory.get(binding.codecURI).get();
        Field field = binding.field;
        if (field.getType().isArray()) {
          pContext.put(String.format("%s.length", binding.codecURI),
              binding.count);
        }
        field.setAccessible(true);
        Object decoded = bindingCodec.decode(pInput, pContext).get();
        pContext.put(field.getName(), decoded);
        field.set(result, decoded);
      }
      return Optional.of(result);
    } catch (InstantiationException | IllegalAccessException
        e) {
      LOG.error(e.getMessage(), e);
    }
    return Optional.empty();
  }

  @Override
  public void encode(T pData, DataOutputStream pOutput) throws IOException {

  }

  public List<Binding> bindings() {
    return bindings;
  }
}