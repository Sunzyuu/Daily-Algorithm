package leetcode;

import java.util.*;

public class Day12 {

    /**
     * 129. 求根节点到叶节点数字之和
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     * 叶节点 是指没有子节点的节点。
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        return sumNumbers(root, list, res);
    }

    private static int sumNumbers(TreeNode root, ArrayList<Integer> list, int res) {
        if (root.right == null && root.left == null) {
            res += listToInt(list);
            return res;
        }
        if (root.left != null) {
            list.add(root.left.val);
            sumNumbers(root.left, list, res);
            list.remove(list.size() - 1);
        }


        if (root.right != null) {
            list.add(root.right.val);
            sumNumbers(root.right, list, res);
            list.remove(list.size() - 1);
        }
        return res;
    }

    private static int listToInt(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum = sum * 10 + integer;
        }
        return sum;
    }


    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = count.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement2(int[] nums) {
        int res = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    res = nums[i + 1];
                }
            }
        }
        return res;
    }


    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        for (int[] nums : matrix) {
            search(nums, target);
        }

        return false;
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return true;
            }
            mid = left + (right - left) / 2;
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        // 从矩阵的右上角搜索 (0, matrix[0].length)
        int m = matrix[0].length;
        int n = matrix.length;
        int x = 0;
        int y = m - 1;
        while (x < n && y >= 0) {
            if (target == matrix[x][y]) {
                return true;
            } else if (target < matrix[x][y]) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }


    private Random random = new Random(System.currentTimeMillis());

    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 第 1 大的数，下标是 len - 1;
        // 第 2 大的数，下标是 len - 2;
        // ...
        // 第 k 大的数，下标是 len - k;
        int len = nums.length;
        int target = len - k;

        int left = 0;
        int right = len - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                // pivotIndex > target
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);
        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int le = left + 1;
        int ge = right;
        // 此处循环的作用就是nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }
            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }
            if (le >= ge) {
                break;
            }
            // 此时的nums[le] > pivot 并且 nums[ge] < pivot 因此需要更换二者的位置
            swap(nums, le, ge);
            le++;
            ge--;
        }
        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    /**
     * 841. 钥匙和房间
     * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
     * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
     * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
     *
     * @param rooms
     * @return
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visted = new boolean[rooms.size()];

        bfs(0, rooms, visted);
        for (boolean b : visted) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private static void bfs(int key, List<List<Integer>> rooms, boolean[] visted) {
        if (visted[key]) {
            return;
        }
        visted[key] = true;
        for (Integer k : rooms.get(key)) {
            bfs(k, rooms, visted);
        }
    }

    /**
     * 127. 单词接龙
     * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
     * 每一对相邻的单词只差一个字母。
     * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
     * sk == endWord
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 将数组转换为set加快访问速度
        HashSet<String> set = new HashSet<>(wordList);
        if (set.size() == 0 || !set.contains(endWord)) {
            return 0;
        }
        // 单词队列
        Queue<String> queue = new LinkedList<>();
        // 记录单词对应的路劲长度
        HashMap<String, Integer> map = new HashMap<>();
        queue.offer(beginWord);  //  将beginWord放入队列
        map.put(beginWord, 1);   // 初始化map
        while (!queue.isEmpty()) {
            // 获取队首元素
            String word = queue.poll();
            int path = map.get(word);
            //遍历该单词的每一个位置
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                // 用每个字母替换
                for (char k = 'a'; k <= 'z'; k++) {
                    chars[i] = k;
                    // 将char数组转换成string
                    String newWord = String.valueOf(chars);
                    if (endWord.equals(newWord)) {  //如果已经找到了endWord那么直接返即可
                        return path + 1;
                    }
                    // 如果新单词不是endWord，那么判断是否在wordList中
                    if(set.contains(newWord) && !map.containsKey(newWord)){
                        // newWord在wordList中，那么将其入队列， 并将步数path+1
                        queue.offer(newWord);
                        map.put(newWord, path + 1);
                    }
                }
            }
        }
        return 0;
    }

    public int findKthLargest1(int[] nums, int k) {
        // 创建优先队列
        // 创建一个容量为k的小根堆，那么遍历整个数组的过程中维护这个小根堆，那么其根元素就是数组中第k大的数
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 0; i < k; i++) {  // 首先将数组中的前k个数放入队列中
            priorityQueue.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) { // 继续遍历k到nums.length-1的元素，如果当前元素大于队列的第一个元素，那么就将队列的头元素弹出放入当前元素
            Integer peek = priorityQueue.peek();
            if(nums[i] > peek){
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
        return priorityQueue.peek();
    }


    /**
     * 面试题 17.09. 第 k 个数
     * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        int[] factors = {3, 5, 7};
        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        set.add(1L);
        priorityQueue.offer(1L);
        int res = 0;
        for (int i = 0; i < k; i++) {
            long curr = priorityQueue.poll();
            for (int factor : factors) {
                long next = factor * curr;
                res = (int) curr;
                if(set.add(next)){
                    priorityQueue.offer(next);
                }
            }
        }
        return res;
    }

    public int getKthMagicNumber1(int k) {
        long[] dp = new long[k + 1];
        dp[1] = 1;
        int p1 = 1, p2 = 1, p3 = 1;
        for (int i = 2; i <= k; i++) {
            dp[i] = Math.min(dp[p1] * 3, Math.min(dp[p2] * 5,dp[p3]  * 7));
            // 此处不可以使用if else结构 判断否则答案会变小
            if(dp[i] == dp[p1] * 3){
                p1 ++;
            }
            if(dp[i] == dp[p2]  * 5){
                p2 ++;
            }
            if(dp[i] == dp[p3] * 7){
                p3++;
            }
        }
        return (int) dp[k];
    }

    public static void main(String[] args) {

    }
}
