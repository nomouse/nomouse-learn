package nomouse.learn.algo.array;

/**
 * @author nomouse
 * @date 2021/9/20
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class BinarySearch {

    public static int binarySearch(int[] nums, int target) {
        // [-1,0,3,5,9,12]
        //
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            int midVal = nums[mid];

            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch2(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }


    private static int binarySearch2(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        return search(array, 0, array.length - 1, target);
    }


    private static int search(int[] array, int startIndex, int endIndex, int target) {
        int midIndex = (startIndex + endIndex) / 2;
        int midValue = array[midIndex];
        if (midValue == target) {
            return midIndex;
        } else if (midValue > target) {
            return search(array, startIndex, midIndex - 1, target);
        } else {
            return search(array, midIndex + 1, startIndex, target);
        }


    }

}
