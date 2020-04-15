package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/15
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 *  
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 *  
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DiameterOfBinaryTree543 {
    public int diameterOfBinaryTree(TreeNode root) {
        //1.
        if (root == null) {
            return 0;
        }
        //2. 递归获取左右子树的最大直径，
        int len = Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right));
        //3. 然后通过helper获取左右子树的深度相加，取较大值即可
        int i = getDep(root.left) + getDep(root.right);
        return Math.max(len, i);
    }

    private int getDep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDep = getDep(root.left);
        int rightDep = getDep(root.right);
        max = Math.max(leftDep + rightDep, max);
        return Math.max(leftDep, rightDep) + 1;
    }

    private int max = 0;

    public int diameterOfBinaryTree3(TreeNode root) {
        //优化思路3: 构建对象意义不大，不需要记录每个节点的最大直径，只需要保存一个全局最大的即可
        getDep(root);
        return max;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        //优化思路2：重复运算太多
        return helper(root).diameter;
    }

    private Obj helper(TreeNode node) {
        //节点的深度为左右节点深度较大值+1，
        // 最大直径为左右节点深度相加、左节点最大直径、右节点最大直径三者之间的较大值
        //1. 空节点或叶子节点
        if (node == null) {
            return new Obj();
        }
        if (node.left == null && node.right == null) {
            return new Obj(1, 0);
        }
        //2. 左右节点的obj
        Obj leftObj = helper(node.left);
        Obj rightObj = helper(node.right);
        //3. 求左右节点最大直径的最大值、经过当前节点的最大直径
        int bigger = Math.max(leftObj.diameter, rightObj.diameter);
        int curDia = leftObj.dep + rightObj.dep;
        return new Obj(Math.max(leftObj.dep, rightObj.dep) + 1, Math.max(bigger, curDia));
    }

    private class Obj {
        int dep;
        int diameter;

        Obj() {
        }

        Obj(int dep, int diameter) {
            this.dep = dep;
            this.diameter = diameter;
        }
    }
}
