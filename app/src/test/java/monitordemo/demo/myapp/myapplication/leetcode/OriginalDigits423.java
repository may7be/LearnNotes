package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2021/11/24
 */
public class OriginalDigits423 {

    private int[] arr;
    private int[] arr2;

    /**
     * 1 <= s.length <= 105
     * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
     * s 保证是一个符合题目要求的字符串
     */
    public String originalDigits(String s) {
        //0. 判空
        if (s == null || s.length() <= 2) {
            return null;
        }

        //1. 将字符串按照字符出现的次数存入数组
        arr = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 97;
            arr[index] += 1;
        }
//        printArr();

        //2. 对于单独出现的字符进行剔除，如zero中的z,two中的w,four中的u,eight中的g
        //同时将数字(0-9)出现的次数存入数组，
        arr2 = new int[10];
        //2.1 先删除zero中的z,two中的w,four中的u,six中的x,eight中的g
        delNumber("zero", 'z', 0);
        delNumber("two", 'w', 2);
        delNumber("four", 'u', 4);
        delNumber("six", 'x', 6);
        delNumber("eight", 'g', 8);
        //2.2 再删除one中o,three中的t,five中的f,seven中的s
        delNumber("one", 'o', 1);
        delNumber("three", 't', 3);
        delNumber("five", 'f', 5);
        delNumber("seven", 's', 7);
        //2.3 唯一剩下的就是nine中的字符
        arr2[9] = arr['i' - 97];

//        printArr2();
        //3. 将arr2中大于0的拼接起来即可
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int times = arr2[i];
            for (int j = 0; j < times; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    /**
     * 对单独出现的字符进行剔除
     */
    private void delNumber(String str, char c, int num) {
        int times = arr[c - 97];
        if (times > 0) {
            //剔除arr中的对应字符出现的次数
            for (char ch : str.toCharArray()) {
                arr[ch - 97] -= times;
            }
            //将出现的次数存入数组arr2
            arr2[num] = times;
        }
    }

    private void printArr() {
        //test
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                char c = (char) (i + 97);
                System.out.println("字符" + c + "出现的次数:" + arr[i]);
            }
        }
    }

    private void printArr2() {
        //test
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "出现次数：" + arr2[i]);
        }
    }

    @Test
    public void fun() {
        String s = "onetwothreeninefoursix";
        System.out.println(s + "：" + originalDigits(s));
    }
}
