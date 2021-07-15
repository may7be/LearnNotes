package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/5/21
 */
public class RemoveElements203 {
    /**
     * 列表中的节点数目在范围 [0, 104] 内
     * 1 <= Node.val <= 50
     * 0 <= val <= 50
     */
    public ListNode removeElements3(ListNode head, int val) {
        //是在原链表的基础上进行操作，最本能的操作
        ListNode newHead = null;
        ListNode last = null;
        while (head != null) {
            if (head.val == val) {
                //当满足条件时，需要修改上下节点之间的关系
                if (last != null) {
                    last.next = head.next;
                }
            } else {
                //正常节点时，移动last即可；同时记录newHead
                last = head;
                if (newHead == null) {
                    newHead = head;
                }
            }
            head = head.next;
        }
        return newHead;
    }

    public ListNode removeElements2(ListNode head, int val) {
        //其实是构造一条新链
        ListNode zero = new ListNode(-1);
        ListNode last = zero;
        while (head != null) {
            if (head.val != val) {
                last.next = head;
                last = last.next;
            }
            head = head.next;
        }
        //最后的next要赋值为空
        last.next = null;
        return zero.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        //暴力法
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            if (head.val != val) {
                list.add(head.val);
            }
            head = head.next;
        }
        ListNode temp = null;
        for (Integer num : list) {
            if (head == null) {
                head = new ListNode(num);
                temp = head;
            } else {
                temp.next = new ListNode(num);
                temp = temp.next;
            }

        }
        return head;
    }
}
