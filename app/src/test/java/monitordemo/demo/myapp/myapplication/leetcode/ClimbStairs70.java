package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-03-05
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbStairs70 {
    public long climbStairs(int n) {
        //1. n正整数 2.遍历：每一步可能性(1或者2)相乘，所以需要判断>=2
        //这种题要总结规律的，硬想很难想，而且容易出错
        //❌结果发现超出时间限制，还得优化。因为重复运算太多
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public long climbStairs2(int n) {
        //记下来前后两个数即可
        if (n == 1) {
            return 1;
        }
        long a = 1, b =2, t;
        for (int i = 3; i < n + 1; i++) {
            t = a;
            a = b;
            b = b + t;
        }
        return b;
    }

    @Test
    public void fun() {
        for (int i = 1; i < 200; i++) {
//            System.out.println(i + "   : " + climbStairs(i));
            System.out.println(i + "(2): " + climbStairs2(i));
        }
    }
}
