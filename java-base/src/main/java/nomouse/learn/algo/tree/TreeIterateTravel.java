package nomouse.learn.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import nomouse.learn.algo.TreeNode;

/**
 * 遍历二叉树
 *
 * @author nomouse
 * @date 2021/9/30
 */
public class TreeIterateTravel {

    private static List<Integer> iterate(TreeNode root) {
        // 前序遍历

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeStack = new Stack<>();

        treeStack.push(root);
        while (!treeStack.isEmpty()) {
            TreeNode cur = treeStack.pop();
            // 判断是否遍历过，遍历过的直接加入结果集
            if (cur != null) {
                // 右
                if (cur.right != null) {
                    treeStack.push(cur.right);
                }

                // 中
                treeStack.push(cur);
                treeStack.push(null);

                // 左
                if (cur.left != null) {
                    treeStack.push(cur.left);
                }
            } else {
                // 空节点弹出
                cur = treeStack.pop();
                result.add(cur.val);
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

        List<Integer> list = iterate(root);
        System.out.println(list);
    }
}
