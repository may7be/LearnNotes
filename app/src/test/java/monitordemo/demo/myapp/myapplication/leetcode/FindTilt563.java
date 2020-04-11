package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/11
 * 给定一个二叉树，计算整个树的坡度。
 * <p>
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 * <p>
 * 整个树的坡度就是其所有节点的坡度之和。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * /   \
 * 2     3
 * 输出: 1
 * 解释:
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 * 注意:
 * <p>
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-tilt
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTilt563 {
    int count = 0;

    public int findTilt(TreeNode root) {
        //思路1：利用helper递归求节点和的过程中计算递归计算各节点的坡度
        helper(root);
        return count;
    }

    /**
     * 返回node的节点和
     */
    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftCount = helper(node.left);
        int rightCount = helper(node.right);
        count += Math.abs(leftCount - rightCount);
        return node.val + leftCount + rightCount;
    }
}
