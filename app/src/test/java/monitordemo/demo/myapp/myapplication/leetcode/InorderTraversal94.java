package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author zhao on 2020-03-22
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderTraversal94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //1.
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return list;
        }
        //2. 递归左右节点，然后和根节点拼一起即可
        List<Integer> list1 = inorderTraversal(root.left);
        List<Integer> list2 = inorderTraversal(root.right);
        list1.add(root.val);
        list1.addAll(list2);
        return list1;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        //迭代法
        List<Integer> list = new ArrayList<>();
        //1.
        if (root == null) {
            return list;
        }
        //2. 迭代
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        //记录已经迭代过的node, 即当前层不用迭代的节点集合
        List<TreeNode> noNodes = new ArrayList<>();
        while (nodes.size() > noNodes.size()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove(0);
                //判断需要迭代子分支的节点
                if (!noNodes.contains(node)) {
                    //left：add到nodes，left.val add到list
                    if (node.left != null) {
                        nodes.add(node.left);
                    }
                    nodes.add(node);
                    if (node.right != null) {
                        nodes.add(node.right);
                    }
                    //添加到noNodes
                    noNodes.add(node);
                } else {
                    nodes.add(node);
                }
            }
        }
        //3. 遍历nodes即可
        for (TreeNode node : nodes) {
            list.add(node.val);
        }
        return list;
    }

    public List<Integer> inorderTraversal3(TreeNode root) {
        //迭代法优化版：试着将nodes在for循环中实现插入，而不需要先remove
        List<Integer> list = new ArrayList<>();
        //1.
        if (root == null) {
            return list;
        }
        //2. 迭代
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        //记录已经迭代过的node, 即当前层不用迭代的节点集合
        List<TreeNode> noNodes = new ArrayList<>();
        while (nodes.size() > noNodes.size()) {
            for (int i = 0; i < nodes.size(); i++) {
                TreeNode node = nodes.get(i);
                if (!noNodes.contains(node)) {
                    noNodes.add(node);
                    //分别将left 和right插入到node的左右
                    if (node.left != null) {
                        nodes.add(i, node.left);
                        //i后移一位
                        i++;
                    }
                    if (node.right != null) {
                        nodes.add(i + 1, node.right);
                        //i后移一位
                        i++;
                    }
                }
            }
        }
        //3. 遍历nodes即可
        for (TreeNode node : nodes) {
            list.add(node.val);
        }
        return list;
    }

    public List<Integer> inorderTraversal4(TreeNode root) {
        //方法3的优化版：试着不用noNodes, 左右add后，将left right置为空即可
        List<Integer> list = new ArrayList<>();
        //1.
        if (root == null) {
            return list;
        }
        //2. 迭代
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        //记录当前层是否还有分支
        boolean hasBranch = true;
        while (hasBranch) {
            hasBranch = false;
            //按层遍历
            for (int i = 0; i < nodes.size(); i++) {
                TreeNode node = nodes.get(i);
                hasBranch = hasBranch || !(node.left == null && node.right == null);
                //分别将left 和right插入到node的左右
                if (node.left != null) {
                    nodes.add(i++, node.left);
                    //left置为空
                    node.left = null;
                }
                if (node.right != null) {
                    nodes.add(++i, node.right);
                    //right置为空
                    node.right = null;
                }
            }
        }
        //3. 遍历nodes即可
        for (TreeNode node : nodes) {
            list.add(node.val);
        }
        return list;
    }

    public List<Integer> inorderTraversal6(TreeNode root) {
        //另一种巧妙的入栈方式：直接按照遍历顺序反向入栈。比如中序就右中左顺序入栈即可
        //❌缺点是改变了原二叉树的结构
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (stack.size() > 0) {
            TreeNode pop = stack.pop();
            //如果是叶子节点, add到list即可
            if (pop.left == null && pop.right == null) {
                list.add(pop.val);
                continue;
            }
            //非叶子节点，就重新入栈, 入栈后节点置为null
            if (pop.right != null) {
                stack.add(pop.right);
                pop.right =null;
            }
            stack.add(pop);
            if (pop.left != null) {
                stack.add(pop.left);
                pop.left = null;
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal5(TreeNode root) {
        //❌不太容易理解，也没写完
//        List<Integer> list = new ArrayList<>();
//        if (root == null) {
//            return list;
//        }
//        List<TreeNode> nodes = new ArrayList<>();
//        nodes.add(root);
//        while (nodes.size() > 0) {
//            TreeNode node = nodes.get(0);
//            if (node.left != null) {
//                nodes.add(0, node.left);
//            }else {
//                list.add(node.val);
//                nodes.remove(node);
//                if (node.right != null) {
//                    nodes.add(0, node.right);
//                }
//                node = null;
//            }
//        }
//        return list;
//
//
//
//
//        Stack<TreeNode> stack = new Stack<>();
//        stack.add(root);
//        while (!stack.isEmpty()) {
//            TreeNode pop = stack.peek();
//            if (pop.left != null) {
//                stack.add(pop.left);
//            }else {
//                list.add(pop.val);
//                stack.pop();
//                if (pop.right != null) {
//                    stack.add(pop.right);
//                }
//                pop = null;
//            }
//        }
//        return list;
        return null;
    }
}
