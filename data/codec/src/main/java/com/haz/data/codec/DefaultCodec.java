/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.haz.data.codec.annotation.Bind;
import com.haz.data.codec.annotation.BindSelect;
import com.haz.data.codec.binding.Binding;
import com.haz.data.codec.binding.SelectionBinding;
import com.haz.data.codec.binding.SimpleBinding;

/**
 * @author hasnaer
 *
 */
public class DefaultCodec<T> extends AbstractCodec<T> {

  private List<Binding> bindings;

  public DefaultCodec(String pURI, Class<T> pType) {
    super(pURI, pType);
    bindings = new ArrayList<>();
    loadBindings(type, new HashMap<>());
  }

  @SuppressWarnings("rawtypes")
  private void loadBindings(Class<?> pType, Map<String, Class> pGenerics) {
    if (pType != null && !pType.equals(Object.class)) {
      if (pType.getGenericSuperclass() instanceof ParameterizedType) {
        TypeVariable[] typeVariables = pType.getSuperclass()
            .getTypeParameters();
        Type[] actualTypes = ((ParameterizedType) pType.getGenericSuperclass())
            .getActualTypeArguments();
        for (int i = 0; i < typeVariables.length; i++) {
          pGenerics.put(typeVariables[i].getName(), (Class) actualTypes[i]);
        }
      }
      loadBindings(pType.getSuperclass(), pGenerics);
      for (Field field : pType.getDeclaredFields()) {
        Annotation binding = field.getDeclaredAnnotation(Bind.class);
        if (binding != null) {
          loadBinding((Bind) binding, field, pGenerics);
        }
        binding = field.getDeclaredAnnotation(BindSelect.class);
        if (binding != null) {
          loadBinding((BindSelect) binding, field, pGenerics);
        }
      }
    }
  }

  @SuppressWarnings("rawtypes")
  private void loadBinding(Bind pBinding, Field pField,
      Map<String, Class> pGenerics) {
    String bindingURI = null;
    if (!pBinding.codec().isEmpty()) {
      bindingURI = pBinding.codec();
    } else {
      Class<?> c = pGenerics.getOrDefault(
          pField.getGenericType().getTypeName(), pField.getType());
      bindingURI = Factory.create(c).uris().findFirst().get();
    }
    bindings.add(new SimpleBinding(pField, bindingURI, pBinding.count(), pBinding.subCodec()));
  }

  @SuppressWarnings("rawtypes")
  private void loadBinding(BindSelect pBinding, Field pField,
      Map<String, Class> pGenerics) {
    bindings.add(new SelectionBinding(pField, pBinding.types(), pBinding.keyExpr()));
  }

  @Override
  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException {
    try {
      T result = type.newInstance();
      for (Binding binding : bindings) {
        String codecURI = "";
        String count = "";
        String subCodec = "";
        Field field = binding.field();
        switch (binding.type()) {
          case SIMPLE:
            SimpleBinding simple = (SimpleBinding) binding;
            codecURI = simple.codecURI;
            count = simple.count;
            subCodec = simple.subCodecURI;
            break;
          case SELECTION:
            SelectionBinding selection = (SelectionBinding) binding;
            codecURI = selection.resolveCodecURI (expandExpression(selection.keyExpr, pContext));
            break;
        }
        Codec<?> bindingCodec = Factory.get(codecURI).get();        
        if (field.getType().isArray()) {
          pContext.put(String.format("%s.length", codecURI),
              count);
          if (!subCodec.isEmpty()) {
            pContext.put(String.format("%s.subCodec", codecURI),
                Factory.get(subCodec).get());
          }
        }
        field.setAccessible(true);
        Object decoded = bindingCodec.decode(pInput, pContext).get();
        pContext.put(field.getName(), decoded);
        field.set(result, decoded);
      }
      return Optional.of(result);
    } catch (InstantiationException | IllegalAccessException e) {
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