/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * @author hasnaer
 *
 */
abstract class AbstractCodec<T> implements Codec<T> {

  protected final Logger LOG = Logger.getLogger(getClass());

  protected final String uri;
  protected final Class<T> type;

  public AbstractCodec(String pUri, Class<T> pType) {
    uri = pUri;
    type = pType;
  }

  @Override
  public Stream<String> uris() {
    return Stream.of(uri);
  }

//yuk ugly, must extract this into its own resolver
 protected static String expandExpression(String pExpr, Context pContext) {
   Pattern variable = Pattern.compile("(?<VAR>\\$(?<NAME>[a-zA-Z]+))");
   Matcher matcher = variable.matcher(pExpr);
   StringBuffer buffer = new StringBuffer();
   while (matcher.find()) {
     matcher.appendReplacement(buffer, pContext.get(matcher.group("NAME"))
         .get().toString());
   }
   matcher.appendTail(buffer);
   return buffer.toString();
 }
}
