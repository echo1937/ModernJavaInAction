package modernjavainaction.chap17;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;

public class Main {

    public static void main(String[] args) {
        Publisher<TempInfo> publisher = getTemperatures();
        publisher.subscribe(new TempSubscriber());
    }

    private static Publisher<TempInfo> getTemperatures() {

        return new Publisher<>() {
            @Override
            public void subscribe(Flow.Subscriber<? super TempInfo> subscriber) {
                subscriber.onSubscribe(new TempSubscription(subscriber, "New York"));
            }
        };

    }

}
