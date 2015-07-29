# reactive-streams-sequenced-proposal

##Motivation
- Subscribtion.request(..) pull messages become idempotent, which is important for distributed streaming as the original spec's credit-alike flow control creates distributed state (error prone)
- as there is talk back from subscriber to publisher anyway, we can add ACK semantics allowing for guaranteed delivery, storage, retransmission, etc.

These extension allow for implementing a sequencing wrapper, so strict RS 1.0 streams can be made sequenced for network transfer using an adapter implementation.

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

+ some type safety related subinterfaces of original API.
