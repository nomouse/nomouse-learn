package nomouse.algo.tree;

import nomouse.algo.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author nomouse
 * @date 2021/9/30
 */
public class TreeTravel {

    public static List<Integer> preorderIt(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
            st.push(root);
        }

        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            if (cur != null) {
                st.pop();

                if (cur.right != null) {
                    st.push(cur.right);
                }
                if (cur.left != null) {
                    st.push(cur.left);
                }

                st.push(cur);
                st.push(null);
            } else {
                st.pop();
                cur = st.peek();
                st.pop();
                result.add(cur.val);
            }
        }
        return result;
    }

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        preorderRec(root, result);

        return result;
    }

    public static void preorderRec(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        // 先序
        list.add(root.val);

        if (root.left != null) {
            preorderRec(root.left, list);
        }

        if (root.right != null) {
            preorderRec(root.right, list);
        }

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

        List<Integer> list = preorder(root);
        System.out.println(list);
    }
}
