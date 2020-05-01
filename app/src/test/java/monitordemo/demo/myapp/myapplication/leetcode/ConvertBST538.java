package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author zhao on 2020/4/30
 * 538. 把二叉搜索树转换为累加树
 */
public class ConvertBST538 {
    private int cot;

    public TreeNode convertBST4(TreeNode root) {
        //双百解法
        if (root != null) {
            convertBST4(root.right);
            cot += root.val;
            root.val = cot;
            convertBST4(root.left);
        }
        return root;
    }
    public TreeNode convertBST3(TreeNode root) {
        //递归：按照右中左的顺序来即可
        //1.
        if (root == null) {
            return null;
        }
        //2.
        root.right = convertBST(root.right);
        cot += root.val;
        root.val = cot;
        root.left = convertBST(root.left);
        return root;
    }

    public TreeNode convertBST(TreeNode root) {
        //递归：按照右中左的顺序来即可
        //1.
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            cot += root.val;
            root.val = cot;
            return root;
        }
        //2.
        root.right = convertBST(root.right);
        cot += root.val;
        root.val = cot;
        root.left = convertBST(root.left);
        return root;
    }

    private List<TreeNode> list = new LinkedList<>();

    public TreeNode convertBST2(TreeNode root) {
        //思路：得到从左到右的节点，然后倒叙修改其val即可
        //1.
        if (root == null) {
            return null;
        }
        //2.
        helper(root);
        for (TreeNode treeNode : list) {
            cot += treeNode.val;
            treeNode.val = cot;
        }
        return root;
    }

    private void helper(TreeNode root) {
        //1.
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root);
            return;
        }
        //2.
        helper(root.right);
        list.add(root);
        helper(root.left);
    }
}
