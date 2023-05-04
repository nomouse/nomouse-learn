package nomouse.learn.algo.array;

/**
 * 长度最小连续子数组
 * <br>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <br>
 * 示例：
 * <br>
 * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 *
 * @author nomouse
 * @date 2021/9/20
 */
public class MinSubArray {


    /**
     * 2,3,1,2,4,3
     */
    public static int[] searchMinSubArray(int[] array, int sum) {
        int[] minIndexArray = new int[]{0, Integer.MAX_VALUE};

        for (int s = 0; s < array.length; s++) {
            int f = s + 1;
            int curSum = array[s];
            while (f < array.length) {
                curSum += array[f];
                if (curSum >= sum) {
                    System.out.println("s=" + s + ",f=" + f);
                    if ((minIndexArray[1] - minIndexArray[0]) > (f - s)) {
                        minIndexArray[0] = s;
                        minIndexArray[1] = f;
                        break;
                    }
                }
                f++;
            }
        }

        System.out.println("Min:s=" + minIndexArray[0] + ",f=" + minIndexArray[1]);
        return minIndexArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 2, 4, 3};

        searchMinSubArray(array, 7);
    }


}
