package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/10/21
 */
public class LeafSimilar872 {

    /**
     * 给定的两棵树可能会有 1 到 200 个结点。
     * 给定的两棵树上的值介于 0 到 200 之间。
     */
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        //优化下获取list的过程; list比对，可以直接用equals
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        initLeafs(root1, list1);
        initLeafs(root2, list2);
        return list1.equals(list2);
    }

    private void initLeafs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        initLeafs(root.left, list);
        initLeafs(root.right, list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //暴力法：先求出叶子序列，然后依次比较
        List<Integer> list1 = getLeafList(root1);
        List<Integer> list2 = getLeafList(root2);
        if (list1.size() != list2.size()) {
            return false;
        }
        int len = list1.size();
        for (int i = 0; i < len; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getLeafList(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return list;
        }
        list.addAll(getLeafList(root.left));
        list.addAll(getLeafList(root.right));
        return list;
    }

}
