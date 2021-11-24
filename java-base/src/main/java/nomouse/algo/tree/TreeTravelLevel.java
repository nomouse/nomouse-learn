package nomouse.algo.tree;

import nomouse.algo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 后序遍历
 *
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * </p>
 *
 * @author nomouse
 * @date 2021/9/30
 */
public class TreeTravelLevel {

    public static List<List<Integer>> dfs(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        dfs(root, 0, result);

        return result;
    }

    public static void dfs(TreeNode node, int deep,
                           List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        if (node.val == null) {
            return;
        }
        List<Integer> levelRes;
        if (result.size() < deep + 1) {
            levelRes = new ArrayList<>();
            result.add(levelRes);
        }
        result.get(deep).add(node.val);

        deep++;

        dfs(node.left, deep, result);

        dfs(node.right, deep, result);

    }

    public static List<List<Integer>> bfs(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> levelRes = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    levelRes.add(cur.val);

                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }

                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
            }
            result.add(levelRes);
        }

        return result;
    }

    public static void main(String[] args) {
        //        1
        //     2     3
        //    4 5   6 7
        TreeNode left = new TreeNode(2,
            new TreeNode(4), new TreeNode(5));

        TreeNode right = new TreeNode(3,
            new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);

        List<List<Integer>> list = dfs(root);
        System.out.println(list);
    }
}
