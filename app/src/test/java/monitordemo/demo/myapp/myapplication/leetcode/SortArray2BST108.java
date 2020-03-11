package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-11
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArray2BST108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        //❌理解题意有问题，二叉搜索树的定义：它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
        // 1.
        if (nums == null || nums.length == 0) {
            return null;
        }
        //遍历
        TreeNode first = new TreeNode(nums[0]);
        //记录当前层的节点
        List<TreeNode> list = new ArrayList<>();
        list.add(first);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (list.isEmpty()) {
                break;
            }
            //依次取出第一个节点，对left right赋值
            TreeNode remove = list.remove(0);
            remove.left = new TreeNode(nums[i]);
            //left赋值后，add到list
            list.add(remove.left);
            if (++i < len) {
                remove.right = new TreeNode(nums[i]);
                list.add(remove.right);
            }
        }
        return first;
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        //1.
        if (nums == null || nums.length == 0) {
            return null;
        }
        //递归思路：
        //1. 取相对中间位置的元素作为根节点；
        //2. 左节点再从左边队列里生成即可；右节点从右边队列生成
        return createTree(0, nums.length - 1, nums);
    }

    private TreeNode createTree(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int p = (left + right) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = createTree(0, p - 1, nums);
        root.right = createTree(p + 1, right, nums);
        return root;
    }
}
