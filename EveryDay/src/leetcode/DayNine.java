package leetcode;

import java.util.*;

public class DayNine {


    /**
     * 455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        // 优先考虑饼干，先满足小胃口的人
        for (int i = 0; i < g.length; i++) {
            if (ans < s.length && g[i] <= s[ans]) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 376. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
     * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int ans = 1;
        int preDiff = 0, curDiff = 0;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            int curIndex = i - 1;
            if (curDiff * preDiff <= 0 && curDiff != 0) {
                ans++;
                preDiff = curDiff;
            }
            preDiff = curDiff;
        }
        return ans;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int cover = 0;
        for (int i = 0; i < nums.length; i++) {
            cover = Math.max(i + nums[i], cover); // 当前的最大的覆盖范围
            if (cover >= nums.length - 1)         // 当覆盖范围到达数组的末尾时 表示可以跳到最后
                return true;
        }
        return false;
    }


    /**
     * 45. 跳跃游戏 II
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int result = 0;
        int curCover = 0;  // 当前最大覆盖范围
        int maxCover = 0;  // 当前最大覆盖范围内下一步的最大覆盖范围
        // 需要考虑当前最大覆盖范围和下一步的最大覆盖范围
        for (int i = 0; i < nums.length; i++) {
            maxCover = Math.max(maxCover, nums[i] + i);  // 找到当前覆盖范围中下一个最大的覆盖范围
            if (maxCover >= nums.length - 1) {             // 如果下一个最大覆盖范围到达数组的末尾直接步数+1 跳出循环
                result++;
                break;
            }
            if (i == curCover) {                          // 当走到当前覆盖范围的末尾，需要更新 curCover 为maxCover
                curCover = maxCover;
                result++;
            }
        }
        return result;
    }


    /**
     * 1005. K 次取反后最大化的数组和
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
//        int result = 0;
//        nums = IntStream.of(nums)  // 将数组按照绝对值大小从大到小排序
//                .boxed()
//                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
//                .mapToInt(Integer::intValue).toArray();
//        int len = nums.length;
//        for (int i = 0; i < len; i++) {
//            if(k > 0 && nums[i] < 0){
//                nums[i] = - nums[i];
//                k--;
//            }
//        }
//        if(k % 2 == 1) nums[len -1] = -nums[len-1];
//
//        return Arrays.stream(nums).sum();


        Arrays.sort(nums);
        int result = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            } else if (k > 0 && nums[i] == 0) {
                k = 0;
                break;
            }
            if (Math.abs(nums[temp]) > Math.abs(nums[i])) {
                temp = i;
            }
        }
        if (k % 2 == 1) nums[temp] = -nums[temp];
        return Arrays.stream(nums).sum();
    }


    /**
     * 134. 加油站
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, temp = Integer.MAX_VALUE;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            temp = Math.min(temp, sum);
        }
        if (sum < 0) return -1;
        if (temp >= 0) return 0;

        for (int i = gas.length - 1; i >= 0; i--) {
            temp += gas[i] - cost[i];
            if (temp >= 0) return i;
        }
        return -1;
    }

    /**
     * 135. 分发糖果
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * <p>
     * 你需要按照以下要求，给这些孩子分发糖果：
     * <p>
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int ans = 0;
        int[] candies = new int[ratings.length];
        candies[0] = 1;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        ans = Arrays.stream(candies).sum();
        return ans;
    }


    /**
     * 860. 柠檬水找零
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     * @param bills
     * @return
     */
    public static boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
            } else if (bills[i] == 10) {
                if (fiveCount >= 1) {
                    fiveCount--;
                    tenCount++;
                } else {
                    return false;
                }
            } else {
                if (tenCount >= 1 && fiveCount >= 1) {
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
//        int[][] reslut = new int[people.length][people[0].length];
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        LinkedList<int[]> reslut = new LinkedList<>();
        for (int[] person : people) {
            reslut.add(person[1], person);
        }
        return reslut.toArray(new int[people.length][]);
    }


    /**
     * 452. 用最少数量的箭引爆气球
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
     * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
     * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        int ans = 1;
        if (points.length == 1) return ans;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int minRightEgde = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= minRightEgde) {
                minRightEgde = Math.min(minRightEgde, points[i][1]); // 更新重叠气球的最小右边界
            } else {
                minRightEgde = points[i][1];
                ans++;
            }
        }
        return ans;
    }

    /**
     * 435. 无重叠区间
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     *
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int minRightEgde = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] < minRightEgde) {
                res++;
            } else {
                minRightEgde = intervals[i][1];
            }
        }
        return res;
    }

    /**
     * 763. 划分字母区间
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a'] = i;
        }

        int maxEdge = 0, lastEdge = 0, flag = 0;
        for (int i = 0; i < s.length(); i++) {
            maxEdge = Math.max(maxEdge, chars[s.charAt(i) - 'a']);
            if (maxEdge == i) {
                if (lastEdge == 0 && flag == 0) {
                    res.add(maxEdge - lastEdge + 1);
                    flag++;
                } else {
                    res.add(maxEdge - lastEdge);
                }
                lastEdge = maxEdge;
            }
        }
        return res;
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); //先按照左端点递增排序
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {  // 根据当前左端点与最大右端点的关系对左右边界进行处理
            if (intervals[i][0] <= right) {
                right = Math.max(right, intervals[i][1]);
            } else {
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        res.add(new int[]{left, right}); // 将最后一个左右边界加入到答案中
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 738. 单调递增的数字
     * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
     * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
     *
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = chars.length - 2; i >= 0; i--) {
            if(chars[i] > chars[i + 1]){
                chars[i] --;
                start = i + 1;
            }
        }
        for (int i = start; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int res = 0;
        int minPrice = prices[0] + fee;
        for (int price : prices) {
            if(price + fee < minPrice){
                minPrice = price + fee;
            }else if(price > minPrice){
                res += price - minPrice;
                minPrice = price;
            }
        }
        return res;
    }


    /**
     * 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量
     * @param root
     * @return
     */
    private static int res = 0;
    public static int minCameraCover(TreeNode root) {
        if(minCamera(root) == 0){
            res++;
        }
        return res;
    }
    /**
     节点的状态值：
     0 表示无覆盖
     1 表示 有摄像头
     2 表示有覆盖
     后序遍历，根据左右节点的情况,来判读 自己的状态
     */
    private static int minCamera(TreeNode root) {
        if(root == null){
            return 2;
        }
        int left = minCamera(root.left);
        int right = minCamera(root.right);

        if(left == 2 && right == 2){
            return 0;
        }else if(left ==0 || right == 0){
            res++;
            return 1;
        }else {
            return 2;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, -3, -1, 5, -4};
//        largestSumAfterKNegations(nums, 2);
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int[][] ints = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        findMinArrowShots(points);
//        System.out.println(Arrays.deepToString(merge(ints)));

        monotoneIncreasingDigits(1234);
    }
}
