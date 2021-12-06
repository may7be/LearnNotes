package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhao on 2021/12/6
 */
public class TruncateSentence1816 {
    /**
     * 1 <= s.length <= 500
     * k 的取值范围是 [1,  s 中单词的数目]
     * s 仅由大小写英文字母和空格组成
     * s 中的单词之间由单个空格隔开
     * 不存在前导或尾随空格
     */
    public String truncateSentence(String s, int k) {
        //1. 切割
        String[] s1 = s.split(" ");
        //2. 拼接
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(s1[i]);
            if (i < k - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String truncateSentence2(String s, int k) {
        //统计空格出现的次数即可
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 32) {
                k--;
                if (k == 0) {
                    return s.substring(0, i);
                }
            }
        }
        return s;
    }

    @Test
    public void fun() {
        String s = "hello world";
        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));
    }
}
