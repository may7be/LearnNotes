package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/7
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTrees617 {
    private TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //1.
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        //2. 根节点相加，然后递归左右分支即可
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    private TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        //❌❌❌：问题在于采用逐层merge，已经采用了list的数据结构，所以计算很冗余，放弃这种操作
        //思路2：迭代法
        //1.
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        //2.
        List<TreeNode> list1 = new ArrayList<TreeNode>();
        list1.add(t1);
        List<TreeNode> list2 = new ArrayList<>();
        list2.add(t2);
        boolean hasNextLevel = true;
        System.out.println("t1: " + t1.val + ", t2:" + t2.val);
        while (hasNextLevel) {
            hasNextLevel = false;
            int size = list1.size();
            for (int i = 0; i < size; i++) {
                TreeNode node1 = list1.remove(0);
                TreeNode node2 = list2.remove(0);
                if (node1 != null || node2 != null) {
                    hasNextLevel = true;
                }
                addNextNodes2List(node1, list1);
                addNextNodes2List(node2, list2);
                mergeBranches(node1, node2);
            }
        }
        return t1;
    }

    private void addNextNodes2List(TreeNode node1, List<TreeNode> list1) {
        if (node1 != null) {
            list1.add(node1.left);
            list1.add(node1.right);
        } else {
            list1.add(null);
            list1.add(null);
        }
    }

    private void mergeBranches(TreeNode t1, TreeNode t2) {
    }
}
