package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author zhao on 2020-03-08
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 说明:
 * <p>
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSymmetricTree101 {
    public boolean isSymmetric(TreeNode root) {
        //1.
        if (root == null) {
            return true;
        }
        //2. 递归即可
        return isMirrorTree(root.left, root.right);
    }

    private boolean isMirrorTree(TreeNode left, TreeNode right) {
        //总结出镜像二叉树的特点：val相等；left.left和right.right 、left.right和right.left互为镜像
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isMirrorTree(left.left, right.right) && isMirrorTree(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        //思路2：理论上递归都可以用迭代来解决
        if (root == null) {
            return true;
        }
        //将需要比较的两个node依次放入list，然后取出来比对即可
        List<TreeNode> list = new LinkedList<>();
        list.add(root.left);
        list.add(root.right);
        while (list.size() >= 2) {
            TreeNode left = list.get(0);
            TreeNode right = list.get(1);
            //同时为空时，直接remove
            if (left == null && right == null) {
                list.remove(left);
                list.remove(right);
                continue;
            }
            //一方为空或val不等时时，直接return false
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            //都不为空，而且val相等。需要remove，并且add子树了
            list.remove(left);
            list.remove(right);
            list.add(left.left);
            list.add(right.right);
            list.add(left.right);
            list.add(right.left);
        }
        return list.size() == 0;
    }

    public boolean isSymmetric3(TreeNode root) {
        //思路2优化：选用合适的数据结构，用queue代替list
        if (root == null) {
            return true;
        }
        //将需要比较的两个node依次放入list，然后取出来比对即可
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root.left);
        list.add(root.right);
        while (list.size() >= 2) {
            TreeNode left = list.poll();
            TreeNode right = list.poll();
            //同时为空时，直接remove
            if (left == null && right == null) {
                continue;
            }
            //一方为空或val不等时时，直接return false
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            //都不为空，而且val相等。需要remove，并且add子树了
            list.add(left.left);
            list.add(right.right);
            list.add(left.right);
            list.add(right.left);
        }
        return list.size() == 0;
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
