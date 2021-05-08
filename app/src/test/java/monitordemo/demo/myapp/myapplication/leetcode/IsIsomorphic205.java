package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;

/**
 * @Author zhao on 5/8/21
 */
public class IsIsomorphic205 {
    /**
     * 可以假设 s 和 t 长度相同。
     * 要求：不同字符串不能映射到同一字符串，相同字符串只能映射到同一个字符串
     * s2t，就代表同构，至于t能否映射到s并不关心
     */
    public boolean isIsomorphic3(String s, String t) {
        //可以换数组试试
        int len = s.length();
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        return false;
    }

    public boolean isIsomorphic2(String s, String t) {
        //一个map也能搞定。但是更耗时了
        HashMap<Character, Character> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char k = s.charAt(i);
            char v = t.charAt(i);
            if (map.containsValue(v)) {
                if (!map.containsKey(k)) {
                    return false;
                }
            }
            if (map.containsKey(k)) {
                if (map.get(k) != v) {
                    return false;
                }
            } else {
                map.put(k, v);
            }
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        //map1保证相同字符串只能映射到同一字符串，map2保证不同字符串映射到不同字符串
        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char k = s.charAt(i);
            char v = t.charAt(i);
            if (map.containsValue(v)) {
                if (!map.containsKey(k)) {
                    return false;
                }
            }
            if (map.containsKey(k)) {
                if (map.get(k) != v) {
                    return false;
                }
            } else {
                map.put(k, v);
            }
            if (map2.containsKey(v)) {
                if (map2.get(v) != k) {
                    return false;
                }
            } else {
                map2.put(v, k);
            }
        }
        return true;
    }
}
