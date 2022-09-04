package array;

import java.util.ArrayList;
import java.util.Arrays;

public class array {

    public static int singleNumber(int[] nums) {
        for(int i= 1; i<nums.length;i++){
            nums[0] ^= nums[i];
        }
        return nums[0];
    }


    /**
     * 两个数组的交集 II
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while(p1 < nums1.length && p2 < nums2.length){
            if(nums1[p1] == nums2[p2]){
                res.add(nums1[p1]);
                p1++;
                p2++;
            }else if(nums1[p1] > nums2[p2]){
                p2 ++;
            }else {
                p1++;
            }
        }

        //把list转化为数组
        int[] ans = new int[res.size()];
        for (int k = 0; k < res.size(); k++) {
            ans[k] = res.get(k);
        }
        return ans;

    }

    /**
     * 旋转数组
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] nums2 = nums;
        for (int i = 0; i < len; i++) {
            int temp = nums2[(i + k) % len];
            nums[i]  = temp;
        }

    }

    /**
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 本质就是二分查找
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;

        while(start < end){
            int mid = start + (end - start)/2;
            if(!isBadVersion(mid)){
                start = mid + 1;
            }else {
                end = mid;
            }
        }
        return start;
    }

    private boolean isBadVersion(int mid) {
        return true;
    }


    public static void main(String[] args) {

    }
}
