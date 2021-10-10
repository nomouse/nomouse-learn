package nomouse.mq;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class Consumer {

    public boolean consume(String topic, String body) {
        System.out.println(
            "|thread=" + Thread.currentThread().getName()
                + "|topic=" + topic
                + "|body=" + body);
        return true;
    }
}
