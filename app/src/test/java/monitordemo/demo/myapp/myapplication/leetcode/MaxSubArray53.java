package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-02-29
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray53 {
    public int maxSubArray(int[] nums) {
        //1. 判空 2. 遍历：<=0的直接放弃；跟下一项相加，<=0放弃；
        //nums至少一个元素
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int count = nums[0];
        int temp = nums[0];
        //[-1,1,2,1] [-2,3,1,3]
        for (int i = 1; i < length; i++) {
            int n = nums[i];
            count += n;
            //更新较大值：temp count n
            temp = temp > count ? temp : count;
            temp = temp > n ? temp : n;

            //count<=0, 需要跟它的上一位进行比对; count <n，说明可以从n开始重新计算；>=n, 就无所谓了
            if (count <= 0) {
                count = nums[i - 1] < n ? n : 0;
            }
            if (count < n) {
                count = n;
            }
            System.out.println("temp: " + temp + ", count: " + count);

//            if (count == 0) {
//                if (n > 0) {
//                    count += n;
//                }
//            } else {
//                count += n;
//                //取 和0比的较大值
//                count = count > 0 ? count : 0;
//
//            }
        }
        return temp;
    }

    public int maxSubArray2(int[] nums) {
        //经验是： 不断地试错
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int count = nums[0];
        int max = nums[0];
        //[-1,1,2,1] [-2,3,1,3]
        for (int i = 1; i < length; i++) {
            int n = nums[i];
            count += n;
            //更新较大值：temp count n
            max = max > count ? max : count;
            max = max > n ? max : n;

            //count<=0, 需要跟它的上一位进行比对; count <n，说明可以从n开始重新计算；>=n, 就无所谓了
            if (count <= 0) {
                count = nums[i - 1] < n ? n : 0;
            }
            if (count < n) {
                count = n;
            }
            System.out.println("temp: " + max + ", count: " + count);
        }
        return max;
    }

    public int maxSubArray3(int[] nums) {
        //贪心算法：使用单个数组来查找最大或者最小元素或总和的问题，此算法都可在线性时间内解决
        //每一步都是最优方案，到最后就是全局最优
        //最关键的是：当前位置之前的最大和。如果加了当前元素之后，还没当前元素大，说明最大和即为当前元素.(加之前先比较count 和 0的大小)
        int length = nums.length;
        int count = nums[0];
        int max = nums[0];
        //[-1,1,2,1] [-2,3,1,3]
        for (int i = 1; i < length; i++) {
            count = Math.max(count, 0) + nums[i];
            max = Math.max(max, count);
            System.out.println("max: " + max + ", count: " + count);
        }
        return max;
    }

    public int maxSubArray4(int[] nums) {
        //思路：依次寻找最大子链，然后记录max即可
        //一个元素时，最大子链就是自身，max就是第一个值
        //当有第二个元素时，首先看记录的chainCount是否大于0，不大于0的话，就可抛弃前面的子链了；然后更新max
        //当有第三个元素时，还是看chainCount,大于0的话，直接加上；然后更新max
        //当有第四个元素时，重复步骤2
        //[-2,1,-3,4,-1,2,1,-5,4],
        //[-2,1]
        if (nums == null) {
            return 0;
        }
        int chain = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            chain = chain > 0 ? chain + num : num;
            max = Math.max(chain, max);
        }
        return Math.max(max, chain);
    }

    @Test
    public void fun() {
        int[] nums1 = new int[]{-1, -12, -1, -2, -3};
        int[] nums2 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums3 = new int[]{-1, 1, 2, 1};
        int[] nums4 = new int[]{-2, 3, 1, 3};

        System.out.println("max: " + maxSubArray3(nums4));
    }
}
