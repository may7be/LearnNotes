package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 5/6/21
 */
public class Decode1720 {
    /**
     * 2 <= n <= 104
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 105
     * 0 <= first <= 105
     */
    public int[] decode2(int[] encoded, int first) {
        //貌似只能转为字符串硬算了
        //a^a=0 a^0=a,故a^x=b, 则x=a^b
        int len = encoded.length;
        int[] arr = new int[len + 1];
        arr[0] = first;
        for (int i = 0; i < len; i++) {
            arr[i + 1] = arr[i] ^ encoded[i];
        }
        return arr;
    }

    public int[] decode(int[] encoded, int first) {
        //貌似只能转为字符串硬算了
        //a^a=0 a^0=a,故a^x=b, 则x=a^b
        int len = encoded.length;
        int[] arr = new int[len + 1];
        arr[0] = first;
        for (int i = 0; i < len; i++) {
            arr[i + 1] = cal(arr[i], encoded[i]);
        }
        return arr;
    }

    private int cal(int a, int b) {
        if (a == b) {
            return 0;
        }
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        String s1 = Integer.toBinaryString(a);
        String s2 = Integer.toBinaryString(b);
        int len1 = s1.length();
        int len2 = s2.length();
        StringBuilder sb = new StringBuilder();
        if (len1 >= len2) {
            int diff = len1 - len2;
            for (int i = 0; i < len1; i++) {
                if (i < diff) {
                    sb.append(s1.charAt(i));
                } else {
                    sb.append(s1.charAt(i) == s2.charAt(i - diff) ? 0 : 1);
                }
            }
        } else {
            int diff = len2 - len1;
            for (int i = 0; i < len2; i++) {
                if (i < diff) {
                    sb.append(s2.charAt(i));
                } else {
                    sb.append(s2.charAt(i) == s1.charAt(i - diff) ? 0 : 1);
                }
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    @Test
    public void fun() {
        int[] nums = {8, 7, 3, 8, 1, 4, 10, 10, 10, 2};
//        System.out.println(Arrays.toString(nums));
//        System.out.println(deleteAndEarn(nums));
//        System.out.println(Integer.toBinaryString(100));
//        System.out.println(Integer.valueOf("0001"));
        char c = '0';
        int a = c;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append('1');
        sb.append('0');
//        System.out.println(sb.toString());
        System.out.println(cal(1, 0));
        System.out.println(cal(0, 2));
        System.out.println(cal(2, 1));

        System.out.println(cal(1, 0));
    }
}
