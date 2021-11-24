package nomouse.midware.mq;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nomouse
 * @date 2021/9/16
 * <p>
 * 自己实现一个mq
 */
public class MessageQueueTest {

    public static void main(String[] args) {
        String topic = "to";
        BrokerServer server = new BrokerServer();

        ProducerClient producer = new ProducerClient(server);

        ConsumerClient consumer1 = new ConsumerClient(server);

        ConsumerClient consumer2 = new ConsumerClient(server);

        ExecutorService executor =
            new ThreadPoolExecutor(2,
                4, 1000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        executor.execute(() -> consumer1.consume(topic));
        executor.execute(() -> consumer2.consume(topic));
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            String[] strArray = string.split(",");

            producer.publish(strArray[0], strArray[1]);
        }
    }
}
