package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class DayTen {
    /**
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
//        LinkedList<Integer> stack = new LinkedList<>();
//        int index = 0;
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        /**
         * 输入: temperatures = [73,74,75,71,69,72,76,73]
         * 输出: [1,1,4,2,1,1,0,0]
         */
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int temp = stack.pop();
                res[temp] = i - temp;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 496. 下一个更大元素 I
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums1.length; i++) {
            int index = 0;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    index = j;
                }
            }
            for (int j = index + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        LinkedList<Integer> stack = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        // 首先使用map存放nums1中的元素，key value 分别为value index
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        // 遍历nums2并维持此单调栈为递增的栈，即如果当前元素小于等于栈顶元素时 直接入栈
        // 如果当前元素大于栈顶元素则，进行出栈操作 并在出栈时判断此出栈元素是否为nums1中的元素，如果是则
        // 通过index = map.get(stack.pop())获取其索引值 这样res[index] = nums2[i]
        // 最后再进行入栈操作即可
        stack.push(nums2[0]);
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= stack.peek()) {
                stack.push(nums2[i]);
            } else {
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    int temp = stack.pop();
                    if (map.containsKey(temp)) {
                        res[map.get(temp)] = nums2[i];
                    }
                }
                stack.push(nums2[i]);
            }
        }
        return res;
    }


    /**
     * 503. 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     *
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[2 * nums.length];
        Arrays.fill(res, -1);
        int[] nums2 = new int[2 * nums.length];
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = nums[i % nums.length];
        }
/*        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        因为数组中存在重复的元素 因此无法使用map来记录索引
        */
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < nums2.length; i++) {  //
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    int tmepIndex = stack.pop();
                    res[tmepIndex] = nums2[i];
                }
                stack.push(i);
            }
        }
        return Arrays.copyOfRange(res, 0, nums.length);
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // 双指针
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) {
                continue;
            }

            int rHeight = height[i];
            int lHeight = height[i];
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rHeight) {
                    rHeight = height[j];
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > lHeight) {
                    lHeight = height[j];
                }
            }
            int h = Math.min(rHeight, lHeight) - height[i];
            if (h > 0) {
                sum += h;
            }

        }
        return sum;
    }

    public int trap1(int[] height) {
        // 单调栈
        int sum = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        // 维持单调栈
        for (int i = 1; i < height.length; i++) {
            if(height[i] < height[stack.peek()]){
                stack.push(i);
            }else if(height[i] == height[stack.peek()]){
                stack.pop();
                stack.push(i);
            }else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                    int mid = stack.pop(); // 作为凹槽的底
                    //判断stack是否为空
                    if(!stack.isEmpty()){
                        // 获得矩形的高
                        int h = Math.min(height[stack.peek()], height[i]) - height[mid];
                        int w = i - stack.peek() - 1;

                        sum += h * w;
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }

    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        //首先找到当前柱子两边第一个小于该柱子的索引值， 因此需要栈辅助实现
        Stack<Integer> stack = new Stack<Integer>();
        // 数组扩容，在头和尾各加入一个元素
        int [] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int index = 0; index < heights.length; index++){
            newHeights[index + 1] = heights[index];
        }
        heights = newHeights;

        // 此单调栈为单调递减 即从顶到底为 为递减
        // 栈中存储的是数组的下标 首先将索引0放入stack
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            //判断当前元素与栈顶元素的大小关系
            // heights[i] > heights[stack.peek()] 则直接入栈
            if(heights[i] > heights[stack.peek()]){
                stack.push(i);
            }else if(heights[i] == heights[stack.peek()]){
                // 如果遇到相等的高度 那么需要更新索引
                stack.pop();
                stack.push(i);
            }else{
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                    // 如果当前元素小于栈顶元素 则栈顶元素遇到的右侧第一个小于自己的柱子
                    // 根据单调栈的定义 可以知道栈顶元素的下一个元素就是其左侧第一个小于自己的柱子
                    // 那么就可以由这三个元素构成矩形
                    int mid = stack.pop();
                    int right = i;
                    int left = stack.peek();
                    int w = right -left - 1;
                    int h = heights[mid];
                    res = Math.max(res, w * h);
                }
                stack.push(i);
            }
        }
        return res;
    }


    /**
     * 2022.9.16
     * 单调栈
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3};
        nextGreaterElements(nums);
    }
}
