package nomouse.learn.algo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuchunhao on 2022/3/2
 */
public class RemoveArrayElements {

    /**
     * 给你一个数组nums和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * @param nums
     * @param target
     */
    public static void removeRepeatElements(int[] nums, int target) {
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

    /**
     * 给你一个数组nums和一个值 val，你需要 原地 移除所有重复的数字。
     *
     * @param nums
     */
    public static void removeRepeatElement(int[] nums) {
        // [-1,0,3,9,3,9,12]

        // 1.先遍历一遍找到所有重复字符，用set存储
        Set<Integer> repeatSet = new HashSet<>();
        Set<Integer> existSet = new HashSet<>();
        for (int i : nums) {
            if (existSet.contains(i)) {
                repeatSet.add(i);
            } else {
                existSet.add(i);
            }
        }

        // 2.双指针遍历数组:fast代表遍历到的数据，slow代表上一次移动的序列
        int size = nums.length;
        int slow = 0;
        int repeatCount = 0;
        for (int fast = 0; fast < size; fast++) {
            int v = nums[fast];
            if (!repeatSet.contains(v)) {
                // 当前字段不重复
                nums[slow] = nums[fast];
                slow++;
            } else {
                repeatCount++;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 9, 0, 3, 5, 9, 12};

        removeRepeatElements2(nums, 9);

        System.out.println(Arrays.toString(nums));
    }


    private static void removeRepeatElements2(int[] nums, int repeatValue) {
        if (nums.length == 0) {
            return;
        }

        int leftIndex = 0;
        for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
            int rightValue = nums[rightIndex];
            if (rightValue == repeatValue) {
                // 需要移除，往后遍历
            } else {
                // 不需要移除，用当前值覆盖上次位置，标记后移
                nums[leftIndex] = rightValue;
                leftIndex = leftIndex + 1;
            }
        }
    }


}