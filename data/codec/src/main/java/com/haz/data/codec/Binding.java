/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;


/**
 * @author hasnaer
 *
 */
public class Binding {
  // public final, just to avoid get/set , guaranteed not to be reset
  public final String name;
  public final String codecURI;
  public final String count;
  public Binding(String pName, String pCodecURI, String pCount) {
    name = pName;
    codecURI = pCodecURI;
    count = pCount;
  }

}