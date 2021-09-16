package nomouse.algo.sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author nomouse
 * @date 2021/9/8
 */
public class KLargestElementTest {

    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        System.out.println(
            findKthLargest(new int[] {3, 11, 4, 6, 9, 7, 5, 16, 1}, 3));
    }
}
