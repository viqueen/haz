/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.net.URI;

/**
 * @author hasnaer
 *
 */
public class Binding {
  private String name;
  private URI codecURI;

  public Binding(String pName, URI pCodecURI) {
    name = pName;
    codecURI = pCodecURI;
  }

  public String name() {
    return name;
  }

  public URI codecURI() {
    return codecURI;
  }
}