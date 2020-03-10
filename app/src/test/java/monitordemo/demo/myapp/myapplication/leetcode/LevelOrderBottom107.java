package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-10
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrderBottom107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //跟从上往下也没区别，每次add(0)即可
        List<List<Integer>> lists = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        if (root != null) {
            nodeList.add(root);
        }
        List<Integer> list = new ArrayList<>();
        while (nodeList.size() > 0) {
            list.clear();
            int size = nodeList.size();
            for (int i = 0; i < size; i++) {
                //每次取第一个元素，add到list
                TreeNode treeNode = nodeList.remove(0);
                list.add(treeNode.val);
                //同时，left right add到nodeList
                if (treeNode.left != null) {
                    nodeList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nodeList.add(treeNode.right);
                }
            }
            lists.add(0, list);
        }
        return lists;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        //优化1：删除list
        List<List<Integer>> lists = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        if (root != null) {
            nodeList.add(root);
        }
        while (nodeList.size() > 0) {
            int size = nodeList.size();
            for (int i = 0; i < size; i++) {
                //每次取第一个元素，add到list
                TreeNode treeNode = nodeList.remove(0);
                if (i == 0) {
                    lists.add(0, new ArrayList<Integer>());
                }
                lists.get(0).add(treeNode.val);
                //同时，left right add到nodeList
                if (treeNode.left != null) {
                    nodeList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nodeList.add(treeNode.right);
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        //递归思路
        List<List<Integer>> lists = new ArrayList<>();
        addToLists(root, lists, 1);
        return lists;
    }

    private void addToLists(TreeNode root, List<List<Integer>> lists, int l) {
        if (root == null || lists == null) {
            return;
        }
        if (lists.size() < l) {
            lists.add(0, new ArrayList<Integer>());
        }
        lists.get(lists.size() - l).add(root.val);
        addToLists(root.left, lists, l + 1);
        addToLists(root.right, lists, l + 1);
    }
}
