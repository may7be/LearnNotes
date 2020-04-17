package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/17
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 *  
 *
 * 提示：
 *
 * 节点总数 <= 10000
 * 本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepth55 {
    private int maxDepth(TreeNode root) {
        //递归
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        //迭代
        if (root == null) {
            return 0;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int count = 0;
        while (!list.isEmpty()) {
            count++;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = list.remove(0);
                if (remove.left != null) {
                    list.add(remove.left);
                }
                if (remove.right != null) {
                    list.add(remove.right);
                }
            }
        }
        return count;
    }
}
