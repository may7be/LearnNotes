package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 2020-03-31
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuildTree105 {
    private TreeNode buildTree(int[] preorder, int[] inorder) {
        //前序遍历 preorder = [3,9,20,15,7]
        //中序遍历 inorder = [9,3,15,20,7]
        //1.
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        if (preorder.length == 0) {
            return null;
        }
        //2.分为根节点、左右节点
        TreeNode root = new TreeNode(preorder[0]);
        //左节点在中序遍历中的索引
        int leftIndex = -1;
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            if (inorder[i] == root.val) {
                leftIndex = i;
                break;
            }
        }
        //获取左节点的中序、前序遍历数组
        int[] leftInorder = new int[leftIndex];
        System.arraycopy(inorder, 0, leftInorder, 0, leftIndex);
        int[] leftPreorder = new int[leftIndex];
        System.arraycopy(preorder, 1, leftPreorder, 0, leftIndex);
        //获取右节点的中序、前序遍历数组
        int[] rightInorder = new int[length - leftIndex - 1];
        System.arraycopy(inorder, leftIndex + 1, rightInorder, 0, length - leftIndex - 1);
        int[] rightPreorder = new int[length - leftIndex - 1];
        System.arraycopy(preorder, 1 + leftIndex, rightPreorder, 0, length - leftIndex - 1);
        //3. 分别获取左右节点，拼接即可
        TreeNode left = buildTree(leftPreorder, leftInorder);
        TreeNode right = buildTree(rightPreorder, rightInorder);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        //优化：转为list更容易切割
        //但是int数组转为list还是有难度的❌❌
        return null;
    }

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        //优化2：每次递归都要求leftIndex明显比较冗余；能否不切割成子数组
        //前序遍历 preorder = [3,9,20,15,7]
        //中序遍历 inorder = [9,3,15,20,7]
        //1.
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int length = preorder.length;
        if (length == 0) {
            return null;
        }
        //2. 将中序的索引存入map: key是val，value为索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        //3.根据preorder中left位置元素在inorder中的顺序，可以知道左右分支
        //❌：问题就在这里：方法参数定义不够清晰
        return getTree(preorder, map, 0, 0, length - 1);
    }

    /**
     * @param preorder 前序遍历数组
     * @param map      中序遍历索引map
     * @param left     当前节点在前序数组中索引的开端
     * @param start    当前节点在中序数组中的索引的开端
     * @param end      前节点在中序数组中的索引的尾端
     * @return node
     */
    private TreeNode getTree(int[] preorder, Map<Integer, Integer> map, int left, int start, int end) {
        if (left >= preorder.length || start > end) {
            return null;
        }
        //1. 根节点
        TreeNode root = new TreeNode(preorder[left]);
        if (left == preorder.length - 1 || start == end) {
            return root;
        }
        //2. 获取中序中的索引
        Integer index = map.get(preorder[left]);
        //左右分支的范围：left(start, index-1), right(index+1, end)
        //3. 获取左右节点
        //⚠️ 这里其实是三种情况：index在start;index在中间；index在end
        //在start时，左分支的数量为0；在end时，右分支数量为0
        //左节点在前序的开端为left+1
        root.left = getTree(preorder, map, left + 1, start, index - 1);
        //尾端为：left+左分支的数量+1
        root.right = getTree(preorder, map, left + (index - 1 - start + 1) + 1, index + 1, end);
        return root;
    }
}
