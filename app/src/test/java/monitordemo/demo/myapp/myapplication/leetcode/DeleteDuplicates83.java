package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-03-06
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteDuplicates83 {
    public ListNode deleteDuplicates(ListNode head) {
        //1. 判空 2.遍历，依次赋值即可
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(head.val);
        ListNode temp = first;
        while (head.next != null) {
            head = head.next;
            if (head.val != temp.val) {
                temp.next = new ListNode(head.val);
                temp = temp.next;
            }
        }
        return first;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        //直接修改head
        //思路：比较head前后两个节点的val值，如果不等，将后一个节点往后移动
        //记录前后两个节点
        ListNode start = head;
        ListNode end = start.next;
        while (start != null && end != null) {
            if (end.val == start.val) {
                //相等，则移动end到下个节点即end.next
                //同时，将start.next也赋值为end.next
                end = end.next;
                start.next = end.next;

            } else {
                //不等，则移动start到start.next
                start = start.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        //优化：将end省略
        ListNode t = head;
        while (t != null && t.next != null) {
            if (t.next.val == t.val) {
                //相等，则移动t.next到下个节点即t.next.next
                t.next = t.next.next;
            } else {
                //不等，则移动t到t.next
                t = t.next;
            }
        }
        return head;
    }



    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void fun() {
        //[1,1,2,3,3]

    }
}
