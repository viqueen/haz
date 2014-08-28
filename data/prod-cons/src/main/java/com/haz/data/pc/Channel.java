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

  Optional<Producer> producer();

  Optional<Consumer> consumer();

  public void setConsumer(Consumer pConsumer);

  public void setProducer(Producer pProducer);

  public void execute() throws Exception;

  default Optional<?> EOC() {
    return Optional.empty();
  }
  
  default void triggerEOC() {
    getQueue().add(EOC());
  }
}