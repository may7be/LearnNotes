package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author zhao on 2021/8/3
 */
class FourSumCount454 {

    /**
     * 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间
     */
    public int fourSumCount4(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //还是暴力法，少两层嵌套
        if (nums1.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int i1 : nums2) {
                map.put(i + i1, map.getOrDefault(i + i1, 0) + 1);
            }
        }
        int count = 0;
        for (int i : nums3) {
            for (int i1 : nums4) {
                if (map.containsKey(-(i + i1))) {
                    count += map.get(-(i + i1));
                }
            }
        }
        return count;
    }

    public int fourSumCount3(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //还是暴力法，只不过少了一层嵌套，最后一个nums存入map, 还是超时
        if (nums1.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums4) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int count = 0;
        for (int i : nums1) {
            for (int i1 : nums2) {
                for (int i2 : nums3) {
                    int key = -(i + i1 + i2);
                    if (map.containsKey(key)) {
                        count += map.get(key);
                    }
                }
            }
        }
        return count;
    }

    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //暴力法：嵌套4层循环, 但是先排序，还是会超时
        if (nums1.length == 0) {
            return 0;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        int count = 0;
        for (int i : nums1) {
            for (int i1 : nums2) {
                for (int i2 : nums3) {
                    for (int i3 : nums4) {
                        int sum = i + i1 + i2 + i3;
                        if (sum == 0) {
                            count++;
                        } else if (sum > 0) {
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //暴力法：嵌套4层循环, 果然是会超时
        if (nums1.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i : nums1) {
            for (int i1 : nums2) {
                for (int i2 : nums3) {
                    for (int i3 : nums4) {
                        if (i + i1 + i2 + i3 == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
