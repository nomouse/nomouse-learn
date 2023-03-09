package nomouse.learn.algo.tree;

import nomouse.learn.algo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nomouse
 * @date 2021/9/17
 */
public class TreeTools {

    public int[] parseTree(TreeNode root) {
        return null;
    }

    /**
     * @param array
     * @return <p>
     * 1,2,3,4,5,6,7
     */
    public TreeNode parseArray(int[] array) {
        List<TreeNode> treeList
            = new ArrayList<>(array.length);
        for (int i : array) {
            TreeNode tree = new TreeNode();
            tree.val = i;
            treeList.add(tree);
        }

        for (int i = 0; i <= (treeList.size() / 2) - 1; i++) {
            TreeNode curr = treeList.get(i);
            curr.left = treeList.get(i * 2 + 1);
            curr.right = treeList.get(i * 2 + 2);
        }

        return treeList.get(0);
    }
}
