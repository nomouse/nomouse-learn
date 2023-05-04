package nomouse.learn.algo.num;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个整数数组nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <br>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <br>
 * 示例:
 * <br>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <br>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * <br>
 * 所以返回 [0, 1]
 * <br>
 *
 * @author wuchunhao on 2023/4/19
 */
public class TwoNumSum {

    public static List<int[]> check(int[] array, int target) {

        List<int[]> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int restValue = target - value;

            Integer matchIndex = map.get(value);
            if (matchIndex != null) {
                int[] matchPair = new int[]{matchIndex, i};
                result.add(matchPair);
            } else {
                map.put(restValue, i);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 7, 8, 11, 15};
        int target = 9;

        List<int[]> res = check(array, target);
        for (int[] pair : res) {
            System.out.println("[" + pair[0] + "," + pair[1] + "]");
        }
    }
}
