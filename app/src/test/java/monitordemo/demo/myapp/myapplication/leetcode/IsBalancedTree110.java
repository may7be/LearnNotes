package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-03-12
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsBalancedTree110 {
    public boolean isBalanced(TreeNode root) {
        //1.判空
        if (root == null) {
            return true;
        }
        //2. 左右先是，左右的高度差不超过2
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(getDepth(root.left) - getDepth(root.right)) < 2;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        root.val = Math.max(getDepth(root.left), getDepth(root.right)) + 1;
        return root.val;
    }

    public boolean isBalanced2(TreeNode root) {
        //优化思路：消除depth计算冗余
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            root.val = 1;
            return true;
        }
        //2. 左右先是，左右的高度差不超过2
        //问题的关键是怎么在 判断isBalanced(root.left)后，同时记录其高度
        //一个投机的办法是将其高度赋值到其val上
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }
        int leftDepth = root.left == null ? 0 : root.left.val;
        int rightDepth = root.right == null ? 0 : root.right.val;
        root.val = Math.max(leftDepth, rightDepth) + 1;
        return Math.abs(leftDepth - rightDepth) < 2;
    }

    public boolean isBalanced3(TreeNode root) {
        //优化思路2：修改val不太靠谱。可以将lef是否balance和depth存放在一个对象里
        //或者：获取平衡二叉树的高度，如果不平衡则返回-1
        return getBalancedDepth(root) != -1;
    }

    private int getBalancedDepth(TreeNode root) {
        //1. 空，深度为0
        if (root == null) {
            return 0;
        }
        //2. 分支同时为空，深度为1
        if (root.left == null && root.right == null) {
            return 1;
        }
        //3. 获取左右分支的高度
        int leftDepth = getBalancedDepth(root.left);
        int rightDepth = getBalancedDepth(root.right);
        //4. 先判断左右分支是否平衡
        if (leftDepth == -1 || rightDepth == -1) {
            return -1;
        }
        //5. 再判断高度差
        if (Math.abs(leftDepth - rightDepth) >= 2) {
            return -1;
        }
        //6. 返回平衡二叉树的高度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
