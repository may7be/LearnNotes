package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 2020/4/4
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuildTree106 {

    private Map<Integer, Integer> map;
    private int[] mPostorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //1.
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        //2. 跟105题很像，主要思路就是从根节点入手，然后分为左右子树递归即可
        int len = inorder.length;
        if (len == 1) {
            return new TreeNode(inorder[0]);
        }
        mPostorder = postorder;
        //2.1 将inorder的索引存入map，key为val，value为index
        map = new HashMap<>();
        int i = 0;
        for (int in : inorder) {
            map.put(in, i++);
        }
        //2.2 构建helper递归即可
        return buildTreeHelper(0, len - 1, len - 1);
    }

    /**
     * @param start     当前节点在中序数组中左索引
     * @param end       当前节点在中序数组中右
     * @param rootIndex 当前节点在后序数组中的根索引
     * @return tree
     */
    private TreeNode buildTreeHelper(int start, int end, int rootIndex) {
        if (start > end || rootIndex < 0) {
            return null;
        }
        //1. 根节点
        TreeNode root = new TreeNode(mPostorder[rootIndex]);
        if (start == end || rootIndex == 0) {
            return root;
        }
        //2. 获取根节点在中序数组中索引
        int index = map.get(mPostorder[rootIndex]);
        //3. 递归取左右子树即可
        //左子树的根节点在后序数组中的索引：rootIndex-(右子树的节点数)-1
        root.left = buildTreeHelper(start, index - 1, rootIndex - (end - index - 1 + 1) - 1);
        root.right = buildTreeHelper(index + 1, end, rootIndex - 1);
        return root;
    }
}
