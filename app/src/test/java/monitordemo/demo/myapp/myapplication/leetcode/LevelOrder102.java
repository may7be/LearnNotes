package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author zhao on 2020-03-09
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrder102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //递归
        List<List<Integer>> lists = new LinkedList<>();
        addToLists(root, 0, lists);
        return lists;
    }

    private void addToLists(TreeNode root, int i, List<List<Integer>> lists) {
        if (root == null || lists == null) {
            return;
        }
        if (lists.size() < i + 1) {
            lists.add(new LinkedList<Integer>());
        }
        lists.get(i).add(root.val);
        //依次把左右树添加进去即可
        if (root.left != null) {
            addToLists(root.left, i + 1, lists);
        }
        if (root.right != null) {
            addToLists(root.right, i + 1, lists);
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        //迭代
        List<List<Integer>> lists = new LinkedList<>();
        List<TreeNode> nodeList = new LinkedList<>();
        if (root != null) {
            nodeList.add(root);
        }
        while (nodeList.size() != 0) {
            //记录当前nodeList.size
            int removeSize = nodeList.size();
            lists.add(new LinkedList<Integer>());
            List<Integer> last = ((LinkedList<List<Integer>>) lists).getLast();
            //先add当前层的val
            List<TreeNode> addTrees = new LinkedList<>();
            for (TreeNode treeNode : nodeList) {
                last.add(treeNode.val);
                if (treeNode.left != null) {
                    addTrees.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    addTrees.add(treeNode.right);
                }
            }
            //再add左右分支
            nodeList.addAll(addTrees);
            //删除前removeSize个元素
            for (int i = 0; i < removeSize - 1; i++) {
                nodeList.remove(0);
            }
        }
        return lists;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
