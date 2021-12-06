package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 2021/12/6
 */
class WordPattern290 {
    /**
     * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
     */
    public boolean wordPattern(String pattern, String s) {
        //依次将pattern对应的word存入map
        int len = pattern.length();
        String[] split = s.split(" ");
        if (len != split.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(split[i])) {
                    return false;
                }
            } else {
                //⚠️是一对一的映射，所以也要保证value的不重复
                if (map.containsValue(split[i])) {
                    return false;
                } else {
                    map.put(pattern.charAt(i), split[i]);
                }
            }
        }
        return true;
    }
}
