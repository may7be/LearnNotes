package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/5/7
 * 572. 另一个树的子树
 */
public class IsSubTree572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        //递归即可: 根节点相同，或者左右分支相同
        if (s == null) {
            return false;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
