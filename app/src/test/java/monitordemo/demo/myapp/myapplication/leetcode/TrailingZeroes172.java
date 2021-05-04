package monitordemo.demo.myapp.myapplication.leetcode;

import java.math.BigInteger;

/**
 * @Author zhao on 5/4/21
 */
public class TrailingZeroes172 {
    /**
     * 你算法的时间复杂度应为 O(log n) 。
     * 0-10000
     */
    public int trailingZeroes2(int n) {
        //通过规律发现，其实阶乘的结果尾部包含几个零，取决于有几个5
        //比如10的阶乘里有2个5，20里4个5，25里6个5；
        //并且每多一个25就多1个，比如25多1个，125多2
        int count = 0;
        while (n / 5 > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public int trailingZeroes(int n) {
        //暴力法，直接计算：会超时
        //注意数据类型
        BigInteger a = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            a = a.multiply(BigInteger.valueOf(i));
        }
        int count = 0;
        while (a.mod(BigInteger.valueOf(10)).equals(BigInteger.valueOf(0))) {
            count++;
            a = a.divide(BigInteger.valueOf(10));
        }
        return count;
    }
}
