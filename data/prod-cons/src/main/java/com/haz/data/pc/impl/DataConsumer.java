/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc.impl;

import com.haz.data.pc.Channel;
import com.haz.data.pc.Consumer;

/**
 * @author hasnaer
 *
 */
abstract class DataConsumer implements Consumer {

  private Channel inputChannel;

  public DataConsumer(Channel pInputChannel) {
    inputChannel = pInputChannel;
  }

  @Override
  public final Channel getInputChannel() {
    return inputChannel;
  }

}
