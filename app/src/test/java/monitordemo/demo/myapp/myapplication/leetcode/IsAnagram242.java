package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;

/**
 * @Author zhao on 7/29/21
 */
public class IsAnagram242 {

    /**
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * <p>
     * 1 <= s.length, t.length <= 5 * 104
     * s 和 t 仅包含小写字母
     *  
     * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //1. 先记录一个字符串每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //2. 再遍历另一个字符串，如果map中的key没有该字符，直接return false; 有的话，value-1
        for (int i = 0; i < len; i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
            }else {
                return false;
            }
        }
        //3. 判断map中的每个value是否都为0
        for (Character c : map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }
        return true;
    }
}
