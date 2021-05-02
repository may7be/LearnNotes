package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author zhao on 5/3/21
 */
public class TitleToNumber171 {
    @Test
    public void fun() {
        System.out.println(titleToNumber("AB"));
    }

    public int titleToNumber2(String columnTitle) {
        //转换即可
        int len = columnTitle.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int te = columnTitle.charAt(i) - 64;
            sum += Math.pow(26, len - 1 - i) * te;
        }
        return sum;
    }

    public int titleToNumber(String columnTitle) {
        //存入map
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 65; i <= 90; i++) {
            map.put(String.valueOf((char) i), i - 64);
        }
        //转换即可
        int len = columnTitle.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int te = map.get(String.valueOf(columnTitle.charAt(i)));
            sum += Math.pow(26, len - 1 - i) * te;
        }
        return sum;
    }
}
