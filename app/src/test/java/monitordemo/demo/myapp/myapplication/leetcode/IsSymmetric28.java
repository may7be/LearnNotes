package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhao on 2020/4/8
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSymmetric28 {
    private boolean isSymmetric(TreeNode root) {
        //思路：递归看来行不通了，貌似可以通过迭代法
        //❌❌虽然能解决，但是问题在于太多耗时，需要统计太多空的节点
        //1.
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        //2. 逐层比对,从第二层开始：个数必须是偶数，并且反转后要一样
        List<TreeNode> list = new ArrayList<>();
        list.add(root.left);
        list.add(root.right);
        boolean hasNextLevel = true;
        while (hasNextLevel) {
            //比对个数
            System.out.println("size: " + list.size());
            if (list.size() / 2 != 0) {
                return false;
            }
            //反转后比对
            List<TreeNode> temp = new ArrayList<>(list);
            Collections.reverse(list);
            if (!isEqualsList(list, temp)) {
                System.out.println("reverse not equals");
                return false;
            }
            hasNextLevel = false;
            //记录下一层
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = list.remove(0);
                if (remove == null) {
                    list.add(null);
                    list.add(null);
                } else {
                    hasNextLevel = true;
                    list.add(remove.left);
                    list.add(remove.right);
                }
            }
        }
        return true;
    }

    private boolean isEqualsList(List<TreeNode> list, List<TreeNode> temp) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (TreeNode.isEquals(list.get(i), temp.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSymmetric2(TreeNode root) {
        //思路2：递归，root.left 和root.right需要对称
        if (root == null) {
            return true;
        }
        return isSymmetricBranch(root.left, root.right);

    }

    private boolean isSymmetricBranch(TreeNode a, TreeNode b) {
        //node a和b 对称的条件：a.left 和b.right对称；a.right和b.left对称
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val && isSymmetricBranch(a.left, b.right) && isSymmetricBranch(a.right, b.left);
    }
}
