package nomouse.learn.algo.heap;

import java.util.PriorityQueue;

/**
 * @author nomouse
 * @date 2021/9/17
 */
public class MidValueTest {

    public static int midValue(int[] left, int[] right) {

        PriorityQueue<Integer> queue =
            new PriorityQueue<>((a, b) -> b - a);


        return 0;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue =
            new PriorityQueue<>((a, b) -> b - a);

        queue.offer(1);

        Integer i = queue.peek();

    }
}
