package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-03-24
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateTrees96 {
    public int numTrees(int n) {
        //最简单暴力的递归法
        if (n == 0) {
            return 1;
        }
        return getTressNum(1, n);
    }

    private int getTressNum(int start, int end) {
        if (start > end) {
            //其实是null，应该算1
            return 1;
        }
        int sum = 0;
        //依次做根节点
        for (int i = start; i <= end; i++) {
            //左右分支个数
            int left = getTressNum(start, i - 1);
            int right = getTressNum(i + 1, end);
            sum += left * right;
        }
        return sum;
    }

    public int numTrees2(int n) {
        //优化1：递归法产生了大量的重复
        //getTreeNum根传入的具体参数无关，只跟end-start个数有关
        //所以，定义一个数组来记录0个元素，1个元素，2个元素对应的个数
        //实际这就是动态规划
        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                num[i] += num[j] * num[i - 1 - j];
            }
        }
        return num[n];
    }

    public int numTrees3(int n) {
        //最佳的卡塔兰公式： f(n) = (4n-2)/(n+1)f(n-1), n>=1, f(0)=1
        if (n == 0) {
            return 1;
        }
        long last = 1;
        for (int i = 1; i <= n; i++) {
            last = last * (4 * i - 2) / (i + 1);
        }
        return (int) last;
    }

    @Test
    public void fun() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i + "(动态规划): " + numTrees2(i));
            System.out.println(i + "(卡塔兰公式): " + numTrees3(i));
        }
    }
}
