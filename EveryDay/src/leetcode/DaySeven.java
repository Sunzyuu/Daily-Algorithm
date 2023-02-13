package leetcode;

import java.util.*;

public class DaySeven {

    /**
     * 383. 赎金信
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false 。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            chars[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            chars[ransomNote.charAt(i) - 'a']--;
            if (chars[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 349. 两个数组的交集
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }

        return res.stream().mapToInt(x -> x).toArray();
    }


    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {


        return 0;
    }


    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {

        int[] result = new int[k];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        // 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }


    /**
     * 1047. 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * <p>
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * <p>
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     *
     * @param s
     * @return
     */
    public static String removeDuplicates(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Stack<Character> stack = new Stack<>();
        int index = 0;
        stack.push(s.charAt(index++));
        while (index < s.length()) {

            if (stack.isEmpty()) {
                stack.push(s.charAt(index));
                index++;
            } else {
                Character pop = stack.pop();
                if (pop == s.charAt(index)) {
                    index++;
                } else {
                    stack.push(pop);
                    stack.push(s.charAt(index));
                    index++;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }


    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int p1 = 0;
        int p2 = 0;
        int index = 0;
        int[] nums = new int[len1 + len2];
        while (p1 < len1 || p2 < len2) {
            if (p1 >= len1) {
                nums[index++] = nums2[p2++];
            } else if (p2 >= len2) {
                nums[index++] = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                nums[index++] = nums1[p1++];
            } else {
                nums[index++] = nums2[p2++];
            }
        }
        if ((len1 + len2) % 2 == 0) {
            return (double) nums[(len1 + len2) / 2];
        } else {
            return ((double) nums[(len1 + len2) / 2] + (double) nums[(len1 + len2) / 2 - 1]) / 2;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        /**
         * 二分查找
         */
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    private static double getKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界情况
            if (index1 > len1) {
                return nums2[index2 + k - 1];
            }
            if (index2 > len2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;

            // index + half -1 - index + 1  = half
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 注意 两个整数之间的除法只保留整数部分。
     * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                stack.push(-stack.pop() + stack.pop());
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }


    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combineHelper(n, k, 1, result, path);
        return result;
    }

    private static void combineHelper(int n, int k, int startIndex, List<List<Integer>> result, LinkedList<Integer> path) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineHelper(n, k, i + 1, result, path);
            path.removeLast();
        }
    }


    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinenation(candidates, target, 0, result, path);
        return result;
    }

    private static void combinenation(int[] candidates, int target, int i, List<List<Integer>> result, LinkedList<Integer> path) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (target - candidates[j] < 0) break;
            path.add(candidates[j]);
            combinenation(candidates, target - candidates[j], j, result, path);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 40. 组合总和 II
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * 注意：解集不能包含重复的组合。
     *
     * @param candidates
     * @param target
     * @return
     */
//    private static HashSet<List<Integer>> hashSet = new HashSet<>();
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
//        boolean[] flag = new boolean[candidates.length];
        Arrays.sort(candidates);
        combinenation2(candidates, target, 0, result, path);
        return result;
    }

    private static void combinenation2(int[] candidates, int target, int i, List<List<Integer>> result, LinkedList<Integer> path) {
        if (target < 0) return;
        if (target == 0) {
                result.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if(j > i && candidates[j - 1] == candidates[j]) {
                continue;
            }
            if (target - candidates[j] < 0) break;
            path.add(candidates[j]);
            combinenation2(candidates, target - candidates[j], j + 1, result, path);
            path.removeLast();
        }
    }


    /**
     * 216.组合总和III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
//        combineHelper2(n, k, 1, result, path);
        combineHelper3(n, k, 1, result, path);
        return result;
    }

    private static void combineHelper2(int n, int k, int startIndex, List<List<Integer>> result, LinkedList<Integer> path) {
        ArrayList<Integer> list = new ArrayList<>(path);
        int sum = 0;
        for (int a : list) {
            sum += a;
        }
        if (path.size() == k) {
            if (sum == n) {
                result.add(list);
            }
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            combineHelper2(n, k, i + 1, result, path);
            path.removeLast();
        }
    }
    private static void combineHelper3(int n, int k, int startIndex, List<List<Integer>> result, LinkedList<Integer> path) {
        if (n < 0) return; // 优化，当n < 0 时，说明后面不可能有正确的组合提前返回即可
        if (path.size() == k && n == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            combineHelper3(n - i, k, i + 1, result, path);
            path.removeLast();
        }
    }



    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        Deque<String> path = new LinkedList<>();
        partition(s, 0, result, path);
        return result;
    }

    private static void partition(String s, int index, List<List<String>> result, Deque<String>  path) {
        if(index >= s.length()){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int j = index; j < s.length(); j++) {
            if(isPalindrome(s, index, j)){
                String str = s.substring(index, j + 1);
                path.addLast(str);
            }else {
                continue;
            }
            partition(s, j + 1, result, path);
            path.removeLast();
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        int left = start;
        int right = end;
        for (; left < right; left++, right--) {
            if(s.charAt(left) != s.charAt(right))
                return false;
        }
        return true;
    }





    public static void main(String[] args) {
//        canConstruct("aa", "ab");
//        removeDuplicates("abbaca");

        int[] nums1 = {1, 3, 6};
        int[] nums2 = {2, 5, 7};
//        findMedianSortedArrays(nums1, nums2);
//        combine(4, 2);
//        System.out.println(combine2(7, 3));

        int[] candidates = { 10, 1, 2, 7, 6, 1, 5};
//        List<List<Integer>> lists = combinationSum(candidates, 7);
//        System.out.println(lists);
        System.out.println(combinationSum2(candidates, 8));

    }

}
