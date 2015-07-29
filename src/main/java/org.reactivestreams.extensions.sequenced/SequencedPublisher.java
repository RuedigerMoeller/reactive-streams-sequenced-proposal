package org.reactivestreams.extensions.sequenced;

import org.reactivestreams.Publisher;

/**
 * Created by ruedi on 29/07/15.
 */
public interface SequencedPublisher<T extends SequencedMessage> extends Publisher<T> {
}
