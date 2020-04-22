package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author zhao on 2020/4/22
 * 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class NumberOfSubArrays1248 {
    /**
     * 1 <= nums.length ；1 <= k <= nums.length
     */
    public int numberOfSubArrays(int[] nums, int k) {
        //思路：遍历，记录奇数的索引
        //1.
        int len = nums.length;
        int count = 0;
        //记录奇数索引
        LinkedList<Integer> list = new LinkedList<>();
        int lastIndex = -1;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (num % 2 == 1) {
                if (list.size() == k) {
                    count += (list.getFirst() - lastIndex) * (i - list.getLast());
                    lastIndex = list.getFirst();
                    list.removeFirst();
                }
                list.add(i);
            }
        }
        if (list.size() == k) {
            count += (list.getFirst() - lastIndex) * (len - list.getLast());
        }
        return count;
    }

    @Test
    public void fun1() {
        int[] arr = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k = 2;
        System.out.println(numberOfSubArrays(arr, k));
    }
}
