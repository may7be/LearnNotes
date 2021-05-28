package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 5/28/21
 */
public class TotalHammingDistance477 {
    public int totalHammingDistance(int[] nums) {
        //暴力法：耗时2671ms
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                sum += Integer.bitCount(nums[i] ^ nums[j]);
            }
        }
        return sum;
    }

    public int totalHammingDistance2(int[] nums) {
        //会超时
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int xor = nums[i] ^ nums[j];
                while (xor != 0) {
                    xor &= xor - 1;
                    sum++;
                }
            }
        }
        return sum;
    }

    public int totalHammingDistance3(int[] nums) {
        //暴力法：记录[a,b]对应的汉明距离，防止重复运算
        //还是会超时
        int sum = 0;
        int len = nums.length;
        Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                Map<Integer, Integer> key = new HashMap<>();
                key.put(nums[i], nums[j]);
                if (map.containsKey(key)) {
                    sum += map.get(key);
                } else {
                    int count = Integer.bitCount(nums[i] ^ nums[j]);
                    sum += count;
                    map.put(key, count);
                }
            }
        }
        return sum;
    }

    public int totalHammingDistance4(int[] nums) {
        // 依次统计最后一位1和0的个数
        //用时20ms
        int sum = 0;
        int len = nums.length;
        while (true) {
            int a = 0, s = 0;
            for (int i = 0; i < len; i++) {
                int num = nums[i];
                if ((num & 1) == 1) {
                    a++;
                }
                nums[i] = num >> 1;
                s += nums[i];
            }
            sum += a * (len - a);
            if (s == 0) {
                return sum;
            }
        }
    }

    public int totalHammingDistance4V2(int[] nums) {
        // 依次统计最后一位1和0的个数
        int sum = 0;
        int len = nums.length;
        while (true) {
            int a = 0, s = 0;
            for (int i = 0; i < len; i++) {
                int num = nums[i];
                a += num & 1;
                nums[i] = num >> 1;
                s += nums[i];
            }
            sum += a * (len - a);
            if (s == 0) {
                return sum;
            }
        }
    }

    /**
     * 数组中元素的范围为从 0到 10^9。
     * 数组的长度不超过 10^4。
     */
    public int totalHammingDistance5(int[] nums) {
        //统计各比特位1的个数
        //用时 5ms
        int sum = 0, len = nums.length;
        //10^9 < 2^30, 说明最多有30位
        for (int i = 0; i < 30; i++) {
            int a = 0;
            for (int num : nums) {
                a += ((num >> i) & 1);
            }
            sum += a * (len - a);
        }
        return sum;
    }
}
