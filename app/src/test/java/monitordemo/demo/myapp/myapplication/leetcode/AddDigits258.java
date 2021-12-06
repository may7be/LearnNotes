package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2021/12/6
 */
public class AddDigits258 {
    /**
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     * <p>
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     */
    public int addDigits(int num) {
        //连续运算即可
        int sum = num;
        while (sum / 10 != 0) {
            num = sum;
            sum = 0;
            while (num != 0) {
                sum += (num % 10);
                num /= 10;
            }
        }
        return sum;
    }

    @Test
    public void fun() {
        int num = 38;
        System.out.println("ans: " + addDigits(num));
    }
}
