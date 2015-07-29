# reactive-streams-sequenced-proposal

##Motivation
- request(..) messages become idempotent, which is important for distributed streaming as the original spec's credit-alike flow control creates distributed state (error prone)
- as there is talk back from subscriber to publisher anyway, we can add ACK semantics allowing for guaranteed delivery, storage, retransmission, etc.

Basically adds

```java
public interface SequencedMessage {
    long sequence();
    default Object payload() {
        return this;
    }
}
```

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
