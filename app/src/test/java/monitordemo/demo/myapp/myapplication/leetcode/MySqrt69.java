package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @Author zhao on 2020-03-03
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MySqrt69 {
    private int count = 0;

    public int mySqrt(int x) {
        //200 52
        //思路：对10取整，然后二分查找。效率取决于
        if (x == 0 || x == 1) {
            return x;
        }
        return findSqrt(x, 1, x / 2);

//        int t = 10;
//        while (true) {
//            if (x <= t * t) {
//                return findSqrt(x, t / 10 + 1, t);
//            }
//            t *= 2;
//        }
    }

    /**
     * 寻找x的平方根，范围为begin-end
     *
     * @param x
     * @param left
     * @param right
     * @return
     */
    private int findSqrt(int x, long left, long right) {
        int count = 0;
        //相乘时，注意越界问题
        while (left < right) {
            count++;
            long mid = (left + right) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                left = (int) (mid + 1);
            } else {
                right = (int) (mid - 1);
            }
        }
        System.out.println("count: " + count);
        return (int) (left * left <= x ? left : left - 1);
    }

    public int mySqrt2(int x) {
        //思路2: 递归,基于 mySqrt2(x) = 2 * mySqrt2(x/4)，
        //针对精确计算发现，这是个恒等式
        //但是发现小数无法操作
        if (x == 0) {
            return 0;
        }
        return (int) findSqrt((double) x);
    }

    private double findSqrt(double d) {
        DecimalFormat df = new DecimalFormat("0.0");
        String format = df.format(2 * findSqrt(d / 4));
        return Double.valueOf(format);
    }

    public int mySqrt22(int x) {
        //思路2: 递归,基于 mySqrt2(x) = 2 * mySqrt2(x/4)，但是/4取整过程中会遗弃掉1或者2或者3
        //所以，需要对余数进行判断
        //❌错误示范。其实应该判断x是否是4的幂次方，不是整个递归过程中都得加1
        if (x == 0) {
            return 0;
        }
        long t = 2 * mySqrt22(x / 4);
        if (x % 4 == 0) {
            System.out.println(x + ".sqrt: " + t);
            return (int) t;
        } else {
            System.out.println(x + ".sqrt: " + (int) ((t + 1) * (t + 1) <= x ? t + 1 : t));
            return (int) ((t + 1) * (t + 1) <= x ? t + 1 : t);
        }
    }

    public int mySqrt23(int x) {
        System.out.println("mySqrt23:" + (++count));
        //思路2.3: 递归,基于 mySqrt2(x) = 2 * mySqrt2(x/4)，
        //判断x是否是4的幂次方
        if (x == 0) {
            return 0;
        }
        //判断是否是4的幂次方太麻烦
        long t = 2 * mySqrt23(x / 4);
        return (int) ((t + 1) * (t + 1) <= x ? t + 1 : t);

    }

    public int mySqrt3(int x) {
        //思路3：牛顿法
        long a = x;
        int count = 0;
        while (a * a > x) {
            a = (a + x / a) / 2;
            count++;
        }
        System.out.println("count: " + count);
        return (int) a;
    }

    @Test
    public void fun() {
        System.out.println("begin:" + System.currentTimeMillis());
//        int x = 8192;
        int x = 2100000000;
        System.out.println(x + "_sqrt: " + mySqrt(x));
//        System.out.println(x + "_sqrt2: " + mySqrt2(x));
//        System.out.println(x + "_sqrt22: " + mySqrt22(x));
        System.out.println(x + "_sqrt23: " + mySqrt23(x));
        System.out.println(x + "_sqrt3: " + mySqrt3(x));

        System.out.println("end  :" + System.currentTimeMillis());
    }
}
