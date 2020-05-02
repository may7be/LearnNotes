package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/5/2
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring3 {
    public int lengthOfLongestSubstring(String s) {
        //两次循环应该可以搞定
        //1.
        if (s == null) {
            return 0;
        }
        //2.
        int len = s.length();
        List<Character> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (list.contains(s.charAt(j))) {
                    break;
                } else {
                    list.add(s.charAt(j));
                }
            }
            count = Math.max(count, list.size());
            //当count大于等于剩下字符串的长度时，不再遍历
            if (count >= len - 1 - i) {
                break;
            }
            list.clear();
        }
        return count;
    }

    public int lengthOfLongestSubstring2(String s) {
        //❌❌❌
        // 优化思路：第一种解法存在大量的重复运算。一次循环应该就搞得定
        //1.
        if (s == null) {
            return 0;
        }
        //2.
        char[] chars = s.toCharArray();
        int len = chars.length;
        List<Character> list = new ArrayList<>();
        int count = 0;
        int temp = 0;
        for (int i = 0; i < len; i++) {
            if (list.contains(chars[i])) {
                count = Math.max(count, list.size());
                if (count >= len - 1 - temp) {
                    break;
                }
                list.clear();
                temp = i;
            }
            list.add(chars[i]);
        }
        if (list.size() > 0) {
            count = Math.max(count, list.size());
        }
        return count;
    }
}
