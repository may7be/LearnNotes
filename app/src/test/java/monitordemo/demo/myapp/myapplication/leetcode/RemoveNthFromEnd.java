package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 7/23/21
 */
public class RemoveNthFromEnd {

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     * <p>
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //最笨的方法：记录每个节点，然后删除即可
        if (head == null || head.next == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int size = list.size();
        if (n == 1) {
            list.get(size - 2).next = null;
            return list.get(0);
        } else if (n == size) {
            return list.get(1);
        } else {
            list.get(size - n - 1).next = list.get(size - n + 1);
            return list.get(0);
        }
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        //依次遍历，记录前中后三个节点，还有记录节点数
        //问题是一次扫描，怎么能知道到哪才是要删除的节点呢？可以用快慢指针
        //快指针永远比慢指针快n个节点，然后当快指针走到最后的时候，慢指针.next就刚好是要删除的指针
        if (head == null || head.next == null) {
            return null;
        }
        //1. 定义快慢指针
        ListNode zero = new ListNode(-1);
        zero.next = head;
        ListNode slow = zero, fast = zero;
        //2. 移动fast到最后一个节点, 前n次只移动fast
        int count = 0;
        boolean flag = true;
        while (fast.next != null) {
            count++;
            if (count > n) {
                flag = false;
                slow = slow.next;
            }
            fast = fast.next;
        }
        //3. 删除slow.next节点即可
        slow.next = slow.next.next;
        //4. slow未移动时，头节点为slow.next
        return flag ? slow.next : head;
    }
}
