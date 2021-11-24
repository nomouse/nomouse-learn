package nomouse.midware.mq;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class ConsumerClient {

    private BrokerServer server;

    public ConsumerClient(BrokerServer server) {
        this.server = server;
    }

    public void consume(String topic) {
        System.out.println("消费线程启动");

        while (true) {
            System.out.println("正从队列获取数据...");
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String body = server.consume(topic);
            if (body != null) {
                System.out.println(
                    "|thread=" + Thread.currentThread().getName()
                        + "|topic=" + topic
                        + "|body=" + body);
            }
        }
    }

}
