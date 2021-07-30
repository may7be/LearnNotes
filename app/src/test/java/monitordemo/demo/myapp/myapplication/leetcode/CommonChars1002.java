package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 7/29/21
 */
public class CommonChars1002 {

    /**
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * A[i][j] 是小写字母
     * <p>
     * 你可以按任意顺序返回答案
     */
    public List<String> commonChars(String[] words) {
        //1. 将words中word对应的每个字符出现的次数存入数组
        int size = words.length;
        List<int[]> list = new ArrayList<>(size);
        for (String word : words) {
            int[] arr = new int[26];
            int len = word.length();
            for (int i = 0; i < len; i++) {
                arr[word.charAt(i) - 97]++;
            }
            list.add(arr);
        }
        //2. 比对同一字符出现的次数，都>1的话，符合要求
        List<String> strings = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            boolean flag = true;
            int te = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                if (list.get(i)[j] == 0) {
                    flag = false;
                    break;
                }
                te = Math.min(te, list.get(i)[j]);
            }
            if (flag) {
                for (int i = 0; i < te; i++) {
                    strings.add(String.valueOf((char) (j + 97)));
                }
            }
        }
        return strings;
    }
}
