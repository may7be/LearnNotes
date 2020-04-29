package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/4/30
 * 1095. 山脉数组中查找目标值
 * 双百
 */
public class findInMountainArray1095 {
    private MountainArray mountainArr;
    private int mTarget;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        //暴力法：先找出山顶索引，然后左右两边都是有序的，二分查找即可
        //1.
        int value0 = mountainArr.get(0);

        int valueLast = mountainArr.get(mountainArr.length() - 1);
        if (target < value0 && target < valueLast) {
            return -1;
        }
        //2.找出top
        this.mountainArr = mountainArr;
        int top = getMountainTop(0, mountainArr.length() - 1);
        System.out.println("top:" + top);
        //3.
        if (target > mountainArr.get(top)) {
            return -1;
        }
        if (target == mountainArr.get(top)) {
            return top;
        }
        //4.
        this.mTarget = target;
        if (target < value0) {
            //说明只可能在右山腰
            System.out.println("右山");
            return binarySearch(top + 1, mountainArr.length() - 1, false);
        }
        if (target < valueLast) {

            return binarySearch(0, top - 1, true);
        }
        //5. 先从左山找，左山找到就不找了
        int leftIndex = binarySearch(0, top - 1, true);
        System.out.println("左:" + leftIndex);
        if (leftIndex != -1) {
            return leftIndex;
        }
        return binarySearch(top + 1, mountainArr.length() - 1, false);
    }

    private int binarySearch(int l, int r, boolean isLeft) {
        //
        if (l > r) {
            return -1;
        }
        int mid = (l + r) / 2;
        int midV = mountainArr.get(mid);
        if (midV == mTarget) {
            return mid;
        } else if (midV > mTarget) {
            return isLeft ? binarySearch(l, mid - 1, isLeft) : binarySearch(mid + 1, r, isLeft);
        } else {
            return isLeft ? binarySearch(mid + 1, r, isLeft) : binarySearch(l, mid - 1, isLeft);
        }
    }

    private int getMountainTop(int l, int r) {
        if (l > r) {
            //不可能发生
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        int midV = mountainArr.get(mid);
        int midV1 = mountainArr.get(mid + 1);
        if (midV < midV1) {
            //其实永远都有mid+2
            if (mid + 2 <= mountainArr.length() - 1) {
                if (midV1 > mountainArr.get(mid + 2)) {
                    return mid + 1;
                }
            }
            return getMountainTop(l + 1, r);
        } else if (midV > midV1) {
            //其实永远都有mid-1
            if (mid - 1 >= 0) {
                if (mountainArr.get(mid - 1) < midV) {
                    return mid;
                }
            }
            return getMountainTop(l, r - 1);
        } else {
            //相邻元素不可能相等
            return -1;
        }
    }

    interface MountainArray {
        int get(int index);

        int length();
    }
}
