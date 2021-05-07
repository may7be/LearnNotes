package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 5/7/21
 */
public class CountPrimes204 {
    /**
     * 0 <= n <= 5 * 106
     */
    public int countPrimes(int n) {
        //还有埃及筛、线性筛法
        //暴力法: 会超时
        if (n <= 2) {
            return 0;
        }
        int count = 1;
        for (int i = 3; i < n; i++) {
            //依次判断i是否为质数
            //i是否为质数：是否不能被2->i的平方根的所有数整除
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int a) {
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
}
