package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/11/25
 */
class IsPowerOfTwo231 {
    /**
     * 整数 n
     * -231 <= n <= 231 - 1
     * 进阶：你能够不使用循环/递归解决此问题吗？
     */
    public boolean isPowerOfTwo(int n) {
        //递归法
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        return isPowerOfTwo(n / 2);
    }
}
