# reactive-streams-sequenced-proposal

##Motivation of adding sequenced semantics
- Subscription.request(..) becomes idempotent, which is important for distributed streaming as the original spec's credit-alike flow control creates distributed state (error prone)
- as there is talk back from subscriber to publisher anyway, we can add ACK semantics allowing for guaranteed delivery, storage, retransmission, etc.

These extensions enable implementation of a sequencing wrapper, so RS 1.0 streams can be made sequenced using an adapter implementation adding sequencing support under the hood.

In case of acknowledged/guaranteed delivery or store-and-forward MQ patterns, clients are likely to interact with the sequenced stream API directly.

Basically adds

```java
public interface SequencedMessage {
    long sequence();
    default Object payload() {
        return this;
    }
}
```
and

```java
public interface SequencedSubscription extends Subscription {

    /**
     * request next n messages, starting with the given sequence. If sequence is <= 0,
     * start with the first available message. Sequences are expected to be strictly ordered and increment by 1,
     * all sequences are expected to be > 0.
     *
     * Note this message implicitely acknowledges receive of 'startingSequence'. This means a buffering publisher
     * could delete buffered messages with a sequence of <= 'startingSequence'
     *
     * @param startingSequence - last received/known message sequence.
     * @param n - number of messages that can be send starting with given sequence
     */
    void request(long startingSequence, long n);

}
```

Bonus:

```java
public interface KeyedMessage {
    Object getKey();
}
```

to enable last value caches (persistent/non persistent). Can be used for adaptive rate limiting ("netting"), reduced replay volume.
