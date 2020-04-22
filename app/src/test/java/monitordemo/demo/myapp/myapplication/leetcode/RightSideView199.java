package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/22
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 */
public class RightSideView199 {
    public List<Integer> rightSideView(TreeNode root) {
        //思路：按层遍历即可，层内从右向左遍历
        //1.
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //2.
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int len = nodes.size();
            for (int i = 0; i < len; i++) {
                TreeNode remove = nodes.remove(0);
                if (i == 0) {
                    list.add(remove.val);
                }
                if (remove.right != null) {
                    nodes.add(remove.right);
                }
                if (remove.left != null) {
                    nodes.add(remove.left);
                }
            }
        }
        return list;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        //思路：递归,记录树的深度，add之前判断list.size = 深度-1即可
        List<Integer> list = new ArrayList<>();
        helper(list, root, 1);
        return list;
    }

    private void helper(List<Integer> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (list.size() == level - 1) {
            list.add(root.val);
        }
        helper(list, root.right, level + 1);
        helper(list, root.left, level + 1);
    }
}
