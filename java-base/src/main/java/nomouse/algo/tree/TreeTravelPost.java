package nomouse.algo.tree;

import nomouse.algo.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author nomouse
 * @date 2021/9/30
 */
public class TreeTravelPost {

    public static List<Integer> postorderIt(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
            st.push(root);
        }

        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            // 后序
            if (cur != null) {
                st.pop();
                st.push(cur);
                st.push(null);
                if (cur.right != null) {
                    st.push(cur.right);
                }
                if (cur.left != null) {
                    st.push(cur.left);
                }
            } else {
                st.pop();
                cur = st.peek();
                st.pop();
                result.add(cur.val);
            }
        }
        return result;
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        postorderRec(root, result);

        return result;
    }

    public static void postorderRec(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        // 后序
        if (root.left != null) {
            postorderRec(root.left, list);
        }

        if (root.right != null) {
            postorderRec(root.right, list);
        }

        list.add(root.val);

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

        List<Integer> list = postorder(root);
        System.out.println(list);
    }
}
