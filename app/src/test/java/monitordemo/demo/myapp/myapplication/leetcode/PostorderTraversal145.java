package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2021/11/25
 */
class PostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(list, root);
        return list;
    }

    private void postorder(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        //左右中
        postorder(list, root.left);
        postorder(list, root.right);
        list.add(root.val);
    }
}
