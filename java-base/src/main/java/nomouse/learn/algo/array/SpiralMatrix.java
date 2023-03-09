package nomouse.learn.algo.array;

import java.util.Arrays;

/**
 * @author nomouse
 * @date 2021/9/20 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 */
public class SpiralMatrix {

    public int[][] spiralMatrix(int n) {

        int[][] res = new int[n][n];

        // 方向，0右，1下，2左，3上
        int way = 0;

        int step = 0;

        int row = 0, col = 0;
        res[row][col] = 1;

        int rowLimit = n - 1;
        int colLimit = n - 1;
        for (int i = 2; i <= n * n; i++) {
            // 往右
            switch (way) {
                case 1:
                    // 下走一步
                    row++;
                    step++;
                    res[row][col] = i;

                    // 判断下一步
                    if (row + 1 >= n || res[row + 1][col] != 0) {
                        // 转向左
                        step = 0;
                        way = 2;
                    }
                    break;
                case 2:
                    // 左走一步

                    col--;
                    step++;
                    res[row][col] = i;

                    if (col - 1 < 0 || res[row][col - 1] != 0) {
                        // 转向上
                        step = 0;
                        way = 3;
                    }
                    break;
                case 3:
                    // 上走一步
                    row--;
                    step++;
                    res[row][col] = i;

                    if (row - 1 < 0 || res[row - 1][col] != 0) {

                        // 转向右，注意要调整limit
                        step = 0;
                        way = 0;
                    }

                    if (step >= rowLimit) {

                    }
                    break;
                default:
                    // 右走一步
                    col++;
                    step++;
                    res[row][col] = i;

                    if (col + 1 >= n || res[row][col + 1] != 0) {
                        // 转向下
                        step = 0;
                        way = 1;
                    }
                    break;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        int[][] res = new SpiralMatrix().spiralMatrix(6);

        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

}
