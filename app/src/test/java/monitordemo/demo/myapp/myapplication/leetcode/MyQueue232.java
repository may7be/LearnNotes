package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Stack;

/**
 * @Author zhao on 2021/11/26
 */
class MyQueue232 {

    private final Stack<Integer> stack1;
    private final Stack<Integer> stack2;
    private int first;

    public MyQueue232() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (empty()) {
            first = x;
        }
        stack1.push(x);
    }

    public int pop() {
        if (empty()) {
            return Integer.MIN_VALUE;
        }
        //将stack1中的元素倒入stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int pop = stack2.pop();
        //更新first
        if (!stack2.isEmpty()) {
            first = stack2.peek();
        }
        //将stack2中的元素倒回stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return pop;
    }

    public int peek() {
        if (empty()) {
            return Integer.MIN_VALUE;
        }
        return first;
    }

    public boolean empty() {
        return stack1.isEmpty();
    }
}
