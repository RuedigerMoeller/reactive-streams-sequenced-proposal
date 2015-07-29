package org.reactivestreams.extensions.sequenced;

import org.reactivestreams.Subscriber;

/**
 * Created by ruedi on 29/07/15.
 */
public interface SequencedSubscriber<T extends SequencedMessage> extends Subscriber {

//    void onSubscribe(SequencedSubscription s); actually would require change of rs 1.0 spec: Subscriber<T extends Subscription>
//    go with a downcast in impls for now

}
