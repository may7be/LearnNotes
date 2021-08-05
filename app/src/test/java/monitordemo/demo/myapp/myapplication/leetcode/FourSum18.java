package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zhao on 2021/8/4
 */
public class FourSum18 {
    @Test
    public void fun() {
//        [2,2,2,2,2] 8
//        [-3,-1,0,2,4,5]2
        int[] nums = {-3, -1, 0, 2, 4, 5};
        int target = 2;
        System.out.println(fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //双指针法：降低一层嵌套-----中心思想就是所有元素一定要移动到跟之前不同的的位置
        List<List<Integer>> lists = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            return lists;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                //移动到元素不同的位置
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = len - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    System.out.println("i j l r:" + i + "," + j + "," + l + "," + r + "sum:" + sum);
                    if (sum == target) {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        //移动到元素不同的位置
                        do {
                            l++;
                        } while (l < r && nums[l] == nums[l - 1]);
                        do {
                            r--;
                        } while (l < r && nums[r] == nums[r + 1]);
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return lists;
    }

}
