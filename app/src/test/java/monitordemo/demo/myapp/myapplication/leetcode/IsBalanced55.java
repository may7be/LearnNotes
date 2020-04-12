package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/12
 * 是否平衡二叉树
 */
public class IsBalanced55 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(helper(root.left) - helper(root.right)) <= 1;
    }

    /**
     * 返回root节点的深度
     */
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }
}
