package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/5
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 迭代方式翻转
     */
    public static ListNode reverse(ListNode head) {
        //依次遍历就可以，比较绕，但是可以充分理解面向对象
        ListNode prev = null;
        while (head != null) {
            //先记录next
            ListNode next = head.next;
            //更改head.next，顺便切断了head和next之间的联系
            head.next = prev;
            //移动pre指针指向head
            prev = head;
            //移动head指针到next
            head = next;
        }
        return prev;
    }

    /**
     * 递归方式翻转
     */
    public static ListNode reverseList2(ListNode head) {
        //递归
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }
}
