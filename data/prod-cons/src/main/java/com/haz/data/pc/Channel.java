/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;

/**
 * @author hasnaer
 *
 */
public interface Channel {

  BlockingQueue<Optional<?>> getQueue();
  Producer getProducer();
  Consumer getConsumer();
  
}