package nomouse.learn.algo.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个 n×m 的二维整数数组，用来表示一个迷宫，数组中只包含 0 或 1，其中 0 表示可以走的路，1 表示不可通过的墙壁。 最初，有一个人位于左上角 (1,1)
 * 处，已知该人每次可以向上、下、左、右任意一个方向移动一个位置。 请问，该人从左上角移动至右下角 (n,m) 处，至少需要移动多少次。 数据保证 (1,1) 处和 (n,m) 处的数字为
 * 0，且一定至少存在一条通路。 输入格式 第一行包含两个整数 n 和 m。 接下来 n 行，每行包含 m 个整数（0 或 1），表示完整的二维数组迷宫。 输出格式
 * 输出一个整数，表示从左上角移动至右下角的最少移动次数。 ————————————————
 */
public class Maze {


    public static void main(String[] args) {
        int[][] maze = new int[][]{
            new int[]{1, 1, 0, 1},
            new int[]{0, 1, 1, 1},
            new int[]{0, 0, 0, 1},
            new int[]{0, 1, 1, 1},
        };

        int[][] mazeFlag = new int[][]{
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
            new int[]{0, 0, 0, 0},
        };

        int[][] direct = new int[][]{
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1},
            new int[]{1, 0},
        };

        Deque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(0, 0));
        mazeFlag[0][0] = 1;

        while (!deque.isEmpty()) {
            Point cur = deque.poll();

            System.out.println("x=" + cur.x + "|y=" + cur.y);

            for (int i = 0; i < 4; i++) {
                int xMove = direct[i][0];
                int yMove = direct[i][1];

                // 计算下一个位置
                int x = cur.x + xMove;
                int y = cur.y + yMove;

                // 超出范围
                if (x < 0 || y < 0 || x > 3 || y > 3) {
                    continue;
                }

                // 判断是否走过
                if (mazeFlag[x][y] == 1) {
                    continue;
                }

                // 判断是否能走
                if (maze[x][y] == 0) {
                    continue;
                }

                deque.push(new Point(x, y));
                mazeFlag[x][y] = 1;

                // 到终点
                if (x == 3 && y == 3) {
                    deque.clear();
                }
            }

        }

    }


    public static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
