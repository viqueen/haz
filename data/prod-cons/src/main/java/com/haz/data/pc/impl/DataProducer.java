/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc.impl;

import com.haz.data.pc.Channel;
import com.haz.data.pc.Producer;

/**
 * @author hasnaer
 *
 */
public abstract class DataProducer implements Producer {
  private Channel outputChannel;

  public DataProducer(Channel pOutputChannel) {
    outputChannel = pOutputChannel;
  }

  @Override
  public final Channel getOutputChannel() {
    return outputChannel;
  }
}
