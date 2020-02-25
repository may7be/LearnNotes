package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-23
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoList21 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //递归法
        //linkedList是android特有的数据结构
        //1. 首先判空：两者同时为空，或者一方为空； 2：依次取next进行即可,比较大小即可
//        //一方为空
//        if (l1 == null) {
//            return l2;
//        }
//        if (l2 == null) {
//            return l1;
//        }
        //都不为空，就一直取val即可
        ListNode minNode = new ListNode(-1);
        ListNode temp = minNode;

        //都不为空
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                //为next赋值
                temp.next = l1;
                //l1赋值为l1.next
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return minNode.next;
    }


    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        //其实是递归,
        if (l1 == null && l2 == null) {
            throw new IllegalArgumentException("参数异常");
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l2.next, l1);
            return l2;
        }
    }


    /**
     * 其实代表一个节点，因为是有序的，所以可以用第一个节点来代表整个链表
     */
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
