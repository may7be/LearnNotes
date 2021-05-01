package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author zhao on 5/2/21
 */
public class ConvertToTitle168 {
    @Test
    public void fun() {
        System.out.println(convertToTitle(52));
    }

    public String convertToTitle(int columnNumber) {
        //其实就是10进制转26进制, 依次对26取余，倒叙拼起来即可
        //存入map
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 65; i <= 90; i++) {
            map.put(i - 64, Character.toString((char) i));
        }
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            int a = columnNumber % 26;
            //余数为0时，单独处理
            if (a == 0) {
                sb.append("Z");
                columnNumber /= 26;
                columnNumber--;
            } else {
                sb.append(map.get(a));
                columnNumber /= 26;
            }

        }
        return sb.reverse().toString();
    }
}
