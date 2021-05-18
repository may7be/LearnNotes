package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/18/21
 */
public class CountTriplets1442 {
    /**
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
     * 0 <= i < j <= k < arr.length
     */
    public int countTriplets4(int[] arr) {
        //时间复杂度为O(n)也可以，但是有点麻烦

        return -1;
    }

    public int countTriplets3(int[] arr) {
        //暴力法：其实不用枚举j，枚举ik组合即可
        //同时从[i,k]的异或结果为0，可推导出[0,i-1] = [0,k],而[0,i]的结果是可算的
        int len = arr.length;
        //依次记录[0, i]的异或结果
        int[] ans = new int[len + 1];
        ans[0] = 0;
        for (int i = 0; i < len; i++) {
            ans[i + 1] = ans[i] ^ arr[i];
        }
        //暴力枚举即可
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                if (ans[i] == ans[k + 1]) {
                    count += k - i;
                }
            }
        }
        return count;
    }

    public int countTriplets2(int[] arr) {
        //暴力法：枚举每一个三元组，然后比对即可
        //同时从[i,k]的异或结果为0，可推导出[0,i-1] = [0,k],而[0,i]的结果是可算的
        int len = arr.length;
        //依次记录[0, i]的异或结果
        int[] ans = new int[len + 1];
        ans[0] = 0;
        for (int i = 0; i < len; i++) {
            ans[i + 1] = ans[i] ^ arr[i];
        }
        //暴力枚举即可
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j; k < len; k++) {
                    if (ans[i] == ans[k + 1]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int countTriplets(int[] arr) {
        //从左向右依次计算^结果，直到等于0, 得到start、end, 要求end > start
        //则[start, end]中的三元组数目就很好算了
        //可优化方向：可以双指针同时左右开算; 异或结果可能也需要保存下
        int count = 0;
        //以start为i的索引需要记录下来
        List<Integer> startList = new ArrayList<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            //已经计算过的start索引，就不再计算了
            if (startList.contains(i)) {
                continue;
            }
            int result = arr[i];
            //记录异或结果为0的(索引+1)
            List<Integer> list = new ArrayList<>();
            for (int k = i + 1; k < len; k++) {
                result ^= arr[k];
                if (result == 0) {
                    count += k - i;
                    //计算已有索引集和k的差值
                    for (Integer kIn : list) {
                        count += k - kIn;
                    }
                    list.add(k + 1);
                }
            }
            startList.addAll(list);
        }
        return count;
    }

}
