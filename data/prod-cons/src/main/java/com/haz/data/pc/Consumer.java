/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc;

import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public interface Consumer extends Instance {

  @Override
  default void run() {
    setUp();
    try {
      for (Optional<?> data = fetch(); supports(data); data = fetch()) {
        consume(data);
      }
    }
    catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
    wrapUp();
  }

  public void consume(Optional<?> pData);

  public boolean supports(Optional<?> pData);

  default Optional<?> fetch() throws InterruptedException {
    return getInputChannel().getQueue().take();
  }

  Channel getInputChannel();
}