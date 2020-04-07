package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/7
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InvertTree226 {
    private TreeNode invertTree(TreeNode root) {
        //1.
        if (root == null) {
            return null;
        }
        //2. 左右分支互换, 递归即可
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        //思路2：迭代法
        //1.
        if (root == null) {
            return null;
        }
        //2. 迭代
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode remove = list.remove(0);
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
            invertNode(remove);
        }
        return root;
    }

    private void invertNode(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
    }

}
