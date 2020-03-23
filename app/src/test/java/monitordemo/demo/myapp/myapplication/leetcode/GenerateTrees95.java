package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020-03-23
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateTrees95 {
    public List<TreeNode> generateTrees2(int n) {
        //递归法
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (end < start) {
            list.add(null);
            return list;
        }
        if (end == start) {
            list.add(new TreeNode(start));
            return list;
        }
        for (int i = start; i <= end; i++) {
            //依次做根节点
            List<TreeNode> leftNodes = generateTrees(start, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, end);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public List<TreeNode> generateTrees(int n) {
//        List<TreeNode> list = new ArrayList<>();
//        //1.
//        if (n < 1) {
//            return list;
//        }
//        if (n == 1) {
//            TreeNode node = new TreeNode(1);
//            list.add(node);
//            return list;
//        }
//        if (n == 2) {
//            return generateNode(1, 2);
//        }
//        if (n == 3) {
//            return generateNode(1, 2, 3);
//        }
//        //2.依次选取根节点，然后拼接左中右即可
//        //n ==3
//
//
//        return null;
        if (n == 0) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        return generateNodesFromList(list);
    }

    private List<TreeNode> generateNodesFromList(List<Integer> valList) {
        List<TreeNode> list = new ArrayList<>();
        if (valList == null) {
            list.add(null);
            return list;
        }
        if (valList.size() == 1) {
            return generateNode(valList.get(0));
        }
        if (valList.size() == 2) {
            return generateNode(valList.get(0), valList.get(1));
        }
        if (valList.size() == 3) {
            System.out.println("0: " + valList.get(0) + ", 1: " + valList.get(1) + ", 2: " + valList.get(2));
            return generateNode(valList.get(0), valList.get(1), valList.get(2));
        }

        for (int i = 0; i < valList.size(); i++) {
            //依次做根节点，分leftValList,rightValList
            List<Integer> leftValList = null;
            List<Integer> rightValList = null;
            System.out.println(valList.get(i));
            if (i > 0) {
                leftValList = valList.subList(0, i);
            }
            System.out.println("leftValList: " + (leftValList == null ? "" : leftValList.toString()));
            //获取左节点集合
            List<TreeNode> leftNodes = generateNodesFromList(leftValList);
            if (i < valList.size() - 1) {
                rightValList = valList.subList(i + 1, valList.size());
            }
            System.out.println("rightValList: " + (rightValList == null ? "" : rightValList.toString()));
            //获取右节点集合
            List<TreeNode> rightNodes = generateNodesFromList(rightValList);
            System.out.println("leftNodes: " + leftNodes.size() + ", rightNodes: " + rightNodes.size());
            //左中右进行拼接, add到list中
            list.addAll(connectLeftAndRight(leftNodes, valList.get(i), rightNodes));
        }
        return list;
    }

    private List<TreeNode> connectLeftAndRight(List<TreeNode> leftNodes, Integer rootVal, List<TreeNode> rightNodes) {
        List<TreeNode> list = new ArrayList<>();
        for (TreeNode leftNode : leftNodes) {
            for (TreeNode rightNode : rightNodes) {
                TreeNode root = new TreeNode(rootVal);
                root.left = leftNode;
                root.right = rightNode;
                list.add(root);
            }
        }
        return list;
    }

    private List<TreeNode> generateNode(int root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(root));
        return list;
    }

    private List<TreeNode> generateNode(int small, int big) {
        List<TreeNode> list = new ArrayList<>();
        //small做根节点
        TreeNode node = new TreeNode(small);
        node.right = new TreeNode(big);
        list.add(node);

        //big做根节点；
        TreeNode node2 = new TreeNode(big);
        node2.left = new TreeNode(small);
        list.add(node2);
        return list;
    }

    private List<TreeNode> generateNode(int s, int m, int b) {
        List<TreeNode> list = new ArrayList<>();
        //s做根节点
        List<TreeNode> list1 = generateNode(m, b);
        for (TreeNode treeNode : list1) {
            TreeNode node = new TreeNode(s);
            node.right = treeNode;
            list.add(node);
        }
        //m做根节点
        TreeNode node = new TreeNode(m);
        node.left = new TreeNode(s);
        node.right = new TreeNode(b);
        list.add(node);
        //b做根节点
        List<TreeNode> list3 = generateNode(s, m);
        for (TreeNode treeNode : list3) {
            TreeNode node3 = new TreeNode(b);
            node3.left = treeNode;
            list.add(node3);
        }
        return list;
    }

    @Test
    public void fun() {
        List<TreeNode> nodes = generateTrees(4);
        System.out.println("nodes.size: " + nodes.size());

    }

}
