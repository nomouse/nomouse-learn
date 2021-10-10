package nomouse.mq;

import java.util.Scanner;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class Test {

    public static void main(String[] args) {
        Producer producer = new Producer();

        Consumer consumer1 = new Consumer();
        producer.reg("to", consumer1);

        Consumer consumer2 = new Consumer();
        producer.reg("to", consumer2);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            String[] strArray = string.split(",");

            producer.publish(strArray[0], strArray[1]);
        }
    }
}
