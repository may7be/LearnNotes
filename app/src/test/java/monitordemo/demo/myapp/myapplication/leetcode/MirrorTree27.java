package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/13
 * 面试题27. 二叉树的镜像
 */
public class MirrorTree27 {
    private TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
