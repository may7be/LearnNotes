package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/10/21
 */
public class IsPalindrome234 {
    /**
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode te = head;
        while (te != null) {
            ListNode next = te.next;
            te.next = prev;
            prev = te;
            te = next;
        }
        return prev;
    }

    public boolean isPalindrome2(ListNode head) {
        //未完成：整体反转的时候，可以用先copyNode
        //避免使用 O(n)O(n) 额外空间的方法就是改变输入。可以将后半部分反转，然后比对即可
        //不能整体都反转，因为反转的过程中已经改变原链表了
        //1. 先用快慢指针找到中间节点
        ListNode low = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {


            low = low.next;
            fast = fast.next.next;
        }




        //直接反转，然后再比对
//        ListNode te = head;
//        ListNode reverse = null;
//        while (te != null) {
//            ListNode next = te.next;
//            te.next = reverse;
//            reverse = te;
//            te = next;
//        }
        ListNode reverse = reverse(head);
        while (reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
        System.out.println("++++");
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        while (head != null) {
            //实际只需要比对前半部分即可
            if (head.val != reverse.val) {
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        //暴力法: 获取list，然后比对即可
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        //
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
