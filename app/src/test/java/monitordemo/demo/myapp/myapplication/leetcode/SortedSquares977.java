package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author zhao on 7/6/21
 */
public class SortedSquares977 {

    /**
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 已按 非递减顺序 排序
     * 进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
     */
    public int[] sortedSquares(int[] nums) {
        //暴力法：先计算，然后排序，但是时间复杂度不符合要求
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            res[i] = num * num;
        }
        Arrays.sort(res);
        return res;
    }

    public int[] sortedSquares2(int[] nums) {
        //暴力法没有利用nums已经有序这个规则，比如[-4,-1,0,3,10]
        //可以用一个指针指向数组的头部，另一个指针指向数组的尾部，然后比对就可以获取最大值；然后移动指针，再次比对；
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int k = len - 1;
        int[] res = new int[len];
        while (start < end) {
            int a = nums[start] * nums[start];
            int b = nums[end] * nums[end];
            if (a < b) {
                res[k] = b;
                k--;
                end--;
            } else if (a == b) {
                res[k] = b;
                res[k-1] = b;
                k -= 2;
                start++;
                end--;
            }else {
                res[k] = a;
                k--;
                start++;
            }
        }
        //start == end，说明是最小值；start > end，说明已经结束
        if (start == end) {
            res[0] = nums[start] * nums[start];
        }
        return res;
    }

    @Test
    public void fun(){
        int[] nums = {-4,-1,0,3,10};
        System.out.println(Arrays.toString(nums));
        int[] ints = sortedSquares2(nums);
        System.out.println("====");
        System.out.println(Arrays.toString(ints));
    }

}
