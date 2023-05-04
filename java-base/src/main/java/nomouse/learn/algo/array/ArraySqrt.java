package nomouse.learn.algo.array;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/9/20
 * <p>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * </br>
 * 示例 1： 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为
 * [0,1,9,16,100]
 * </br>
 * 示例 2： 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
 */
public class ArraySqrt {

    public static int[] arraySqrt(int[] nums) {
        int[] res = new int[nums.length];

        int low = 0;
        int high = nums.length - 1;
        for (int j = nums.length - 1; j >= 0; j--) {

            int lowVal = nums[low] < 0 ? -nums[low] : nums[low];
            int highVal = nums[high];
            if (lowVal > highVal) {
                res[j] = lowVal * lowVal;
                low++;
            } else {
                res[j] = highVal * highVal;
                high--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arraySqrt(new int[]{
            -9, -5, 0, 1, 3, 7
        })));
    }


    private static int[] arraySqrt2(int[] array) {
        return null;
    }
}
