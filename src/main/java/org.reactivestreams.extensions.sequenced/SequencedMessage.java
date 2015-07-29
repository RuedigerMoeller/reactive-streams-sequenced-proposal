package org.reactivestreams.extensions.sequenced;

/**
 * Created by ruedi on 29/07/15.
 *
 * Marks a sequenced message, can be implemented as a wrapper or by directly implementing the interface
 *
 */
public interface SequencedMessage {

    /**
     * Sequences are expected to be growing
     *
     *
     * @return sequence of this message inside current stream.
     */
    long sequence();

    /**
     * unsure about this, having a setter would enable repackaging/resequencing of messages without
     * having to use a wrapper. On the other hand it makes messages mutable ..
     *
     * I tend to keep this out of the public interface and add an additional marker interface which might
     * be part of a concrete implementation.
     *
     * @param newSeq
     * @return
     */
//    long sequence(long newSeq);


    default Object payload() {
        return this;
    }

}
