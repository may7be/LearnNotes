package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 5/8/21
 */
public class HammingWeight191 {
    public int hammingWeight3(int n) {
        //位运算优化：观察到n&(n-1)会让n中少一个1
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        //暴力法：也可以用n依次和2的i次方进行与运算，结果为0，说明当前二进制位为0
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1 << i) != 0) {
                count++;
            }
        }
        return count;
    }

    public int hammingWeight(int n) {
        //暴力法：依次取二进制位，和1进行比对即可
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }
}
