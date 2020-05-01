package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/5/1
 * 118. 杨辉三角
 */
public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        //按层插入即可
        //1.
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        //2.
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        lists.add(list0);
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastList = lists.get(i - 1);
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                int left = j == 0 ? 0 : lastList.get(j - 1);
                int right = j == i ? 0 : lastList.get(j);
                list.add(left + right);
            }
            lists.add(list);
        }
        return lists;
    }

    public List<List<Integer>> generate2(int numRows) {
        //简单优化
        //1.
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        //2.
        lists.add(new ArrayList<Integer>());
        lists.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<Integer> lastList = lists.get(i - 1);
            for (int j = 1; j < i; j++) {
                list.add(lastList.get(j - 1) + lastList.get(j));
            }
            list.add(1);
            lists.add(list);
        }
        return lists;
    }
}
