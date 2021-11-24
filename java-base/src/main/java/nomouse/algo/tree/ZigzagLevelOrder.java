package nomouse.algo.tree;

import nomouse.algo.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author nomouse
 * @date 2021/11/18
 */
public class ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode treeNode) {

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);

        boolean reserve = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            result.add(list);
            for (int i = 0; i < size; i++) {

                TreeNode tree = queue.poll();
                if (tree != null) {
                    list.add(tree.val);
                } else {
                    continue;
                }

                if (tree.left != null) {
                    queue.offer(tree.left);
                }

                if (tree.right != null) {
                    queue.offer(tree.right);
                }
            }

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

        List<List<Integer>> list = new ZigzagLevelOrder()
            .zigzagLevelOrder(root);
        System.out.println(list);
    }
}
