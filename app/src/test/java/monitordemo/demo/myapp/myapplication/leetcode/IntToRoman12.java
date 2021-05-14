package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 5/14/21
 */
public class IntToRoman12 {
    private StringBuilder sb;
    private int num;

    /**
     * 1 <= num <= 3999
     */
    public String intToRoman4(int num) {
        //将每个位上的数，直接对应一个字符即可
        String[] ths = {"MMM", "MM", "M", ""};
        String[] hus = {"CM", "DCCC", "DCC", "DC", "D", "CD", "CCC", "CC", "C", ""};
        String[] tes = {"XC", "LXXX", "LXX", "LX", "L", "XL", "XXX", "XX", "X", ""};
        String[] sss = {"IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I", ""};
        StringBuilder sb = new StringBuilder();
        sb.append(ths[3 - num / 1000]);
        num %= 1000;
        sb.append(hus[9 - num / 100]);
        num %= 100;
        sb.append(tes[9 - num / 10]);
        num %= 10;
        sb.append(sss[9 - num]);
        return sb.toString();
    }

    public String intToRoman3(int num) {
        //依次减去最大值即可
        int[] nArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int len = 13;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            while (num >= nArr[i]) {
                sb.append(strArr[i]);
                num -= nArr[i];
            }
        }
        return sb.toString();
    }


    public String intToRoman2(int num) {
        //百/十/个位都是比对9、5、4
        sb = new StringBuilder();
        this.num = num;
        //0. 千位
        append(sb, num / 1000, "M");
        //1. 百位
        append(100, "CM", "D", "CD", "C");
        //2. 十位
        append(10, "XC", "L", "XL", "X");
        //3. 个位
        append(1, "IX", "V", "IV", "I");
        return sb.toString();
    }

    private void append(int ratio, String first, String second, String third, String forth) {
        num %= ratio * 10;
        if (num < ratio) {
            return;
        }
        int cur = num / ratio;
        if (cur >= 9) {
            sb.append(first);
        } else if (cur >= 5) {
            sb.append(second);
            num %= 5 * ratio;
            append(sb, num / ratio, forth);
        } else if (cur >= 4) {
            sb.append(third);
        } else {
            append(sb, num / ratio, forth);
        }
        num %= ratio;
    }

    public String intToRoman(int num) {
        //暴力法：
        StringBuilder sb = new StringBuilder();
        //1. 千位及以上
        append(sb, num / 1000, "M");
        num %= 1000;
        //2. 百位，
        if (num >= 900) {
            sb.append("CM");
            num %= 900;
        } else if (num >= 500) {
            sb.append("D");
            num %= 500;
            append(sb, num / 100, "C");
            num %= 100;
        } else if (num >= 400) {
            sb.append("CD");
            num %= 400;
        } else {
            append(sb, num / 100, "C");
            num %= 100;
        }
        //3. 十位
        if (num >= 90) {
            sb.append("XC");
            num %= 90;
        } else if (num >= 50) {
            sb.append("L");
            num %= 50;
            append(sb, num / 10, "X");
            num %= 10;
        } else if (num >= 40) {
            sb.append("XL");
            num %= 40;
        } else {
            append(sb, num / 10, "X");
            num %= 10;
        }
        //4. 个位
        if (num == 9) {
            sb.append("IX");
        } else if (num >= 5) {
            sb.append("V");
            append(sb, num - 5, "I");
        } else if (num == 4) {
            sb.append("IV");
        } else {
            append(sb, num, "I");
        }
        return sb.toString();
    }

    private void append(StringBuilder sb, int len, String s) {
        for (int i = 0; i < len; i++) {
            sb.append(s);
        }
    }
}
