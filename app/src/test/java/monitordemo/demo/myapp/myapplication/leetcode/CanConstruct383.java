package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 2021/8/3
 */
class CanConstruct383 {
    /**
     * 杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        //用数组代替map：因为map要维护红黑树或者哈希表，而且还要做哈希函数，是费时的！数据量大的话就能体现出来差别了
        char[] arr = new char[26];
        for (char c : magazine.toCharArray()) {
            arr[c - 97] += 1;
        }
        for (char c : ransomNote.toCharArray()) {
            if (arr[c - 97] > 0) {
                arr[c - 97] -= 1;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        //将magazine存入map，遍历ransomNote比对即可, 比较耗时
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (map.containsKey(c) && map.get(c) > 0) {
                //map次数-1
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
