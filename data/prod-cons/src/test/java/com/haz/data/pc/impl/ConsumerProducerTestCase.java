/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.pc.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.haz.data.pc.Channel;

/**
 * @author hasnaer
 *
 */
public class ConsumerProducerTestCase {

  @Test
  public void testConsumerSingleProducer() throws Exception {
    Channel channel = new DataChannel() {
      {
        setConsumer(new SumConsumer(this));
        setProducer(new IntegerProducer(this, true, 0, 20, 1));
      }
    };
    channel.execute();
    assertEquals((20 * 21) / 2, ((SumConsumer) channel.consumer().get()).getSum());
  }

  class SumConsumer extends DataConsumer {

    private int sum = 0;

    SumConsumer(Channel pInputChannel) {
      super(pInputChannel);
    }

    @Override
    public boolean supports(Optional<?> pData) {
      return pData.isPresent() && pData.get() instanceof Integer;
    }

    @Override
    public void consume(Optional<?> pData) {
      sum += (Integer) pData.get();
    }

    @Override
    public void setUp() {
    }

    @Override
    public void wrapUp() {
    }

    public int getSum() {
      return sum;
    }
  }

  class IntegerProducer extends DataProducer {

    final int start, end, increment;
    int current;

    IntegerProducer(
        Channel pOutputChannel,
        boolean pEOC,
        int pStart,
        int pEnd,
        int pIncrement) {
      super(pOutputChannel);
      start = pStart;
      end = pEnd;
      increment = pIncrement;
      current = start;
    }

    @Override
    public Stream<Optional<?>> offer() {
      int data = current;
      current += increment;      
      return Stream.of(Optional.of(data));
    }

    @Override
    public boolean canProduce() {
      return current <= end;
    }    

    @Override
    public void setUp() {
    }

    @Override
    public void wrapUp() {
    }

  }

}