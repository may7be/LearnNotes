package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/11/26
 */
class LowestCommonAncestor235 {

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归即可
        //1. 若p或q是根节点
        int rv = root.val;
        if (rv == p.val || rv == q.val) {
            return root;
        }
        //2. 一左一右，同样return root
        //p左q右
        if (rv > p.val && rv < q.val) {
            return root;
        }
        //q左p右
        if (rv > q.val && rv < p.val) {
            return root;
        }
        //3. 都左或都右，递归
        //都左
        if (rv > p.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //都右
        return lowestCommonAncestor(root.right, p, q);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //简化版递归
        //1. 都左
        int rv = root.val;
        if (rv > p.val && rv > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //2. 都右
        if (rv < p.val && rv < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        //3. 其他情况：一左一右或某一个是根结点
        return root;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        //遍历
        TreeNode temp = root;
        int tv;
        while (true) {
            tv = temp.val;
            if (tv > p.val && tv > q.val) {
                //都左
                temp = temp.left;
            }else if (tv < p.val && tv < q.val) {
                //都右
                temp = temp.right;
            }else {
                return temp;
            }
        }
    }
}
