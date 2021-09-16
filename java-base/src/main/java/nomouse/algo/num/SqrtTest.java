package nomouse.algo.num;

/**
 * @author nomouse
 * @date 2021/9/16
 */
public class SqrtTest {

    public int sqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int begin = 1;
        int end = x;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;

            int val = x / mid;
            if (mid == val) {
                return mid;
            } else if (mid > val) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return end;
    }

    public static void main(String[] args) {
        int x = 8;

        System.out.println(new SqrtTest().sqrt(x));
    }
}
