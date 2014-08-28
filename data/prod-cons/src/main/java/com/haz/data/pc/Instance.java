/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc;

import org.apache.log4j.Logger;

/**
 * @author hasnaer
 *
 */
public interface Instance extends Runnable {
  public Logger log = Logger.getLogger(Instance.class);
  public void setUp();
  public void wrapUp();
}