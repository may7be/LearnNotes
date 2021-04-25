package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * @Author zhao on 4/25/21
 */
public class IncreasingBST897 {
    @Test
    public void fun4() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(7);
        increasingBST2(root);
    }

    private TreeNode lastNode;

    public TreeNode increasingBST2(TreeNode root) {
        //## 其实只需要记录firstNode 和lastNode即可
        TreeNode zeroNode = new TreeNode(-1);
        lastNode = zeroNode;
        concatNode(root);
        return zeroNode.right;
    }

    private void concatNode(TreeNode root) {
        if (root == null) {
            return;
        }
        //拼接左右子树, 注意left赋值为空
        concatNode(root.left);
        lastNode.right = root;
        root.left = null;
        lastNode = lastNode.right;
        concatNode(root.right);
    }

    /**
     * 二叉搜索树：空树；左子树的所有结点< 根结点< 右子树的所有结点
     * 树中节点数的取值范围是 [1, 100]
     * 0 <= Node.val <= 1000
     */
    public TreeNode increasingBST(TreeNode root) {
        //## 先中序遍历，然后生成树即可
        if (root == null) {
            return null;
        }
        //1. 中序遍历，记录value，存到list中
        List<Integer> list = TreeNode.toValueList(root);
        //2. 遍历生成新的树即可
        TreeNode firstNode = null;
        TreeNode lastNode = null;
        for (Integer v : list) {
            TreeNode temp = new TreeNode(v);
            if (lastNode == null) {
                firstNode = temp;
                lastNode = firstNode;
            } else {
                lastNode.right = temp;
                lastNode = lastNode.right;
            }
        }
        return firstNode;
    }
}
