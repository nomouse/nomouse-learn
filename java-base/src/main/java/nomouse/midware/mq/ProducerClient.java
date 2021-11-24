package nomouse.midware.mq;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class ProducerClient {

    private BrokerServer server;

    public ProducerClient(BrokerServer server) {
        this.server = server;
    }

    public boolean publish(String topic, String body) {
        boolean is = server.publish(topic, body);
        System.out.println("正在生产数据...");
        return is;
    }

}
