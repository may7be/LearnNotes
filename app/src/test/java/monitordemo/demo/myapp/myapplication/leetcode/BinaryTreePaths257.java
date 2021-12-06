package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhao on 2021/11/26
 */
class BinaryTreePaths257 {
    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     * <p>
     * 树中节点的数目在范围 [1, 100] 内
     * -100 <= Node.val <= 100
     * <p>
     * 老办法：递归和遍历
     */
    public List<String> binaryTreePaths(TreeNode root) {
        //递归
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //1. 叶子节点
        if (root.left == null && root.right == null) {
            list.add(String.valueOf(root.val));
            return list;
        }
        //2.
        if (root.left != null) {
            for (String path : binaryTreePaths(root.left)) {
                list.add(root.val + "->" + path);
            }
        }
        if (root.right != null) {
            for (String path : binaryTreePaths(root.right)) {
                list.add(root.val + "->" + path);
            }
        }
        return list;
    }

    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> list = new ArrayList<>();
        //按层记录每个节点的路径
        Map<TreeNode, String> map = new HashMap<>();
        map.put(root, String.valueOf(root.val));
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode remove = nodes.remove(0);
            String res = map.get(remove);
            TreeNode left = remove.left;
            TreeNode right = remove.right;
            if (left == null && right == null) {
                list.add(res);
            } else {
                if (left != null) {
                    nodes.add(left);
                    map.put(left, res + "->" + left.val);
                }
                if (right != null) {
                    nodes.add(right);
                    map.put(right, res + "->" + right.val);
                }
            }
        }
        return list;
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<>();


        //遍历：按层记录每个节点的路径
        Map<TreeNode, String> map = new HashMap<>();
        map.put(root, String.valueOf(root.val));
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {

        }


        while (root.left != null || root.right != null) {

        }

        if (root.left == null && root.right == null) {
            list.add(String.valueOf(root.val));
            return list;
        }


        return list;
    }
}
