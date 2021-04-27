package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 4/27/21
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 */
public class RangeSumBST938 {
    /**
     * 树中节点数目在范围 [1, 2 * 104] 内
     * 1 <= Node.val <= 105
     * 1 <= low <= high <= 105
     * 所有 Node.val 互不相同
     */
    private int sum;

    public int rangeSumBST2(TreeNode root, int low, int high) {
        //中序遍历即可。边界就是值<low，或>high
        if (root == null) {
            return sum;
        }
        int val = root.val;
        //1. 分情况处理即可(按照left和right分支参与与否来分)
        if (val > low) {
            //只要已经小于最小值, 就说明left分支可能还存在范围内的数
            rangeSumBST(root.left, low, high);
        }
        if (val >= low && val <= high) {
            sum += val;
        }
        if (val < high) {
            //反之，只要还不够最大值，right分支就可能还存在范围内的数
            rangeSumBST(root.right, low, high);
        }
        return sum;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        //中序遍历即可。边界就是值<low，或>high
        if (root == null) {
            return sum;
        }
        int val = root.val;
        //1. 分情况处理即可(val从左往右，处于不同区间，不同处理)
        if (val < low) {
            // 只比对right
            rangeSumBST(root.right, low, high);
        } else if (val == low) {
            sum += val;
            rangeSumBST(root.right, low, high);
        } else if (val < high) {
            rangeSumBST(root.left, low, high);
            sum += root.val;
            rangeSumBST(root.right, low, high);
        } else if (val == high) {
            sum += val;
            rangeSumBST(root.left, low, high);
        } else {
            // 只比对left
            rangeSumBST(root.left, low, high);
        }
        return sum;
    }
}
