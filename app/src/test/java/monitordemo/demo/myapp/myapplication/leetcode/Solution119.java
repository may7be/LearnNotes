package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author zhao on 2020/5/2
 * 119. 杨辉三角 II
 */
public class Solution119 {

    /**
     * 0<=k<=33
     */
    public List<Integer> getRow(int rowIndex) {
        //依次算出每行的list，直到list.size = rowIndex+1
        List<Integer> list = new LinkedList<>();
        list.add(1);
        while (list.size() < rowIndex + 1) {
            int size = list.size();
            for (int i = 0; i < size + 1; i++) {
                if (i == 0 || i == size) {
                    list.add(1);
                } else {
                    list.add(list.get(i - 1) + list.get(i));
                }
            }
            if (size > 0) {
                list.subList(0, size).clear();
            }
        }
        return list;
    }

    public List<Integer> getRow2(int rowIndex) {
        //原地修改
        List<Integer> list = new LinkedList<>();
        list.add(1);
        if (rowIndex >= 2) {
            list.add(1);
        }
        while (list.size() < rowIndex + 1) {
            list.add(0, 1);
            for (int i = 1; i < list.size() - 1; i++) {
                int t = list.get(i) + list.get(i + 1);
                list.remove(i);
                list.add(i, t);
            }
        }
        return list;
    }
}
