package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/5/21
 */
public class RemoveElements203 {
    public ListNode removeElements2(ListNode head, int val) {
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
