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
public class DefaultCodec<T> implements Codec<T> {

  protected final Logger LOG = Logger.getLogger(getClass());

  private URI uri;
  private Class<T> type;
  private List<Binding> bindings;

  public DefaultCodec(URI pURI, Class<T> pType) {
    uri = pURI;
    type = pType;
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
    URI bindingURI = null;
    if (!pBinding.codec().isEmpty()) {
      bindingURI = URI.create(pBinding.codec());
    } else {
      bindingURI = Factory.create(pField.getType()).uris().findFirst().get();
    }
    bindings.add(new Binding(pField.getName(), bindingURI));
  }

  @Override
  public Optional<T> decode(DataInputStream pInput) throws IOException {
    try {
      T result = type.newInstance();
      for (Binding binding : bindings) {
        Codec<?> subCodec = Factory.get(binding.codecURI()).get();
        Field field = type.getDeclaredField(binding.name());
        field.setAccessible(true);
        field.set(result, subCodec.decode(pInput).get());
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

  @Override
  public Stream<URI> uris() {
    return Stream.of(uri);
  }

  public List<Binding> bindings() {
    return bindings;
  }
}