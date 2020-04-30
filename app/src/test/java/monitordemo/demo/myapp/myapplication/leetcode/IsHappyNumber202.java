package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashSet;

/**
 * @Author zhao on 2020/4/30
 * 202. 快乐数
 * 额外补充一点数学小知识：
 * 1.有一个循环： 44→16→37→58→89→145→42→20→4。所有其他数字都在进入这个循环的链上，或者在进入 11 的链上。
 * 2.不会有平方和不断变大下去的可能
 */
public class IsHappyNumber202 {
    private HashSet<Integer> set = new HashSet<>();

    public boolean isHappy(int n) {
        //暴力法：依次取最后一位；记录出现的平方和，如果出现了已出现过的，则认为进入循环
        //1.
        set.add(n);
        int count = 0;
        while (n != 0) {
            int a = n % 10;
            count += a * a;
            n /= 10;
        }
        if (count == 1) {
            return true;
        }
        if (set.contains(count)) {
            return false;
        }
        return isHappy(count);
    }
}
