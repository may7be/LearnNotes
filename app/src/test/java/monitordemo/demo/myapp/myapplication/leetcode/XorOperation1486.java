package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 5/7/21
 */
public class XorOperation1486 {
    /**
     * 1 <= n <= 1000
     * 0 <= start <= 1000
     * n == nums.length
     */
    public int xorOperation(int n, int start) {
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= start + 2 * i;
        }
        return xor;
    }

    public int xorOperation2(int n, int start) {
        //根据异或数学运算规律：∀i∈Z，有 4i ^ (4i+1)  ^ (4i+2) ^(4i+3) = 0
        //https://leetcode-cn.com/problems/xor-operation-in-an-array/solution/shu-zu-yi-huo-cao-zuo-by-leetcode-solution/

        return -1;
    }
}
