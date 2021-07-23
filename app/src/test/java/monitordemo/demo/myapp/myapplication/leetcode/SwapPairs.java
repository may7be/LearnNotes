package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 7/23/21
 */
public class SwapPairs {

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     */
    public ListNode swapPairs(ListNode head) {
        //1. 0或1个节点，直接return
        if (head == null || head.next == null) {
            return head;
        }
        //2. 记录head.next和head.next.next
        ListNode newHead = head.next;
        ListNode te = head.next.next;
        //3. 交换head和head.next，其中head.next需要指向te的递归值
        head.next = swapPairs(te);
        newHead.next = head;
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        //递归
        return null;
    }
}
