package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import monitordemo.demo.myapp.myapplication.sort.Sort;

/**
 * @Author zhao on 2021/12/2
 */
public class FindRelativeRanks506 {

    /**
     * n == score.length
     * 1 <= n <= 104
     * 0 <= score[i] <= 106
     * score 中的所有值 互不相同
     */
    public String[] findRelativeRanks(int[] score) {
        //排序；然后依次获取pos即可
        int len = score.length;
        String[] arr = new String[len];
        //1. 排序
        int[] temp = Arrays.copyOf(score, len);
        Arrays.sort(temp);
        //2. 获取pos
        for (int i = 0; i < len; i++) {
            int j = Arrays.binarySearch(temp, score[i]);
            if (j == len - 1) {
                arr[i] = "Gold Medal";
            }else if (j == len - 2) {
                arr[i] = "Silver Medal";
            }else if (j == len - 3) {
                arr[i] = "Bronze Medal";
            }else{
                arr[i] = String.valueOf(len - j);
            }
        }
        return arr;
    }

    public String[] findRelativeRanks2(int[] score) {
        //1. 记录原始位置
        int len = score.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(score[i], i);
        }
        //2. 排序
        Arrays.sort(score);
        //3.
        String[] arr = new String[len];
        for (int j = 0; j < len; j++) {
            int pos = map.get(score[j]);
            if (j == len - 1) {
                arr[pos] = "Gold Medal";
            }else if (j == len - 2) {
                arr[pos] = "Silver Medal";
            }else if (j == len - 3) {
                arr[pos] = "Bronze Medal";
            }else{
                arr[pos] = String.valueOf(len - j);
            }
        }
        return arr;
    }

    @Test
    public void fun(){
        int[] score = {10,3,8,9,4};
//        int[] temp = Arrays.copyOf(score, score.length);
//        Arrays.sort(temp);
        System.out.println("score: "+ Arrays.toString(findRelativeRanks(score)));
//        System.out.println("temp: "+ Arrays.toString(temp));
    }
}
