package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2021/8/6
 */
public class ReverseLeftWords58 {

    /**
     * 1 <= k < s.length <= 10000
     */
    public String reverseLeftWords(String s, int n) {
        //切割、拼接即可
        int len = s.length();
        if (n >= len) {
            return s;
        }
        return s.substring(n, len) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        //不申请额外空间，在本串操作---核心思想就是反转
        int len = s.length();
        if (n >= len) {
            return s;
        }
        char[] chars = s.toCharArray();
        //分别反转[0,n-1]和[n,len-1]
        swap(chars, 0, n - 1);
        swap(chars, n, len - 1);
        System.out.println(chars);
        //再做整体反转
        swap(chars, 0, len - 1);
        return new String(chars);
    }

    public void swap(char[] chars, int l, int r) {
        while (l < r) {
            char te = chars[l];
            chars[l] = chars[r];
            chars[r] = te;
            l++;
            r--;
        }
    }

    @Test
    public void fun() {
        String s = "lrloseumgh";
        int n = 6;
        System.out.println("s:" + s);
        System.out.println(reverseLeftWords2(s, n));
    }
}
