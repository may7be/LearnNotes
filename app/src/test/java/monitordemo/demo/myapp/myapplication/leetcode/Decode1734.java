package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 5/11/21
 */
public class Decode1734 {
    @Test
    public void fun() {
        int[] arr = {5, 6, 7};
        int last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            System.out.println(last ^ arr[i]);
            last = arr[i];
        }
    }

    /**
     * 关键在于「前n个自然数」这个条件
     */
    public int[] decode(int[] encoded) {
        //n是奇数
        //思路：求得start,即可依次求得每个数
        //而start需要两步：1. 先求countN = arr[0]^...arr[n]; 2.再求oddN = arr[1]^...arr[n]即可
        //而arr[1]^...arr[n]正好等于encoded中的奇数项异或
        int len = encoded.length;
        int[] perm = new int[len + 1];
        int countN = 0;
        for (int i = 1; i <= len + 1; i++) {
            countN ^= i;
        }
        int oddN = 0;
        for (int i = 1; i < len; i += 2) {
            oddN ^= encoded[i];
        }
        //故perm[0]= countN ^ oddN
        perm[0] = countN ^ oddN;
        for (int i = 0; i < len; i++) {
            perm[i + 1] = encoded[i] ^ perm[i];
        }
        return perm;
    }
}
