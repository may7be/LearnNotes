package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/10/21
 */
public class ReverseList206 {
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
