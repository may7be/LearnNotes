package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-10
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepth104 {
    public int maxDepth(TreeNode root) {
        //1.
        int depth = 0;
        //2. 迭代
        List<TreeNode> list = new ArrayList<>();
        if (root != null) {
            list.add(root);
        }
        while (list.size() > 0) {
            depth++;
            List<TreeNode> adding = new ArrayList<>();
            for (TreeNode treeNode : list) {
                if (treeNode.left != null) {
                    adding.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    adding.add(treeNode.right);
                }
            }
            list.clear();
            list.addAll(adding);
        }
        return depth;
    }

    public int maxDepth2(TreeNode root) {
        //优化1：不要adding,直接修改list
        //1.
        int depth = 0;
        //2. 迭代
        List<TreeNode> list = new ArrayList<>();
        if (root != null) {
            list.add(root);
        }
        while (list.size() > 0) {
            depth++;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.remove(0);
                if (treeNode.left != null) {
                    list.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.add(treeNode.right);
                }
            }
        }
        return depth;
    }

    public int maxDepth3(TreeNode root) {
        //递归
        //1.
        if (root == null) {
            return 0;
        }
        //2. 分别比较左右分支，取较大的
        return Math.max(maxDepth3(root.left), maxDepth3(root.right)) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
