/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc.impl;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.haz.data.pc.Channel;
import com.haz.data.pc.Consumer;
import com.haz.data.pc.Producer;

/**
 * @author hasnaer
 *
 */
public class DataChannel implements Channel {

  protected final Logger log = Logger.getLogger(getClass());
  private final BlockingQueue<Optional<?>> queue;
  private final ExecutorService executor;
  private Consumer consumer;
  private Producer producer;

  public DataChannel() {
    queue = new LinkedBlockingDeque<>();
    executor = Executors.newCachedThreadPool();
  }

  @Override
  public final BlockingQueue<Optional<?>> getQueue() {
    return queue;
  }

  @Override
  public final Optional<Producer> producer() {
    return Optional.ofNullable(producer);
  }

  @Override
  public final void setProducer(Producer pProducer) {
    producer = pProducer;
  }

  @Override
  public final Optional<Consumer> consumer() {
    return Optional.ofNullable(consumer);
  }

  @Override
  public void setConsumer(Consumer pConsumer) {
    consumer = pConsumer;
  }

  @Override
  public final void execute() throws Exception {
    if (consumer != null && producer != null) {
      log.debug("open channel");
      executor.invokeAll(Stream.of(consumer, producer).map(
          task -> Executors.callable(task)).collect(Collectors.toList()));
      log.debug("close channel");
    }
  }
}