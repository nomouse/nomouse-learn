package nomouse.algo.num;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/11/17
 */
public class WaveTest {

    public int parseWave(int[] array) {

        int[] diffArray = new int[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            int diff = array[i] - array[i - 1];
            diffArray[i - 1] = diff;
        }

        // 判断当前位置应该大于0还是小于0
        int step = 0;
        boolean flag = true;
        for (int j = 0; j < diffArray.length; j++) {
            if (flag) {
                if (diffArray[j] < 0) {
                    step = step + (1 - diffArray[j]);
                }
            } else {
                if (diffArray[j] > 0) {
                    step = step + (diffArray[j] + 1);
                }
            }

            flag = !flag;
        }

        System.out.println(Arrays.toString(diffArray));

        return step;
    }

    public static void main(String[] args) {
        int[] num = new int[] {1, 2, 3, 2, 1};

        System.out.println(new WaveTest().parseWave(num));
    }
}
