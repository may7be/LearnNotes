package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/18
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class MaxArea11 {
    /**
     * n 个非负整数
     * n 的值至少为 2
     */
    public int maxArea(int[] height) {
        //暴力法：n*(n-1)/2，取出最大值
        int max = 0;
        int len = height.length;
        for (int i = 0; i < len - 1; i++) {
            int cur = height[i];
            if (cur == 0) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                int next = height[j];
                if (next != 0) {
                    max = Math.max(max, (j - i) * Math.min(cur, next));
                }
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        //思路2：双指针
        //基本想法就是：水的体积取决于高度较低的那个指针，所以移动较小的指针即可；
        //而相等时，如果移动一端的指针，则总体积肯定变小，所以需要同时移动
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int l = height[left];
            int r = height[right];
            max = Math.max(max, (right - left) * Math.min(l, r));
            if (l < r) {
                left++;
            } else if (l > r) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return max;
    }

}
