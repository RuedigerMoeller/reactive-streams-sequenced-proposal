package org.reactivestreams.extensions.sequenced;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

/**
 * Created by ruedi on 29/07/15.
 */
public interface SequencedSubscription extends Subscription {

    /**
     * request next n messages, starting with the given sequence. If sequence is <= 0,
     * start with the first available message
     *
     * @param startingSequence
     * @param n
     */
    void request(long startingSequence, long n);

}
