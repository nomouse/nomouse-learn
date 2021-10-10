package nomouse.algo.hash;

import java.util.Arrays;
import java.util.List;

/**
 * @author nomouse
 * @date 2021/9/21
 */
public class ThreeSum {

    public List<List<Integer>> threeSum
        (int[] nums) {

        // 排序
        java.util.Arrays.sort(nums);

        List<List<Integer>> res
            = new java.util.ArrayList<>();

        // 第一个数循环
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return res;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int start = i + 1;
            int end = nums.length - 1;

            // 剩余和
            int sum = 0 - nums[i];

            while (start < end) {
                int cur = nums[start] + nums[end];
                if (sum > cur) {
                    start++;
                } else if (sum < cur) {
                    end--;
                } else {
                    List<Integer> one
                        = new java.util.ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[start]);
                    one.add(nums[end]);

                    res.add(one);

                    while (end > start && nums[end] == nums[end - 1]) {end--;}
                    while (end > start && nums[start] == nums[start + 1]) {start++;}

                    end--;
                    start++;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> r = new ThreeSum().threeSum(
            new int[] {-1, 0, 1, 2, -1, -4}
        );

        System.out.println(r);
    }
}