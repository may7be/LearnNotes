package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author zhao on 2020-03-17
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 提示：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConnectNode116 {
    public Node connect(Node root) {
        //1.
        if (root == null) {
            return null;
        }
        //2. 从上往下，一层一层记录节点，然后穿成串即可
        List<Node> list = new ArrayList<>();
        List<Node> nextList = new ArrayList<>();
        list.add(root);
        while (list.size() > 0) {
            Node temp = null;
            for (Node node : list) {
                //串成串，
                if (temp != null) {
                    temp.next = node;
                }
                //并且记录下一层
                if (node.left != null) {
                    nextList.add(node.left);
                }
                if (node.right != null) {
                    nextList.add(node.right);
                }
                temp = node;
            }
            list.clear();
            list.addAll(nextList);
            nextList.clear();
        }
        return root;
    }

    public Node connect2(Node root) {
        //优化思路：两层循环总是不太好
        if (root == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        list.add(root);
        int count = 0;
        Node before = null;
        while (list.size() > 0) {
            count++;
            //由于是完美二叉树，通过计数来区分每一层：1，2，4，8，16
            Node remove = list.remove(0);
            //before不为空，并且排除行首
            if (before != null && !isBegin(count)) {
                before.next = remove;
            }
            //并且记录下一层
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
            before = remove;
        }
        return root;
    }

    private boolean isBegin(int count) {
        //获取层级
        int floor = (int) (Math.log(count) / Math.log(2)) + 1;
        //行首
        return Math.pow(2, floor - 1) == count;
    }

    public Node connect3(Node root) {
        //优化思路2：简化代码,使用特殊的数据结构可简化代码
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //永远都poll出第一个就好,因为第一个不会是任何一个node的next
                Node pop = queue.poll();
                if (pop == null) {
                    continue;
                }
                //到最后一个也不用peek了
                if (i < size - 1) {
                    pop.next = queue.peek();
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                }
            }
        }
        return root;
    }

    public Node connect4(Node root) {
        //题解中的解法2：根据上一层来处理下一层
        if (root == null) {
            return null;
        }
        Node temp = root;
        //记录行首node
        Node first = root;
        while (temp.left != null) {
            temp.left.next = temp.right;
            if (temp.next != null) {
                temp.right.next = temp.next.left;
                temp = temp.next;
            } else {
                if (first.left != null) {
                    temp = first.left;
                    first = first.left;
                }
            }
        }
        return root;
    }

    public Node connect5(Node root) {
        //评论中的递归法：
        if (root == null) {
            return root;
        }
        if (root.right != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect5(root.left);
        connect5(root.right);
        return root;
    }



    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    @Test
    public void fun() {
        int count = 6;
        System.out.println(count + "_isBegin: " + (count == 1 || count % 2 == 0));
        System.out.println(count + "_log: " + Math.log(count));
        System.out.println(1 + "_log: " + Math.log(1) / Math.log(2));
        System.out.println(2 + "_log: " + Math.log(2) / Math.log(2));
        System.out.println(4 + "_log: " + Math.log(4) / Math.log(2));
        System.out.println(8 + "_log: " + Math.log(8) / Math.log(2));
        System.out.println(10 + "_log: " + Math.log(10) / Math.log(2));
        System.out.println(11 + "_log: " + Math.log(11) / Math.log(2));
        System.out.println(16 + "_log: " + Math.log(16) / Math.log(2));

        System.out.println(1 + "_isBegin: " + isBegin(1));
        System.out.println(2 + "_isBegin: " + isBegin(2));
        System.out.println(4 + "_isBegin: " + isBegin(4));
        System.out.println(8 + "_isBegin: " + isBegin(8));
        System.out.println(10 + "_isBegin: " + isBegin(10));
        System.out.println(16 + "_isBegin: " + isBegin(16));
        System.out.println("4.0 == 4 " + (4.0 == 4));

    }
}
