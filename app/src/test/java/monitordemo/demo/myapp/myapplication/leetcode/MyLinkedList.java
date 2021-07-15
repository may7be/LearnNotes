package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 7/15/21
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class MyLinkedList {

    private List<ListNode> list;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        list = new ArrayList<>();
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (isValid(index)) {
            return list.get(index).val;
        }
        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (!list.isEmpty()) {
            node.next = list.get(0);
        }
        list.add(0, node);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (!list.isEmpty()) {
            list.get(list.size() - 1).next = node;
        }
        list.add(node);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        index = Math.max(0, index);
        if (index == 0) {
            addAtHead(val);
        } else if (index < list.size()) {
            ListNode node = new ListNode(val);
            list.get(index - 1).next = node;
            node.next = list.get(index);
            list.add(index, node);
        } else if (index == list.size()) {
            addAtTail(val);
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (isValid(index)) {
            list.remove(index);
        }
    }

    private boolean isValid(int index) {
        return index >= 0 && index <= list.size() - 1;
    }
}
