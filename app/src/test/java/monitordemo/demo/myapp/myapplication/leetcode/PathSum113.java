package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author zhao on 2020-03-20
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class PathSum113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //1.
        if (root == null) {
            return null;
        }
        //2. 判断是否叶子节点
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<List<Integer>> treeList = new ArrayList<>();
                treeList.add(new ArrayList<Integer>());
                treeList.get(0).add(root.val);
                return treeList;
            } else {
                return null;
            }
        }
        //3. 递归左右分支
        List<List<Integer>> leftLists = pathSum(root.left, sum - root.val);
        List<List<Integer>> rightLists = pathSum(root.right, sum - root.val);
        //4. 分情况处理
        if (leftLists == null && rightLists == null) {
            return null;
        }
        if (leftLists == null) {
            return getLists(rightLists, root.val);
        }
        if (rightLists == null) {
            return getLists(leftLists, root.val);
        }
        leftLists.addAll(rightLists);
        return getLists(leftLists, root.val);
    }

    private List<List<Integer>> getLists(List<List<Integer>> lists, int val) {
        if (lists == null) {
            return null;
        }
        for (List<Integer> list : lists) {
            list.add(0, val);
        }
        return lists;
    }


    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        //答案要求不返回null,而返回空串
        //1.

        if (root == null) {
            return new ArrayList<>();
        }
        //2. 判断是否叶子节点
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<List<Integer>> treeList = new ArrayList<>();
                treeList.add(new ArrayList<Integer>());
                treeList.get(0).add(root.val);
            }
            return new ArrayList<>();
        }
        //3. 递归左右分支
        List<List<Integer>> leftLists = pathSum(root.left, sum - root.val);
        List<List<Integer>> rightLists = pathSum(root.right, sum - root.val);
        //4. 分情况处理
        leftLists.addAll(rightLists);
        return getLists2(leftLists, root.val);
    }

    private List<List<Integer>> getLists2(List<List<Integer>> lists, int val) {
        for (List<Integer> list : lists) {
            list.add(0, val);
        }
        return lists;
    }

    public List<List<Integer>> pathSum3(TreeNode root, int sum) {
        //迭代法：
        //1.
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                lists.add(new ArrayList<Integer>());
                lists.get(0).add(root.val);
            }
            return lists;
        }
        //2. 一层一层迭代
        lists.add(new ArrayList<Integer>());
        lists.get(0).add(root.val);


        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = nodes.remove(0);
                //先判断是否是叶子节点
                if (remove.left == null && remove.right == null) {
                    if (remove.val == sum) {
                        lists.add(new ArrayList<Integer>());
                        lists.get(0).add(root.val);
                    } else {
                        //删除自己父亲所在分支，

                    }
                }
                lists.add(new ArrayList<Integer>());
                lists.get(lists.size() - 1).add(remove.val);
                remove.val = sum - remove.val;
                //左右添加到nodes
                if (remove.left != null) {
                    nodes.add(remove.left);
                }
                if (remove.right != null) {
                    nodes.add(remove.right);
                }


                //将当前节点的val添加进到，其父类的list中
                for (List<Integer> list : lists) {
                    list.add(remove.val);
                }

            }

        }

        //root至少有一个分支
        List<LinkedList<TreeNode>> nodeLists = new ArrayList<>();
        nodeLists.add(new LinkedList<TreeNode>());
        nodeLists.get(0).add(root);
        List<Integer> sums = new ArrayList<>();
        sums.add(sum - root.val);
        for (int i = 0; i < nodeLists.size(); i++) {
            Integer s = sums.get(i);
            LinkedList<TreeNode> list = nodeLists.get(i);
            TreeNode last = list.getLast();
            if (last.left != null) {
                if (last.left.left != null || last.left.right != null) {
                    list.add(last.left);
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> pathSum4(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        //是否叶子节点
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                lists.add(new ArrayList<Integer>());
                lists.get(0).add(root.val);
            }
            return lists;
        }
        //add到nodeList
        List<List<TreeNode>> treeLists = new ArrayList<>();
        treeLists.add(new ArrayList<TreeNode>());
        treeLists.get(0).add(root);
        while (treeLists.size() > 0) {
            List<TreeNode> remove = treeLists.remove(0);
            //最后一个node
            TreeNode lastNode = remove.get(remove.size() - 1);
            //分别判断左右是否叶子节点
            checkYezi(lastNode.left, remove, sum, lists, treeLists);
            checkYezi(lastNode.right, remove, sum, lists, treeLists);
        }
        return lists;
    }

    private void checkYezi(TreeNode left, List<TreeNode> remove, int sum, List<List<Integer>> lists, List<List<TreeNode>> treeLists) {
        if (left != null) {
            List<TreeNode> newList = new ArrayList<>(remove);
            newList.add(left);
            if (left.left == null && left.right == null) {
                //判断remove  == sum
                if (checkEquals(newList, sum)) {
                    //添加到lists
                    addPathToLists(newList, lists);
                } else {
                    //不处理，remove就被删除了
                }
            } else {
                //添加到lists队尾部
                treeLists.add(newList);
            }
        }
    }

    private void addPathToLists(List<TreeNode> remove, List<List<Integer>> lists) {
        lists.add(new ArrayList<Integer>());
        List<Integer> list = lists.get(lists.size() - 1);
        for (TreeNode node : remove) {
            list.add(node.val);
        }
    }

    private boolean checkEquals(List<TreeNode> remove, int sum) {
        if (remove == null || remove.size() == 0) {
            return false;
        }
        for (TreeNode node : remove) {
            sum -= node.val;
        }
        return sum == 0;
    }

    public List<List<Integer>> pathSum5(TreeNode root, int sum) {
        //优化：方法四有很多冗余的地方
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        //不用判断是否叶子节点，直接add进去
        List<List<TreeNode>> treeLists = new ArrayList<>();
        treeLists.add(new ArrayList<TreeNode>());
        treeLists.get(0).add(root);
        while (treeLists.size() > 0) {
            List<TreeNode> remove = treeLists.remove(0);
            TreeNode last = remove.get(remove.size() - 1);
            //判断叶子节点
            if (last.left == null && last.right == null) {
                if (checkEquals(remove, sum)) {
                    addPathToLists(remove, lists);
                }
            }
            //add分支到treeList
            addBranch2TreesList(last.left, remove, treeLists);
            addBranch2TreesList(last.right, remove, treeLists);
        }
        return lists;
    }

    private void addBranch2TreesList(TreeNode left, List<TreeNode> remove, List<List<TreeNode>> treeLists) {
        if (left != null) {
            List<TreeNode> newList = new ArrayList<>(remove);
            newList.add(left);
            treeLists.add(newList);
        }
    }
}
