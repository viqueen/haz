/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * @author hasnaer
 *
 */
public interface Producer {

  final static Logger log = Logger.getLogger(Producer.class);

  default void produce() {
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
  }

  public Stream<Optional<?>> offer();

  public boolean canProduce();

  public Channel getOutputChannel();

}