package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 2021/12/6
 */
class MissingNumber268 {

    /**
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     */
    public int missingNumber(int[] nums) {
        //排序，依次比对即可
        Arrays.sort(nums);
        int len = nums.length;
        int te = nums[0];
        //1. 不以0开头
        if (te != 0) {
            return 0;
        }
        //2. 正常比对
        for (int i = 1; i < len; i++) {
            te++;
            if (nums[i] != te) {
                return te;
            }
        }
        return len;
    }

    public int missingNumber2(int[] nums) {
        //排序，依次比对下标和元素即可
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return len;
    }

    public int missingNumber3(int[] nums) {
        //存入map，然后循环contains即可
        Map<Integer, Boolean> map = new HashMap();
        for (int num : nums) {
            map.put(num, true);
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(i)) {
                return i;
            }
        }
        return len;
    }

    public int missingNumber4(int[] nums) {
        //异或位运算：a ^ a = 0; a ^ 0 = a
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        int len = nums.length;
        for (int i = 0; i <= len; i++) {
            ans ^= i;
        }
        return ans;
    }
}
