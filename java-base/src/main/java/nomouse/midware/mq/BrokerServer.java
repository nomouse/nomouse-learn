package nomouse.midware.mq;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author nomouse
 * @date 2021/10/10
 */
public class BrokerServer {

    private LinkedBlockingQueue<String> queue =
        new LinkedBlockingQueue<>(16);

    public boolean publish(String topic, String body) {
        queue.add(body);
        return true;
    }

    public String consume(String topic) {
        String body = queue.poll();
        return body;
    }

}
