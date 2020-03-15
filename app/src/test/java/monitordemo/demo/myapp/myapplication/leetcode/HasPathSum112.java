package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-15
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HasPathSum112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //1.
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        //至少有一个子节点，并且root.val<sum
        //2. 从上到下，递归即可
        sum -= root.val;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        //思路2：方法1本质上是深度优先，可改为广度优先
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        //至少有一个子节点
        //2. 从上到下，一层一层计算
        List<List<TreeNode>> lists = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        lists.add(new ArrayList<TreeNode>());
        lists.get(0).add(root);
        countList.add(sum);
        while (lists.size() > 0 && countList.size() > 0) {
            List<TreeNode> nodes = lists.remove(0);
            Integer count = countList.remove(0);
            for (TreeNode node : nodes) {
                //过滤叶子节点
                if (node.left == null && node.right == null) {
                    if (node.val == count) {
                        return true;
                    } else {
                        continue;
                    }
                }
                lists.add(new ArrayList<TreeNode>());
                countList.add(count - node.val);
                if (node.left != null) {
                    lists.get(lists.size() - 1).add(node.left);
                }
                if (node.right != null) {
                    lists.get(lists.size() - 1).add(node.right);
                }
            }
        }
        return false;
    }

    public boolean hasPathSum3(TreeNode root, int sum) {
        //思路2的优化：减少代码
        if (root == null) {
            return false;
        }
        //2. 从上到下，一层一层计算
        //区别是对每个节点和count建立一对一的关系，方法2是两个分支和一个count建立的关系
        List<TreeNode> list = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        list.add(root);
        countList.add(sum);
        while (list.size() > 0 && countList.size() > 0) {
            TreeNode node = list.remove(0);
            Integer count = countList.remove(0);
            //过滤叶子节点
            if (node.left == null && node.right == null && node.val == count) {
                return true;
            }
            if (node.left != null) {
                list.add(node.left);
                countList.add(count - node.val);
            }
            if (node.right != null) {
                list.add(node.right);
                countList.add(count - node.val);
            }
        }
        return false;
    }


    /**
     * -1代表可以放弃这个节点，0代表已经找到合适的路径，1代表继续向下找
     *
     * @param root r
     * @param sum  s
     * @return int
     */
    private int get(TreeNode root, int sum) {
        if (root.val > sum) {
            return -1;
        }
        if (root.val == sum) {
            return root.left == null && root.right == null ? 0 : -1;
        }
        //val <sum, 并且是叶子节点，说明可以放弃这个节点了
        if (root.left == null && root.right == null) {
            return -1;
        }
        //还需继续检查子节点
        return 1;
    }
}
