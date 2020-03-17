package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author zhao on 2020-03-16
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Flatten114 {
    public void flatten(TreeNode root) {
        //1.
        if (root == null) {
            return;
        }
        //2. 最小模型, 递归
        if (root.left == null && root.right == null) {
            return;
        }
        //2.0 展开左右，分情况拼接即可
        flatten(root.left);
        flatten(root.right);
        //2.1 左空
        if (root.left == null) {
            return;
        }
        //2.2 右空
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return;
        }
        //2.3 左右均不空
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        getLastNode(root.right).right = temp;
    }

    public void flatten2(TreeNode root) {
        //优化思路：简化代码
        if (root == null) {
            return;
        }
        //2. 展开左右，分情况拼接即可
        flatten(root.left);
        flatten(root.right);
        if (root.left != null) {
            TreeNode t = root.right;
            root.right = root.left;
            root.left = null;
            if (t != null) {
                TreeNode te = root.right;
                while (te.right != null) {
                    te = te.right;
                }
                te.right = t;
            }
        }
    }

    public void flatten3(TreeNode root) {
        // 思路2：考虑是否可用迭代法
        if (root == null) {
            return;
        }
        //2.不断滴从右向左压栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        //记录上一个节点
        TreeNode before = null;
        while (stack.size() > 0) {
            TreeNode pop = stack.pop();
            //非第一个节点是赋值right，left置为空
            if (before != null) {
                before.right = pop;
                before.left = null;
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
            before = pop;
        }
    }

    private String getChain(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(", ");
        if (root.right != null) {
            sb.append(getChain(root.right));
        }
        return sb.toString();
    }

    private TreeNode getLastNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return getLastNode(root.right);
    }

    private TreeNode getTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = new TreeNode(root.val);
        TreeNode temp = node;
        //递归
        TreeNode left = getTreeNode(root.left);
        if (left != null) {
            temp.right = left;
        }
        if (root.left != null) {
            temp.right = new TreeNode(root.left.val);
            temp = temp.right;

        }
        if (root.right != null) {
            temp.right = new TreeNode(root.right.val);
            temp = temp.right;
        }
        return node;
    }
}
