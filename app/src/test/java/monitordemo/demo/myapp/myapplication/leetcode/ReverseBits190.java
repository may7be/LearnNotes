package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 5/8/21
 */
public class ReverseBits190 {
    @Test
    public void fun() {
        String s = "11111111111111111111111111111101";
        String s2 = String.valueOf(00000111011);
//        System.out.println(new StringBuilder(s).reverse());
//        int n = 10011111111;
//        System.out.println(reverseBits(s));
        int a = 0111011;
        for (int i = 0; i < 6; i++) {
            System.out.println(a & 1);
            a >>>= 1;
        }
    }

    public int reverseBits3(int n) {
        //还有一种分治法：
        return -1;
    }

    public int reverseBits2(int n) {
        //利用位运算，依次取n的最后一个二进制位，放到rev相反的位置即可
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            //1. n&1后，最后一位不变，其他位都是0；
            //2. 然后左移31-i位，然后 和rev进行｜运算即可
            rev |= (n & 1) << (31 - i);
            System.out.println("te: " + n);
            //由于是无符号的，所以不能用>>
            n >>>= 1;
        }
        return rev;
    }

    /**
     * 输入是一个长度为 32 的二进制字符串，而且是无符号的
     */
    public int reverseBits(int n) {
        //由于是无符号的，所以该方法只能处理31位的二进制字符，32位的会抛异常
        String s = Integer.toBinaryString(n);
        String reverse = new StringBuilder(s).reverse().toString();
        return Integer.valueOf(reverse, 2);
    }

    public int reverseBits(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return Integer.valueOf(reverse, 2);
    }


}
