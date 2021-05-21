package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author zhao on 5/21/21
 */
public class TopKFrequent347 {
    /**
     * 1 <= nums.length <= 105
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     * 可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent2(int[] nums, int k) {
        //快速排序会把时间复杂度降为O(logn)
        return null;
    }
    public int[] topKFrequent(int[] nums, int k) {
        //时间复杂度降为O(nlogk)
        //map; 优先队列：插入的删除的时间复杂度为O(logk),k为队列的长度，获取优先级最高元素的时间复杂度为O(1)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //出现频率越高的元素，优先级越低(compareTo的结果越小，代表优先级越高)
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> map.get(o1) == map.get(o2) ? o2 - o1 : map.get(o1) - map.get(o2));
        for (Integer key : map.keySet()) {
            queue.add(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = queue.poll();
        }
        return arr;
    }
}
