package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.LinkedList;

/**
 * @Author zhao on 2020-03-19
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class Connect117 {
    public Node connect(Node root) {
        //1.
        if (root == null) {
            return null;
        }
        //2. 迭代
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        while (list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Node poll = list.poll();
                if (poll == null) {
                    //此处不可能发生，说明此结构选的还是有问题
                    return root;
                }
                if (i < size - 1) {
                    poll.next = list.peek();
                }
                if (poll.left != null) {
                    list.add(poll.left);
                }
                if (poll.right != null) {
                    list.add(poll.right);
                }
            }
        }
        return root;
    }

    private Node realConnect(Node start, Node end) {
        if (end != null) {
            if (start != null) {
                start.next = end;
            }
            start = end;
        }
        return start;
    }


    public Node connect2(Node root) {
        //递归: 主要思路是根据上一层的next关系，从行头开始循环即可。
        if (root == null) {
            return null;
        }
        Node upper = root;
        Node start = null;
        Node nextUpper = null;
        while (upper != null) {
            if (upper.left != null) {
                if (start == null) {
                    nextUpper = upper.left;
                } else {
                    start.next = upper.left;
                }
                start = upper.left;
            }
            if (upper.right != null) {
                if (start == null) {
                    nextUpper = upper.right;
                } else {
                    start.next = upper.right;
                }
                start = upper.right;
            }
            if (upper.next != null) {
                upper = upper.next;
            } else {
                upper = null;
            }
        }
        connect2(nextUpper);
        return root;
    }
}
