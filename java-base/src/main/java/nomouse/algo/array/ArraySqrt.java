package nomouse.algo.array;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/9/20
 */
public class ArraySqrt {

    public int[] arraySqrt(int[] nums) {
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
        System.out.println(Arrays.toString(new ArraySqrt().arraySqrt(new int[] {
            -9, -5, 0, 1, 3, 7
        })));
    }
}
