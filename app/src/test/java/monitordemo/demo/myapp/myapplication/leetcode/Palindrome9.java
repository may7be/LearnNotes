package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author zhao on 2020-02-19
 */
public class Palindrome9 {
    public boolean isPalindrome(int x) {
        //1. 负数应该都不满足 2.转为字符串，反转，比对即可
        //问题：需要额外的空间 o(x.length)
        if (x < 0) {
            return false;
        }
        String num = String.valueOf(x);
        String reverseNum = new StringBuffer(num).reverse().toString();
        return num.equals(reverseNum);
    }

    public boolean isPalindrome2(int x) {
        //1. 0满足；2.负数应该都不满足; 末位是0，也必须是0； 3. 1234321， 强行反转
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverse = 0;

        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
            //这里应该有个阻隔机制
        }
        return reverse == x || reverse / 10 == x;
    }

    public boolean isPalindrome3(int x) {
        //此方法有问题
        //1. 0满足；2.负数应该都不满足; 末位是0，也必须是0； 3. 1234321， 强行反转或用栈来处理
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        //偶数位可以用栈处理，奇数位还是不行
        Stack<Integer> stack = new Stack<>();

        int temp = 0;
        while (x > 0) {
            temp = x % 10;
            x /= 10;
            if (stack.size() > 0 && temp == stack.peek()) {
                stack.pop();
            } else {
                stack.push(temp);
            }
        }
        return stack.size() <= 1;
    }

    @Test
    public void fun1() {
        for (int i = 0; i < 102; i++) {

            System.out.println(i + ": " + isPalindrome3(i));
        }

    }
}
