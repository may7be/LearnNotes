package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zhao on 2020/4/14
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsCousins993 {
    /**
     * 深度相同，但父节点不同，则它们是一对堂兄弟节点
     * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        //1.
        if (root == null || x == y) {
            return false;
        }
        //2. 迭代
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!list.isEmpty()) {
            if (list.contains(x) && list.contains(y)) {
                return true;
            }
            list.clear();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = nodes.remove(0);
                if (remove.left != null && remove.right != null) {
                    int left = remove.left.val;
                    int right = remove.right.val;
                    if (x == left && y == right) {
                        return false;
                    }
                    if (x == right && y == left) {
                        return false;
                    }
                }
                if (remove.left != null) {
                    list.add(remove.left.val);
                    nodes.add(remove.left);
                }
                if (remove.right != null) {
                    list.add(remove.right.val);
                    nodes.add(remove.right);
                }
            }
        }
        return false;
    }


    /**
     * 二叉树的节点数介于 2 到 100 之间。
     * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     */
    private int depthX;
    private int depthY;
    private int parentX;
    private int parentY;

    public boolean isCousins5(TreeNode root, int x, int y) {
        //深度优先遍历，分别记录x和y的depth、parent，然后比对即可
        //可优化的点：如果x和y已经找到，就可以结束遍历了
        dfs(root, x, y, 0, -1);
        System.out.println(depthX + "," + depthY + "," + parentX + "," + parentY);
        return depthX == depthY && parentX != parentY;
    }

    private void dfs(TreeNode root, int x, int y, int depth, int parent) {
        if (root == null) {
            return;
        }
        depth++;
        if (root.val == x) {
            depthX = depth;
            parentX = parent;
        }
        if (root.val == y) {
            depthY = depth;
            parentY = parent;
        }
        dfs(root.left, x, y, depth, root.val);
        dfs(root.right, x, y, depth, root.val);
    }

    public boolean isCousins4(TreeNode root, int x, int y) {
        //优化方向2：删除时直接用remove即可，因为remove有返回值
        List<TreeNode> list = new ArrayList<>();
        List<Integer> vList = new ArrayList<>();
        list.add(root);
        vList.add(-1);
        while (list.size() > 0) {
            int value = 0;
            int len = list.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.remove(0);
                int remove = vList.remove(0);
                if (node.val == x || node.val == y) {
                    if (value == 0) {
                        value = remove;
                    } else {
                        return value != remove;
                    }
                } else if (value == 0) {
                    if (node.left != null) {
                        list.add(node.left);
                        vList.add(node.val);
                    }
                    if (node.right != null) {
                        list.add(node.right);
                        vList.add(node.val);
                    }
                }
            }
            if (value > 0) {
                return false;
            }
        }
        return false;
    }


    public boolean isCousins3(TreeNode root, int x, int y) {
        //优化方向：使用一个list来存储每一层的节点
        List<TreeNode> list = new ArrayList<>();
        List<Integer> vList = new ArrayList<>();
        list.add(root);
        vList.add(-1);
        while (list.size() > 0) {
            int value = 0;
            int len = list.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.get(0);
                if (node.val == x || node.val == y) {
                    if (value == 0) {
                        value = vList.get(0);
                    } else {
                        return value != vList.get(0);
                    }
                } else if (value == 0) {
                    if (node.left != null) {
                        list.add(node.left);
                        vList.add(node.val);
                    }
                    if (node.right != null) {
                        list.add(node.right);
                        vList.add(node.val);
                    }
                }
                list.remove(0);
                vList.remove(0);
            }
            if (value > 0) {
                return false;
            }
        }
        return false;
    }

    public boolean isCousins2(TreeNode root, int x, int y) {
        //按层遍历
        //优化1：确定在该层出现后，后续子节点就不需要添加到下一层了
        HashMap<TreeNode, Integer> map = new HashMap<>();
        HashMap<TreeNode, Integer> addingMap = new HashMap<>();
        map.put(root, -1);
        while (map.size() > 0) {
            int value = 0;
            for (TreeNode key : map.keySet()) {
                if (key.val == x || key.val == y) {
                    //说明在这一层就可以知道结果了
                    if (value == 0) {
                        value = map.get(key);
                    } else {
                        return value != map.get(key);
                    }
                } else if (value == 0) {
                    if (key.left != null) {
                        addingMap.put(key.left, key.val);
                    }
                    if (key.right != null) {
                        addingMap.put(key.right, key.val);
                    }
                }
            }
            if (value > 0) {
                return false;
            }
            map.clear();
            map.putAll(addingMap);
            addingMap.clear();
        }
        return false;
    }

    public boolean isCousins1(TreeNode root, int x, int y) {
        //按层遍历
        HashMap<TreeNode, Integer> map = new HashMap<>();
        HashMap<TreeNode, Integer> addingMap = new HashMap<>();
        if (root.left != null) {
            map.put(root.left, root.val);
        }
        if (root.right != null) {
            map.put(root.right, root.val);
        }
        while (map.size() > 0) {
            int value = 0;
            for (TreeNode key : map.keySet()) {
                if (key.val == x || key.val == y) {
                    //说明在这一层就可以知道结果了
                    if (value == 0) {
                        value = map.get(key);
                    } else {
                        return value != map.get(key);
                    }
                } else {
                    if (key.left != null) {
                        addingMap.put(key.left, key.val);
                    }
                    if (key.right != null) {
                        addingMap.put(key.right, key.val);
                    }
                }
            }
            if (value > 0) {
                return false;
            }
            map.clear();
            map.putAll(addingMap);
        }
        return false;
    }
}
