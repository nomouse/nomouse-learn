package nomouse.algo.array;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/9/20
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 */
public class RemoveElement {

    public void removeElement(int[] nums, int target) {
        // [-1,0,3,9,5,9,12]
        // 双指针
        // 1. l = 0
        // 2. l向右移动直到找到下标等于target
        // 3. r = l, l不动，r向右移动直到找到下标不等于target
        // 4. nums[l] = nums[r],l++,r++
        // 5.
        if (nums == null || nums.length == 0) {
            return;
        }

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            // 当前值
            int val = nums[fast];
            if (val != target) {
                nums[slow] = val;
                slow++;
            }

        }

    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 9, 0, 3, 5, 9, 12};
        new RemoveElement()
            .removeElement(nums, 9);
        System.out.println(Arrays.toString(nums));
    }

}
