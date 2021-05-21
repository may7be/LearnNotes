package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Author zhao on 5/20/21
 */
public class TopKFrequent692 {


    @Test
    public void fun() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(11, "a");
        map.put(12, "b");
        map.put(1, "c");
        map.put(3, "d");
        map.put(2, "e");
        map.put(11, "f");
//        System.out.println(map);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1, 1);
//        System.out.println(list);
        List<String> sList = new ArrayList<>();
        String a = "a";
        String b = "b";
        String c = "c";
        System.out.println(a.compareTo(c));
        sList.add(a);
        sList.add(b);
        sList.add(c);
        //<0代表代表排在前
        //s1.compareTo(s2): >0,代表字母顺序更大
        Collections.sort(sList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return -11;
            }
        });
//        System.out.println(sList);

        String[] words = {"d", "a", "f", "g", "c", "e", "b"};
        topKFrequent3(words, 15);

    }

    /**
     * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
     * 输入的单词均由小写字母组成。
     * <p>
     * 按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序
     */
    public List<String> topKFrequent3(String[] words, int k) {
        //map; 优先队列: 每次poll都是poll优先级最高的元素,但是内部存储不是按照优先级进行排序的
        //由于是取出现频率最高的k个元素，故可以把出现频率最高的元素设置为优先级最低，然后每当队列中元素数>k，就poll优先级最高的元素，然后再reverse即可
        //1. map
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //2. 存入优先队列，每当队列中元素数>k, 则弹出优先级最高的元素
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1) == map.get(o2) ? o2.compareTo(o1) : map.get(o1) - map.get(o2);
            }
        });
        for (String key : map.keySet()) {
            queue.add(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        //3. 按照优先级依次弹出，再reverse即可
        List<String> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        Collections.reverse(list);
        return list;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        //时间复杂度: O(nlogk), 空间复杂度：O(n) ==== 复杂度不对
        //map；list; 排序取值
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(key);
        }
        //s1.compareTo(s2)<0，排到前面
        Collections.sort(list, (s1, s2) -> map.get(s1).equals(map.get(s2)) ? s1.compareTo(s2) : map.get(s2) - map.get(s1));
        return list.subList(0, k);
    }

    public List<String> topKFrequent(String[] words, int k) {
        //时间复杂度: O(nlogn), 空间复杂度：O(n)
        //暴力法：遍历，用map记录单词出现的次数；然后取前K个高频词，如果相同频率，按字母排序
        //1.
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println(map);
        //2. 以出现频率为key，存入treeMap，同时对value按照字母顺序排序；要使用倒叙的comparator
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>(Comparator.reverseOrder());
        for (String key : map.keySet()) {
            int value = map.get(key);
            List<String> orDefault = treeMap.getOrDefault(value, new ArrayList<>());
            //存的时候，按照字母顺序排序
            addString(key, orDefault);
            treeMap.put(value, orDefault);
        }
        System.out.println(treeMap);
        //3. 取前k个高频词即可
        List<String> list = new ArrayList<>();
        for (Integer key : treeMap.keySet()) {
            List<String> subList = treeMap.get(key);
            //对list的size进行判定
            int maySize = list.size() + subList.size();
            if (maySize == k) {
                list.addAll(subList);
                return list;
            } else if (maySize < k) {
                list.addAll(subList);
            } else {
                list.addAll(subList.subList(0, k - list.size()));
                return list;
            }
        }
        return list;
    }

    /**
     * 放入应该放入的位置
     */
    private void addString(String key, List<String> orDefault) {
        //实际作用可以用collects.sort来代替
        if (orDefault.isEmpty()) {
            orDefault.add(key);
            return;
        }
        int start = 0;
        int end = orDefault.size() - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            String midS = orDefault.get(mid);
            boolean flag = leftOrRight(key, midS);
            System.out.println("key:" + key + ",midS:" + midS + "flag:" + flag);
            if (flag) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        int index = leftOrRight(key, orDefault.get(start)) ? start : start + 1;
        orDefault.add(index, key);
    }

    /**
     * 默认这俩字符串不等
     * true代表应该排在midS的left，false表示要排在right
     */
    private boolean leftOrRight(String key, String midS) {
        int kLen = key.length();
        int midLen = midS.length();
        int len = Math.min(kLen, midLen);
        for (int i = 0; i < len; i++) {
            if (key.charAt(i) < midS.charAt(i)) {
                return true;
            } else if (key.charAt(i) > midS.charAt(i)) {
                return false;
            }
        }
        //如果高位已经比较完毕，还未结束，说明高位都相等，比较谁更长就好
        return kLen < midLen;
    }
}
