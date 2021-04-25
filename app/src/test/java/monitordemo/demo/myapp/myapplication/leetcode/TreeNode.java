package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-10
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    static boolean isEquals(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val;
    }

    static List<String> toList(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(node);
        return helper(nodes);
    }

    private static List<String> helper(List<TreeNode> nodes) {
        List<String> list = new ArrayList<>();
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            TreeNode remove = nodes.remove(0);
            if (remove == null) {
                list.add("null");
                nodes.add(null);
                nodes.add(null);
            } else {
                list.add(String.valueOf(remove.val));
                nodes.add(remove.left);
                nodes.add(remove.right);
            }
        }
        System.out.println("nextLevelSize(toList): " + nodes.size());
        if (nodes.isEmpty()) {
            return list;
        }
        //删除队尾的空节点
        for (int i = nodes.size() - 1; i >= 0; i--) {
            if (nodes.get(i) == null) {
                nodes.remove(i);
            } else {
                break;
            }
        }
        if (nodes.isEmpty()) {
            return list;
        }
        list.addAll(helper(nodes));
        return list;
    }

    public static List<Integer> toValueList(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recordValue(root, list);
        return list;
    }

    private static void recordValue(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        recordValue(node.left, list);
        list.add(node.val);
        recordValue(node.right, list);
    }

    public boolean noChild() {
        return left == null && right == null;
    }

    public boolean haveLeftChild() {
        return left != null;
    }

    public boolean haveRightChild() {
        return right != null;
    }

    @Test
    public void fun() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(toList(root));
    }
}
