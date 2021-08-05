package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2021/8/5
 */
public class ReplaceSpace05 {

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 0 <= s 的长度 <= 10000
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public String replaceSpace2(String s) {
        int len = s.length();
        if (len <= 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (" ".equals(String.valueOf(s.charAt(i)))) {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    @Test
    public void fun() {
        String s = "We   are happy.";
        System.out.println(replaceSpace(s));
    }
}
