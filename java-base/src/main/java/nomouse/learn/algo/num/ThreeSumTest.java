package nomouse.learn.algo.num;

import java.util.Arrays;

/**
 * 三数之和
 *
 * @author nomouse
 * @date 2021/11/16
 */
public class ThreeSumTest {

    /**
     * @param ary
     * @return
     */
    public boolean threeSum(int[] ary) {

        if (ary == null || ary.length < 3) {
            return false;
        }

        Arrays.sort(ary);

        int left;
        int right;

        for (int i = 0; i < ary.length; i++) {
            int v = ary[i];
            if (v >= 0) {
                break;
            }

            left = i + 1;
            right = ary.length - 1;

            while (left < right) {
                int twoSum = -ary[left] - ary[right];
                if (twoSum == v) {
                    System.out.println(v + "|" + ary[left] + "|" + ary[right]);
                    return true;
                } else if (twoSum < v) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] ary = new int[] {-3, -2, 1, 0, 4, 6, 3, 2};

        System.out.println(new ThreeSumTest().threeSum(ary));
    }

}
