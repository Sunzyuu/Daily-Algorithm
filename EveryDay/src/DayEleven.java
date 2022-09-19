import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DayEleven {
    /**
     * 1365. 有多少小于当前数字的数字
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     * 以数组形式返回答案。
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
        }
        return res;
    }

    /**
     * 941. 有效的山脉数组
     * 给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。
     * 让我们回顾一下，如果 arr 满足下述条件，那么它是一个山脉数组：
     * arr.length >= 3
     * 在 0 < i < arr.length - 1 条件下，存在 i 使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) { // 此时，一定不是有效的山脉数组
            return false;
        }
        // 双指针
        int left = 0;
        int right = arr.length - 1;
        // 注意防止指针越界
        while (left + 1 < arr.length && arr[left] < arr[left + 1]) {
            left++;
        }
        // 注意防止指针越界
        while (right > 0 && arr[right] < arr[right - 1]) {
            right--;
        }
        // 如果left或者right都在起始位置，说明不是山峰
        if (left == right && left != 0 && right != arr.length - 1) {
            return true;
        }
        return false;
    }


    /**
     * 1207. 独一无二的出现次数
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2005];

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);
        }
//        Arrays.sort(count);
//        for (int i = 1; i < count.length; i++) {
//            if(count[i] != 0 && count[i-1] == count[i]){
//                return false;
//            }
//        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer key: hashMap.keySet()){
            if(!hashSet.add(hashMap.get(key))){
                return false;
            }
        }
        return true;
    }


    /**
     * 1991. 找到数组的中间位置
     * 给你一个下标从 0 开始的整数数组 nums ，请你找到 最左边 的中间位置 middleIndex （也就是所有可能中间位置下标最小的一个）。
     * 中间位置 middleIndex 是满足 nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1] 的数组下标。
     * 如果 middleIndex == 0 ，左边部分的和定义为 0 。类似的，如果 middleIndex == nums.length - 1 ，右边部分的和定义为 0 。
     * 请你返回满足上述条件 最左边 的 middleIndex ，如果不存在这样的中间位置，请你返回 -1 。
     * @param nums
     * @return
     */
    public int findMiddleIndex(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int sum = Arrays.stream(nums).sum();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if(total == sum){
                return i;
            }
            sum -= nums[i];
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * @param nums
     * @param target
     * @return
     */
    //先找>=target的第一个
    //再找>target的第一个
    //我真是这辈子都不想看见这题
    public int[] searchRange(int[] nums, int target) {
        int l=search(nums,target);
        int r=search(nums,target+1);
        if(l==nums.length||nums[l]!=target)
            return new int[]{-1,-1};
        return new int[]{l,r-1};
    }
    //找>=target的第一个
    public int search(int[] nums,int target){
        int l=0,r=nums.length;
        while(l<r){
            int mid=(r+l)>>1;
            if(nums[mid]>=target)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }


    public static void main(String[] args) {

    }
}
