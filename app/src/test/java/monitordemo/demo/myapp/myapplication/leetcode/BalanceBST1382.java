package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * @Author zhao on 4/25/21
 */
public class BalanceBST1382 {

    @Test
    public void fun4() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        balanceBST(root);
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = TreeNode.toValueList(root);
        //2个结点以上才需要排序
        if (list.size() <= 2) {
            return root;
        }
        return balance(0, list.size() - 1, list);
    }

    private TreeNode balance(int start, int end, List<Integer> list) {
        if (isInValidIndex(start, list) || isInValidIndex(end, list) || start > end) {
            return null;
        }
        //寻找中间节点，作为根节点
        int rootIndex = start + (end - start) / 2;
        TreeNode rootNode = new TreeNode(list.get(rootIndex));
        //不断地寻找根节点
        rootNode.left = balance(start, rootIndex - 1, list);
        rootNode.right = balance(rootIndex + 1, end, list);
        return rootNode;
    }

    private boolean isInValidIndex(int index, List<Integer> list) {
        if (list == null) {
            return true;
        }
        return index < 0 || index > list.size() - 1;
    }
}
