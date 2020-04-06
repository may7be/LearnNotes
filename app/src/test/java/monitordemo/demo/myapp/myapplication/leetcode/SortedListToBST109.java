package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zhao on 2020/4/5
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedListToBST109 {

    private List<TreeNode> list;

    public TreeNode sortedListToBST(ListNode head) {
        //需要先取一个根节点，否则无法继续
        //思路:转为真正的链表，然后递归即可

        //1.按顺序存入list
        list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(new TreeNode(temp.val));
            temp = temp.next;
        }
        //2. 利用helper递归即可
        return helper(0, list.size() - 1);
    }

    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }
        //1 取相对中间位置的node为根节点
        int middleIndex = (left + right) / 2;
        TreeNode root = list.get(middleIndex);
        if (left == right) {
            return root;
        }
        //2.递归拼接左右即可
        root.left = helper(left, middleIndex - 1);
        root.right = helper(middleIndex + 1, right);
        return root;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        //官方题解1：利用快慢指针来确定中间节点
        //1. 寻找中间节点(然后切断中间节点和上个节点的关系), 作为根节点
        ListNode middleNode = findMiddleNode(head);
        if (middleNode == null) {
            return null;
        }
        System.out.println("middle:" + middleNode.val);
        TreeNode root = new TreeNode(middleNode.val);
        //2. 递归求左右分支即可
        if (middleNode != head) {
            root.left = sortedListToBST2(head);
            if (root.left != null) {
                System.out.println("left:" + root.left.val);
            }
        }
        root.right = sortedListToBST2(middleNode.next);
        if (root.right != null) {
            System.out.println("right:" + root.right.val);
        }
        return root;
    }

    private ListNode findMiddleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        //快慢指针: 快指针移动两步，慢指针移动一步
        ListNode middlePre = null, slowNode = head, fastNode = head;
        while (fastNode.next != null && fastNode.next.next != null) {
            middlePre = slowNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        //切割:需要把slowNode这个节点置为空(其实并不是将这个节点置空，只需要切割关系即可)，同时还要返回这个节点
        //对象是存在方法区的一个个实例，只是这些实例之间通过next链接起来了而已；而fastNode等这些变量只是存在栈里的临时变量
        if (middlePre != null) {
            middlePre.next = null;
        }
        return slowNode;
    }

    private ListNode mHead;

    public TreeNode sortedListToBST3(ListNode head) {
        //官方题解2：中序遍历思想，因为中序遍历二叉搜索树就是一个递增序列
        //其实就是直接建树，问题是如何找到相对中间的位置作为最左边子树的根节点。如如0，1，2，3，4，5，如何找到最左边子树的根节点呢。
        //想到可以从整个链表的最中间位置，进行递归：整个链表相对中间的位置索引为2，所以最最最边子树的根节点就是0。
        //1.
        if (head == null) {
            return null;
        }
        mHead = head;
        //2. 确定链表的长度
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        //3. 利用helper递归
        return toBSTHelper(0, size - 1);
    }

    private TreeNode toBSTHelper(int left, int right) {
        //❌❌❌一定要注意：递归时，必须要用全局变量，比如head就不能作为参数传进来
        //1.
        if (left > right || mHead == null) {
            return null;
        }
        //2. 求相对中间位置
        int middle = (left + right) / 2;
        //3. 递归求最最左边的子树
        TreeNode leftNode = toBSTHelper(left, middle - 1);
        //4. 求最最左边子树的根节点
        TreeNode root = new TreeNode(mHead.val);
        //5. 递归求最最左边子树的右节点
        mHead = mHead.next;
        TreeNode rightNode = toBSTHelper(middle + 1, right);
        //6. 进行拼接
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    @Test
    public void fun() {
        int[] arr = new int[]{0, 1, 2, 3, 4};
        ListNode head = null, temp = null;
        for (int value : arr) {
            if (temp == null) {
                head = new ListNode(value);
                temp = head;
            } else {
                temp.next = new ListNode(value);
                temp = temp.next;
            }
        }
        System.out.println("arr: " + Arrays.toString(arr));
        TreeNode treeNode = sortedListToBST3(head);
        System.out.println(TreeNode.toList(treeNode));
    }
}
