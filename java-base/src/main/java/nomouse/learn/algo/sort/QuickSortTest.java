package nomouse.learn.algo.sort;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/9/17
 */
public class QuickSortTest {

    public void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = nums[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (nums[j] > p && i < j) {
                j--;
            }

            while (nums[i] < p && i < j) {
                i++;
            }

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        nums[i] = p;

        System.out.println(Arrays.toString(nums));

        sort(nums, start, i - 1);
        sort(nums, i + 1, end);

    }

    public static void main(String[] args) {
        int[] array = new int[] {8, 4, 10, 23, 6, 7, 1, 9, 5};
        new QuickSortTest().sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        Arrays.sort(array);
    }
}
