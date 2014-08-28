/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.haz.data.pc.Channel;
import com.haz.data.pc.Producer;

/**
 * @author hasnaer
 *
 */
public abstract class ProducerPool extends DataProducer {

  private final ExecutorService executor;
  protected final List<Producer> producers;

  public ProducerPool(Channel pOutputChannel) {
    super(pOutputChannel);
    executor = Executors.newCachedThreadPool();
    producers = new ArrayList<>();
  }

  @Override
  public void run() {
    setUp();
    try {
      executor.invokeAll(producers.stream().map(
          producer -> Executors.callable(producer)).collect(Collectors.toList()));
    }
    catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
    finally {
      getOutputChannel().triggerEOC();
    }
    wrapUp();
  }

  @Override
  public Stream<Optional<?>> offer() {
    return Stream.empty();
  }

  @Override
  public boolean canProduce() {
    return !executor.isTerminated();
  }

  @Override
  public boolean eoc() {
    return true;
  }
}
