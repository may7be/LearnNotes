package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 5/27/21
 */
public class HammingDistance461 {
    @Test
    public void fun() {
        int a = 0;
        System.out.println(a >> 1);
    }

    public int hammingDistance(int x, int y) {
        //异或后转二进制串，计算1的个数
        int res = x ^ y;
        String s = Integer.toBinaryString(res);
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    public int hammingDistance2(int x, int y) {
        // 转为二进制串，然后从低位依次比对，注意串的长度不同。
        return 0;
    }

    public int hammingDistance3(int x, int y) {
        // 取最低二进制位低数进行比对
        int count = 0;
        while (x != 0 || y != 0) {
            if ((x & 1) != (y & 1)) {
                count++;
            }
            x >>= 1;
            y >>= 1;
        }
        return count;
    }

    public int hammingDistance4(int x, int y) {
        //异或后转二进制串，计算1的个数
        //计算个数可以直接用系统api
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance5(int x, int y) {
        //  Brian Kernighan 算法进行优化，具体地，该算法可以被描述为这样一个结论：记 f(x)表示 x 和 x-1进行与运算所得的结果
        //  （即  f(x)=x & (x−1)），那么 f(x)  恰为 x 删去其二进制表示中最右侧的 1 的结果。
        int res = x ^ y;
        int count = 0;
        while (res != 0) {
            res &= res - 1;
            count++;
        }
        return count;
    }


}
