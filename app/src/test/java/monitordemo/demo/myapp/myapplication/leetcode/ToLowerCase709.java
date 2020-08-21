package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020/8/21
 */
public class ToLowerCase709 {
    public String toLowerCase(String str) {
        //1. 判空
        if (str == null) {
            return null;
        }
        //2. 遍历即可：主要根据ASCII码对照表，大写字母对应的十进制比小写字母刚好小32
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            //如果大写，+32即转为小写；如果是小写直接返回即可
            if (aChar >= 'A' && aChar <= 'Z') {
                sb.append((char) (aChar + 32));
            } else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }

    public String toLowerCase2(String str) {
        //优化：不用StringBuilder
        //1. 判空
        if (str == null) {
            return null;
        }
        //2. 遍历即可：主要根据ASCII码对照表，大写字母对应的十进制比小写字母刚好小32
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            //如果大写，+32即转为小写；如果是小写直接返回即可
            if (aChar >= 'A' && aChar <= 'Z') {
                aChar += 32;
            }
        }
        return String.valueOf(chars);
    }

    @Test
    public void fun() {
        String str = "AGdA@GgD";
        System.out.println(toLowerCase(str));

    }
}
