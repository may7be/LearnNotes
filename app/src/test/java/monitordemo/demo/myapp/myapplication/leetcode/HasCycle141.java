package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashSet;

/**
 * @Author zhao on 4/27/21
 */
public class HasCycle141 {
    /**
     * 这种写法有问题：
     * if (head.next != null) {
     * quick = head.next.next;
     * } else {
     * quick = null;
     * }
     * slow = head;
     * if (quick == slow) {
     * return true;
     * }
     * if (quick == null) {
     * return false;
     * }
     * head = head.next;
     *
     * @param head
     * @return
     */
    public boolean hasCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //定义快慢两个指针，看快指针能否追上慢的，结束条件是快指针为null，or追上
        ListNode quick = head.next;
        ListNode slow = head;
        while (quick != slow) {
            if (quick == null || quick.next == null) {
                return false;
            }
            quick = quick.next.next;
            slow = slow.next;
        }
        return true;
    }

    public boolean hasCycle2(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.add(head)) {
                head = head.next;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        HashSet<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode temp = head.next;
        while (temp != null) {
            if (set.contains(temp)) {
                return true;
            } else {
                set.add(temp);
                temp = temp.next;
            }
        }
        return false;
    }
}
