package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/8
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[2,2,2,5,2]
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/univalued-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsUnivalTree965 {
    private int val;

    private boolean isUnivalTree(TreeNode root) {
        //1.
        if (root == null) {
            return true;
        }
        val = root.val;
        //2. 左右子树也得是单值树
        return isUnivalNode(root);
    }

    private boolean isUnivalNode(TreeNode node) {
        if (node == null) {
            return true;
        }
        return node.val == val && isUnivalNode(node.left) && isUnivalNode(node.right);
    }

    private boolean isUnivalTree2(TreeNode root) {
        //1.
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        //2. 左右也得是单值树
        return isUnivalTree2(root.left) && isUnivalTree2(root.right);
    }

    private boolean isUnivalTree3(TreeNode root) {
        //思路：递归法
        //1.
        if (root == null) {
            return true;
        }
        int val = root.val;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode remove = list.remove(0);
            if (remove.val != val) {
                return false;
            }
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
        }
        return true;
    }
}
