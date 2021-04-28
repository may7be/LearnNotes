package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 4/28/21
 */
public class JudgeSquareSum635 {
    @Test
    public void fun() {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i + " canSqrt: " + canSqrt(i));
//        }
//        2147483647
        int c = 999999999;
        judgeSquareSum2(c);
        System.out.println("4.0 == 4: " + (4.0 == 4));
        System.out.println("4.1 == 4: " + (4.1 == 4));
    }

    public boolean judgeSquareSum4(int c) {
        //⚠️⚠️耗时5ms
        //对mid求平方根，然后向上取整
        int maxSqrt = (int) Math.ceil(Math.sqrt(c / 2f));
        for (int i = 0; i <= maxSqrt; i++) {
            double b = Math.sqrt(c - i * i);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum3(int c) {
        //⚠️⚠️耗时2ms
        //定义两个指针a和b
        int a = 0;
        int b = (int) Math.sqrt(c);
        while (a <= b) {
            int d = a * a + b * b;
            if (d == c) {
                return true;
            } else if (d < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        //⚠️⚠️耗时12ms
        //穷尽所有组合，拆分c(0和c、1和c-1、2和c-2...)，依次比对即可。
        //事实证明会超时，因为匀速拆分，很明显多了很多冗余运算，应该这么拆：0和c、1和c-1、4和c-4、9和c-9、16和c-16
        int mid = c / 2;
        //对mid求平方根，然后向上取整
        int maxSqrt = (int) Math.ceil(Math.sqrt(mid));
        for (int i = 0; i <= maxSqrt; i++) {
            int pow = (int) Math.pow(i, 2);
            if (canSqrt(c - pow)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 非负整数
     * 0 <= c <= 231 - 1
     * 0可以；俩数相同也可以。
     */

    public boolean judgeSquareSum(int c) {
        //⚠️⚠️耗时20ms
        //穷尽所有组合，拆分c(0和c、1和c-1、2和c-2...)，依次比对即可。
        //事实证明会超时，因为匀速拆分，很明显多了很多冗余运算，应该这么拆：0和c、1和c-1、4和c-4、9和c-9、16和c-16
        int mid = c / 2;
        for (int i = 0; i <= mid; i++) {
            if (canSqrt(i) && canSqrt(c - i)) {
                return true;
            }
        }
        return false;
    }

    private boolean canSqrt(int a) {
        return Math.sqrt(a) % 1 == 0;
    }
}
