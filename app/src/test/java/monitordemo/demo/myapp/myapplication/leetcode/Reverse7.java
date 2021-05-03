package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 5/3/21
 */
public class Reverse7 {
    @Test
    public void fun() {
//        System.out.println(convertToTitle(52));
        int a = -1147483641;
//        String s = String.valueOf(a);
//        System.out.println(s + ".reverse: " + reverse(s));
        System.out.println(reverse2(a));
//        int b = -2;
//        System.out.println(b % 10);
//        System.out.println(b / 10);
    }

    public int reverse3(int x) {
        //终版
        int te = 0;
        int max = Integer.MAX_VALUE / 10;
        while (x != 0) {
            if (te > max || te < -max) {
                return 0;
            }
            te = te * 10 + x % 10;
            x /= 10;
        }
        return te;
    }

    public int reverse2(int x) {
        System.out.println(x);
        int te = 0;
        while (x != 0) {
            int remainder = x % 10;
            int max = Integer.MAX_VALUE / 10;
            if (te > max || te < -max) {
                return 0;
            } else if (te == max && remainder > 7) {
                //其实后两种的场景不可能成立，因为te==max时，remainder一定是1or-1
                return 0;
            } else if (te == -max && remainder < -8) {
                return 0;
            }
            te = te * 10 + remainder;
            x /= 10;
            System.out.println("te: " + te + ", x: " + x);
        }
        return te;
    }

    public int reverse(int x) {
        //环境不支持try catch
        String s = reverse(String.valueOf(x));
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public String reverse(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int end = 0;
        if (s.charAt(0) == 45) {
            sb.append(s.charAt(0));
            end = 1;
        }
        for (int i = len - 1; i >= end; i--) {
            char c = s.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }
}
