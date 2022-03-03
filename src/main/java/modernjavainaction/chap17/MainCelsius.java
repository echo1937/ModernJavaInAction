package modernjavainaction.chap17;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;

public class MainCelsius {

    public static void main(String[] args) {
        Publisher<TempInfo> publisher = getCelsiusTemperatures("New York");
        publisher.subscribe(new TempSubscriber());
    }

    public static Publisher<TempInfo> getCelsiusTemperatures(String town) {

        return new Publisher<TempInfo>() {
            @Override
            public void subscribe(Flow.Subscriber<? super TempInfo> subscriber) {
                TempProcessor processor = new TempProcessor();
                processor.subscribe(subscriber);

                processor.onSubscribe(new TempSubscription(processor, town));
            }
        };
    }

}
