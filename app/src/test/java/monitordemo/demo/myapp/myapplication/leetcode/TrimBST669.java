package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/16
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 1
 * / \
 * 0   2
 * <p>
 * L = 1
 * R = 2
 * <p>
 * 输出:
 * 1
 * \
 * 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TrimBST669 {
    private TreeNode trimBST(TreeNode root, int L, int R) {
        //思路：二叉树的问题先从跟节点开始梳理逻辑
        //1. root.val 如果刚好在(L,R)说明根节点不变;如果<L,将根节点移动到right，然后还需要继续看right.val的值，所以此处需要递归；>R，同理。
        //2. 递归处理left right即可
        //1.
        if (root == null) {
            return null;
        }
        //2.
        if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else if (root.val == L) {
            root.left = null;
        } else if (root.val == R) {
            root.right = null;
        }
        //3.
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
