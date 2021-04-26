package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 4/26/21
 */
public class SingleNumber136 {
    public int singleNumber2(int[] nums) {
        //利用相同的数异或位运算后为0; 0和任何数异或运算后都是原数；异或运算满足交换率
        int te = 0;
        for (int num : nums) {
            te ^= num;
        }
        return te;
    }

    /**
     * 非空整数数组
     */
    public int singleNumber(int[] nums) {
        //先排序
        Arrays.sort(nums);
        //每次取两个值，进行比对
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            i++;
            if (i < nums.length) {
                if (temp != nums[i]) {
                    return temp;
                }
            } else {
                return temp;
            }
        }
        return -1;
    }
}
