package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.List;

/**
 * @Author zhao on 5/2/21
 */
public class LeastBricks554 {
    /**
     * n == wall.length
     * 1 <= n <= 104
     * 1 <= wall[i].length <= 104
     * 1 <= sum(wall[i].length) <= 2 * 104
     * 对于每一行 i ，sum(wall[i]) 应当是相同的
     * 1 <= wall[i][j] <= 231 - 1
     */

    public int leastBricks2(List<List<Integer>> wall) {
        //实际在存入的过程中就可以记录最大值即可: 也就减少了3ms
        //实际是求「砖块边缘相等」最多的值。
        //遍历存入map, 每个边缘出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> list : wall) {
            int sum = 0;
            int size = list.size();
            for (int i = 0; i < size - 1; i++) {
                sum += list.get(i);
                int te = map.getOrDefault(sum, 0) + 1;
                map.put(sum, te);
                max = Math.max(max, te);
            }
        }
        System.out.println(map);
//        //移除边缘为墙右边缘的值
//        map.remove(sum);
//        //求得最大值即可
//        int max = 0;
//        for (Integer key : map.keySet()) {
//            max = Math.max(max, map.get(key));
//        }
        return wall.size() - max;
    }

    public int leastBricks(List<List<Integer>> wall) {
        //实际是求「砖块边缘相等」最多的值。
        //遍历存入map, 每个边缘出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (List<Integer> integers : wall) {
            sum = 0;
            for (Integer a : integers) {
                sum += a;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        System.out.println(map);
        //移除边缘为墙右边缘的值
        map.remove(sum);
        //求得最大值即可
        int max = 0;
        for (Integer key : map.keySet()) {
            max = Math.max(max, map.get(key));
        }
        return wall.size() - max;
    }
}
