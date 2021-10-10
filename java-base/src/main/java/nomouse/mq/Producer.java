package nomouse.mq;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class Producer {

    private ArrayBlockingQueue<String> queue =
        new ArrayBlockingQueue<>(16);

    public boolean publish(String topic, String body) {
        return queue.add(body);
    }

    public void reg(String topic, Consumer consumer) {
        new Thread(() -> {
            try {
                String body = queue.take();

                consumer.consume(topic, body);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
