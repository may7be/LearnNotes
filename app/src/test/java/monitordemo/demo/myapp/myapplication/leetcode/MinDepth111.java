package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-14
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDepth111 {
    public int minDepth(TreeNode root) {
        //1.
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        //2. 递归即可
        int leftMin = minDepth(root.left);
        int right = minDepth(root.right);
        if (leftMin == 0) {
            return right + 1;
        } else if (right == 0) {
            return leftMin + 1;
        } else {
            return Math.min(leftMin, right) + 1;
        }
    }

    private boolean isSingleNode(TreeNode treeNode) {
        if (treeNode == null) {
            return false;
        }
        return treeNode.left == null && treeNode.right == null;
    }

    public int minDepth2(TreeNode root) {
        //优化思路：从底向上效率不够高，应该从上往下
        //从上往下
        int dep = 0;
        List<TreeNode> list = new ArrayList<>();
        if (root != null) {
            list.add(root);
        }
        while (list.size() > 0) {
            //每一层加1
            dep++;
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode treeNode : list) {
                //判断这一层有没有叶子节点
                if (treeNode.left == null && treeNode.right == null) {
                    return dep;
                }
                if (treeNode.left != null) {
                    nextLevel.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nextLevel.add(treeNode.right);
                }
            }
            list.clear();
            list.addAll(nextLevel);
        }
        return dep;
    }
}
