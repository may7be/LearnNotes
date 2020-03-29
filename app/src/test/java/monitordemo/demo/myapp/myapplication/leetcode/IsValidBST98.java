package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;

/**
 * @Author zhao on 2020-03-26
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsValidBST98 {
    class MyList extends ArrayList<Integer> {
        @Override
        public boolean add(Integer integer) {
            if (size() >= 1 && integer > get(size() - 1)) {
                return false;
            }
            return super.add(integer);
        }
    }

    public boolean isValidBST(TreeNode root) {
        //1. 判空
        if (root == null) {
            return true;
        }
        //2. 中序遍历，add的时候比对是否是有序的
        //但是很明显会产生很多重复的运算，还是要正面解决问题
//        getLeftList(root.left);
        return false;
    }

    public boolean isValidBST3(TreeNode root) {
        //递归判断每个节点是否越界即可
        return isBst(root, null, null);
    }

    private boolean isBst(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        if (lower != null && root.val <= lower) {
            return false;
        }
        if (upper != null && root.val >= upper) {
            return false;
        }
        return isBst(root.left, lower, root.val) && isBst(root.right, root.val, upper);
    }


    public boolean isValidBST2(TreeNode root) {
        //还是有问题
        return isValid2(root, false, null);
    }

    /**
     * @param node   当前节点
     * @param isLeft 是左节点吗
     * @param root   当前节点的父节点
     * @return 是否二叉搜索树
     */
    private boolean isValid2(TreeNode node, boolean isLeft, TreeNode root) {
        //1. 空
        if (node == null) {
            return true;
        }
        //2. 左右都为空
        if (node.left == null && node.right == null) {
            return true;
        }
        //3. 左节点不为空
        if (node.left != null) {
            if (node.left.val >= node.val) {
                return false;
            }
            //node是右节点，同时root不为空
            if (!isLeft && root != null) {
                if (node.left.val <= root.val) {
                    return false;
                }
            }
        }
        //4. 右节点不为空
        if (node.right != null) {
            if (node.right.val <= node.val) {
                return false;
            }
            //node是左节点，同时root不为空
            if (isLeft && root != null) {
                if (node.right.val >= root.val) {
                    return false;
                }
            }
        }
        return isValid2(node.left, true, node) && isValid2(node.right, false, node);
    }

    public boolean isValid(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null) {
            if (root.left.val >= root.val || root.left.val >= val) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val <= root.val || root.right.val <= val) {
                return false;
            }
        }
        return isValid(root.left, val) && isValid(root.right, val);
    }

}
