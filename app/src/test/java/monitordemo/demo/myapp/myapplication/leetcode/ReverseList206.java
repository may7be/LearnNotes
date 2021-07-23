package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/10/21
 */
public class ReverseList206 {
    /**
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *  
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     */
    public ListNode reverseList3(ListNode head) {
        //递归
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    public ListNode reverseList4(ListNode head) {
        //迭代: 实际实在迭代中构造了一个新的链表
        if (head == null) {
            return null;
        }
        ListNode pre = new ListNode(head.val);
        while (head.next != null) {
            //先构造出next对应的节点
            ListNode te = new ListNode(head.next.val);
            //将te和pre建立联系
            te.next = pre;
            //将pre指向te
            pre = te;

            //迭代链表
            head = head.next;
        }
        return pre;
    }

    public ListNode reverseList5(ListNode head) {
        //迭代:
        ListNode pre = null;
        while (head != null) {
            //先记录next
            ListNode next = head.next;
            //更改head.next，顺便切断了head和next之间的联系
            head.next = pre;
            //移动pre指针指向head
            pre = head;
            //移动head指针到next
            head = next;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        //依次遍历就可以，比较绕，但是可以充分理解面向对象
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(0, head.val);
            head = head.next;
        }
        ListNode zero = new ListNode(0);
        ListNode te = zero;
        for (Integer integer : list) {
            te.next = new ListNode(integer);
            te = te.next;
        }
        return zero.next;
    }
}
