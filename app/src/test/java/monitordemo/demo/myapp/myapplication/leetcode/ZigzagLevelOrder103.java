package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-30
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZigzagLevelOrder103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //1.
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        //2. 按层从上到下遍历
        List<TreeNode> nodes = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        lists.get(0).add(root.val);
        nodes.add(root);
        boolean reverse = true;
        while (!nodes.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = nodes.remove(size - 1 - i);
                if (!reverse) {
                    //正向
                    addToList(nodes, list, remove.left);
                    addToList(nodes, list, remove.right);
                } else {
                    addToList(nodes, list, remove.right);
                    addToList(nodes, list, remove.left);
                }
            }
            reverse = !reverse;
            //判空
            if (!list.isEmpty()) {
                lists.add(list);
            }
        }
        return lists;
    }

    private void addToList(List<TreeNode> nodes, List<Integer> list, TreeNode node) {
        if (node != null && list != null && nodes != null) {
            list.add(node.val);
            nodes.add(node);
        }
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        //❌❌优化下
        //1.
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        //2. 按层从上到下遍历
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        boolean reverse = true;
        while (!nodes.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = nodes.remove(0);
                list.add(remove.val);
                if (remove.left != null) {
                    nodes.add(remove.left);
                }
                if (remove.right != null) {
                    int index = reverse && nodes.size() > 0 ? nodes.size() - 1 : nodes.size();
                    nodes.add(index, remove.right);
                }
            }
            reverse = !reverse;
            lists.add(list);
        }
        return lists;
    }

    private void addToNodes(List<TreeNode> nodes, TreeNode node) {
        if (nodes != null && node != null) {
            nodes.add(node);
        }
    }
}
