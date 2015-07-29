package org.reactivestreams.extensions.sequenced;

/**
 * Created by ruedi on 29/07/15.
 *
 * Additional proposal
 *
 * a further message marker interface allowing for implementation of generic last value caches.
 * A last value cache can be used in persistent message queues to replace plain message replay.
 * E.g. for market data, one likely aims to store last market prices by stock instead of a full history
 * of price events.
 *
 */
public interface KeyedMessage {

    Object getKey();
}
