/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

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
    loadBindings();
  }

  private void loadBindings() {
    // TODO: handle inherited fields first
    // bindings.addAll(resolveSuperClass.bindings())
    for (Field field : type.getDeclaredFields()) {
      Annotation binding = field.getDeclaredAnnotation(Bind.class);
      if (binding != null) {
        loadBinding((Bind) binding, field);
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
    bindings.add(new Binding(pField.getName(), bindingURI, pBinding.count()));
  }

  @Override
  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException {
    try {
      T result = type.newInstance();
      for (Binding binding : bindings) {
        Codec<?> bindingCodec = Factory.get(binding.codecURI).get();
        Field field = type.getDeclaredField(binding.name);
        if (field.getType().isArray()) {
          pContext.put(String.format("%s.length", binding.codecURI),
              binding.count);
        }
        field.setAccessible(true);
        field.set(result, bindingCodec.decode(pInput, pContext).get());
      }
      return Optional.of(result);
    } catch (InstantiationException | IllegalAccessException
        | NoSuchFieldException e) {
      LOG.error(e.getMessage(), e);
      e.printStackTrace();
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