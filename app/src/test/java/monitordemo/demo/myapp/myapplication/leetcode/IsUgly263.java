package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/6
 */
class IsUgly263 {
    /**
     * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     * <p>
     * -231 <= n <= 231 - 1
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        //递归运算即可
        if (n % 2 == 0 && isUgly(n / 2)) {
            return true;
        }
        if (n % 3 == 0 && isUgly(n / 3)) {
            return true;
        }
        return n % 5 == 0 && isUgly(n / 5);
    }

    public boolean isUgly2(int n) {
        //若 n 是丑数，则 n 可以写成 n = 2^a * 3^b * 5^c 的形式，其中 a,b,c 都是非负整数
        //所以只需要对n进行不断的整除，看最后的结果是否为1即可
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n %5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
