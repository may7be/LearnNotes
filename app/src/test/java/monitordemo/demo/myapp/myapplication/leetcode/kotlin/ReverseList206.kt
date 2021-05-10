package monitordemo.demo.myapp.myapplication.leetcode.kotlin


/**
 * @Author zhao on 5/10/21
 */
class ReverseList206 {
    /**
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     */

    fun reverseList2(head: ListNode?): ListNode? {
        //
        if (head?.next == null) {
            return head
        }
        val list = mutableListOf<ListNode>()
        var te = head
        while (te != null) {
            list.add(0, te)
            te = te.next
        }
        var last = list[0]
        val len = list.size - 1
        for (i in 1..len) {
            last.next = list[i]
            last = list[i]
        }
        last.next = null
        return list[0]
    }

    fun reverseList(head: ListNode?): ListNode? {
        //暴力法: 比较耗时200ms, 而同样的算法java只需要3ms左右
        val list = mutableListOf<Int>()
        var te = head
        while (te != null) {
            list.add(0, te.value)
            te = te.next
        }
        val zero = ListNode(0)
        var temp = zero
        for (i in list) {
            temp.next = ListNode(i)
            temp = temp.next!!
        }
        return zero.next
    }

    class ListNode(var value: Int) {
        var next: ListNode? = null
    }
}