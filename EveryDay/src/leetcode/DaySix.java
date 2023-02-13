package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DaySix {
    /**
     * 1422. 分割字符串的最大得分
     * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
     * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
     *
     * @param s
     * @return
     */
    public static int maxScore(String s) {
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                score++;
            }
        }

        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {

            if (s.charAt(i) == '0') {
                score++;
            } else {
                score--;
            }
            ans = Math.max(ans, score);
        }
        return ans;
    }


    public static int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (fast != slow && nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }


    /**
     * 80. 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int slow = 2, fast = 2;
        while (fast < len) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;


    }


    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    public int searchInsert(int[] nums, int target) {
        /**
         * 执行结果:通过显示详情>
         * 执行用时:0 ms ,在所有Java提交中击败了100.00%的用户
         * 内存消耗:41 MB ，在所有Java提交中击败了50.21%的用户
         */
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        /**
         * 执行用时:0 ms ,在所有Java提交中击败了100.00%的用户
         * 内存消耗:44.9 MB，在所有Java提交中击败了 11.17%的用户
         */
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] res = new int[2];
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        boolean flag = false;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                flag = true;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        left = mid;
        right = mid;
        if (flag) {
            while (left - 1 >= 0 && nums[left - 1] == nums[mid]) {
                left--;
            }
            while (right + 1 < nums.length - 1 && nums[right + 1] == nums[mid]) {
                right++;
            }

            res[0] = left;
            res[1] = right;
        } else {
            res[0] = -1;
            res[1] = -1;
        }
        return res;
    }


    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                res[i] = nums[left] * nums[left];
                left++;
            } else {
                res[i] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 双指针 更准确的说是滑动窗口
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        boolean flag = false;
        int ans = Integer.MAX_VALUE, sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                flag = true;
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        if (flag) {
            return ans;
        }
        return 0;
    }


    /**
     * 904. 水果成篮
     * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
     * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
     * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
     * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
     * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
     * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
     *
     * @param fruits
     * @return
     */
    public static int totalFruit(int[] fruits) {
        int sum = 0, count = 0;
        int tree1 = fruits[0], tree2 = fruits[0];
        int ans = Integer.MAX_VALUE;
        int left = 0;
        for (int rigth = 0; rigth < fruits.length; rigth++) {
            if (fruits[rigth] != tree1 && fruits[rigth] != tree2) {

            }
            while (fruits[rigth] == tree1 || fruits[rigth] == tree2) {
                sum++;

                rigth++;
            }

        }
        return sum;
    }


    /**
     * 16. 最接近的三数之和
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        /**
         * 执行用时:70 ms，在所有Java提交中击败了42.68%的用户
         * 内存消耗:41.4 MB ，在所有Java提交中击败了 56.41%的用户
         */
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            /**
             * 加了剪枝后耗时减少了
             * 执行用时:40 ms ,在所有Java提交中击败了65.31%的用户
             * 内存消耗:41.6 MB ，在所有Java提交中击败了35.62%的用户
             */
            if(nums[i] > 0 && nums[i] > min){
                break;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if(nums[i] > 0  && nums[i] + nums[left] > min){
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                    min = Math.abs(target-sum) > Math.abs(target - min) ? min : sum;
                } else if (sum < target){
                    left++;
                    min = Math.abs(target-sum) > Math.abs(target - min) ? min : sum;
                }else {
                    return target;
                }
            }
        }
        return min;
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * hash
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(hashMap.containsKey(s.charAt(i))){
                int count = hashMap.get(s.charAt(i)) + 1;
                hashMap.put(s.charAt(i), count);
            }else {
                hashMap.put(s.charAt(i), 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            if(hashMap.containsKey(s.charAt(i))){
                int count = hashMap.get(s.charAt(i)) - 1;
                hashMap.put(s.charAt(i), count);
            }else {
                return false;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(hashMap.get(s.charAt(i)) != 0)
                return false;
        }
        return true;
    }


    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」 定义为：
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {

        Set<Integer> hashMap = new HashSet<>();
        while(n != 1 && !hashMap.contains(n)){
            hashMap.add(n);
            n = getSum(n);
        }
        return n == 1;
    }

    public static int getSum(int n){
        int ans = 0;
        while(n > 0){
            int count = n % 10;
            ans += count*count;
            n = n / 10;
        }
        return ans;
    }


    /**
     * 454. 四数相加 II
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * 首先定义 一个unordered_map，key放a和b两数之和，value 放a和b两数之和出现的次数。
     * 遍历大A和大B数组，统计两个数组元素之和，和出现的次数，放到map中。
     * 定义int变量count，用来统计 a+b+c+d = 0 出现的次数。
     * 在遍历大C和大D数组，找到如果 0-(c+d) 在map中出现过的话，就用count把map中key对应的value也就是出现次数统计出来。
     * 最后返回统计值 count 就可以了
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                if(!hashMap.containsKey(sum)){
                    hashMap.put(sum, 1);
                }else {
                    hashMap.put(sum, hashMap.get(sum) + 1);
                }
            }
        }
        int count =0;
        for (int i : nums3) {
            for (int j : nums4) {
                if(hashMap.containsKey(-i - j)){
                    count += hashMap.get(-i - j);
                }
            }
        }

        return count;
    }
    public static void main(String[] args) {

//        int[] nums = {1, 1, 1, 2, 2, 3};
//        removeDuplicates(nums);
        isAnagram("car", "tar");
    }


}
