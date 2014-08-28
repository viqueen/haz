/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author hasnaer
 *
 */
public interface Producer extends Instance {

  @Override
  default void run() {
    while (canProduce()) {
      offer().parallel().forEach(option -> {
        try {
          getOutputChannel().getQueue().put(option);
        }
        catch (Exception e) {
          log.error(e.getMessage(), e);
        }
      });
    }
    getOutputChannel().triggerEOC();
  }

  public Stream<Optional<?>> offer();

  public boolean canProduce();

  public Channel getOutputChannel();

}