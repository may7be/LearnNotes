package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/11/26
 */
class SearchBST700 {

    /**
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     * 二叉树的题一般都有两种解法：遍历和递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        //遍历
        TreeNode temp = root;
        int tv;
        while (true) {
            if (temp == null) {
                return null;
            }
            tv = temp.val;
            if (tv == val) {
                return temp;
            } else if (tv > val) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        //递归
        if (root == null) {
            return null;
        }
        int value = root.val;
        if (value == val) {
            return root;
        } else if (value > val) {
            return searchBST2(root.left, val);
        }else {
            return searchBST2(root.right, val);
        }
    }
}
