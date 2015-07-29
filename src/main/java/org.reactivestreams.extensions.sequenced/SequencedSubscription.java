package org.reactivestreams.extensions.sequenced;
import org.reactivestreams.Subscription;

/**
 * Created by ruedi on 29/07/15.
 *
 * Basically the only real extension (other interfaces just deal with type safety). A pragmatic approach
 * could reduce this extension API to 'SequencedSubscription' and 'SequencedMessage' (downside: weird behaviour
 * in case of mixing up sequenced and unsequenced API).
 *
 * Two things are achieved:
 *
 * - request(..) messages become idempotent, which is important for distributed streaming as the original spec implicitely
 *   creates distributed state (error prone)
 * - as we talk back from subscriber to publisher anyway, we can add ACK semantics allowing for guaranteed delivers, storage
 *   etc.
 */
public interface SequencedSubscription extends Subscription {

    /**
     * request next n messages, starting with the given sequence. If sequence is <= 0,
     * start with the first available message. Sequences are expected to be strictly ordered (no gaps > 1),
     * and > 0.
     *
     * Note this message implicitely acknowledges receive of 'startingSequence'. This means a buffering publisher
     * could delete buffered messages with a sequence of <= 'startingSequence'
     *
     * @param startingSequence - last received/known message sequence.
     * @param n - number of messages that can be send starting with given sequence
     */
    void request(long startingSequence, long n);

}
