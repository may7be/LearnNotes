package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/14
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsCousins993 {
    /**
     * 深度相同，但父节点不同，则它们是一对堂兄弟节点
     * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        //1.
        if (root == null || x == y) {
            return false;
        }
        //2. 迭代
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!list.isEmpty()) {
            if (list.contains(x) && list.contains(y)) {
                return true;
            }
            list.clear();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = nodes.remove(0);
                if (remove.left != null && remove.right != null) {
                    int left = remove.left.val;
                    int right = remove.right.val;
                    if (x == left && y == right) {
                        return false;
                    }
                    if (x == right && y == left) {
                        return false;
                    }
                }
                if (remove.left != null) {
                    list.add(remove.left.val);
                    nodes.add(remove.left);
                }
                if (remove.right != null) {
                    list.add(remove.right.val);
                    nodes.add(remove.right);
                }
            }
        }
        return false;
    }
}
