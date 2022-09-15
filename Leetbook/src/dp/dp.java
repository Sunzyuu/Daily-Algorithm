package dp;

import tree.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * 动态规划汇总
 */
public class dp {

    /**
     * 746. 使用最小花费爬楼梯
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // dp[i][j] 表示从(0,0)到(i,j)路径数量
        for (int i = 0; i < m; i++) {       // 递推公式dp[i][j] = dp[i-1][j] + dp[i][j-1]
            dp[i][0] = 1;               // dp初始化 因为在一条直线上路径一定为1
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }


    /**
     * 343. 整数拆分
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        /*
        数论的方法
        */
        if (n == 2) return 2;
        if (n == 3) return 3;
        if (n == 4) return 4;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res = res * n;
        return res;
    }

    public int integerBreakDp(int n) {
        //dp[i] 为正整数 i 拆分后的结果的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    /**
     * 01背包问题
     *
     * @param weight
     * @param value
     * @param bagSize
     * @return
     */
    public static int bag(int[] weight, int[] value, int bagSize) {
        int[][] dp = new int[weight.length][bagSize + 1]; // dp[i][j] 表示在j容量的情况下i个物品时获取的最大价值
        // dp数组初始化  初始化时只有物品0，当背包容量>=weight[0]才需要填入value[0]
        for (int i = weight[0]; i < weight.length; i++) {
            dp[0][i] = value[0];
        }
        // 先遍历物品再遍历背包容量
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j < weight[i]) dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[weight.length - 1][bagSize];
    }


    public static int bag1(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;

        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[bagSize];
    }


    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }

    /**
     * 1049. 最后一块石头的重量 II
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        /// 首先分析得出将这堆石头分成质量最接近的两堆后，最后粉碎剩下的质量是最小的
        // 转化成01背包问题 dp[i]表示i容量的背包能放下的最大价值 此题的重量为stones[i] 价值也为stones[i]
        // 1 <= stones.length <= 30
        // 1 <= stones[i] <= 100
        // 背包的最大容量为30 * 100 = 30000此处分一半就行 15000；
        int sum = Arrays.stream(stones).sum();
        int target = sum >> 1;
        int[] dp = new int[15005];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return (sum - target) - dp[target];

    }

    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 所有正数的和S 那么负数和为sum - S
        // 二者关系为 S - (sum - S) = target
        // S = (sum + target) >> 1
        // 因为所有数都为整数，所以S必须为整数(target + sum) % 2 != 0 时无解
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        if (size < 0) size = -size;
        int[] dp = new int[size + 1];
        dp[0] = 1;  // 一定要初始化 如果不初始化的话 所有dp都为0
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
                /**
                 * 那么考虑nums[i]的话（只要搞到nums[i]），凑成dp[j]就有dp[j - nums[i]] 种方法。
                 * 例如：dp[j]，j 为5，
                 * 已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 dp[5]。
                 * 已经有一个2（nums[i]） 的话，有 dp[3]种方法 凑成 dp[5]。
                 * 已经有一个3（nums[i]） 的话，有 dp[2]中方法 凑成 dp[5]
                 * 已经有一个4（nums[i]） 的话，有 dp[1]中方法 凑成 dp[5]
                 * 已经有一个5 （nums[i]）的话，有 dp[0]中方法 凑成 dp[5]
                 */
            }
        }
        return dp[size];
    }

    /**
     * 474. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        // 多重01背包问题
        // dp[i][j]表示为最多有i个0和j个1的strs的最大子集的大小为dp[i][j]
        // 如果当前的str中有zeroNumber个0，oneNumber个1则可以由dp[i - zeroNumber][j - oneNmber] + 1 推出
        // 遍历的时候需要取dp[i][j]的最大值dp[i][j] = Math.max(dp[i][j], dp[i -zeroNumber][j - oneNumber])
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (String str : strs) {
            int zeroNumber = 0, oneNumber = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zeroNumber++;
                } else {
                    oneNumber++;
                }
            }
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNumber][j - oneNumber]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i])
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * 题目数据保证答案符合 32 位整数范围。
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1]; // dp[i] 表示凑成目标正整数为i的排列个数为dp[i]
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[i]表示凑成i元所用的最少硬币数
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 279. 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; ++i) {
            // 位置i只依赖 i-j*j 的位置，如 i-1、i-4、i-9 等等位置，才能满足完全平方分割的条件。
            // 因此dp[i]可以取的最小值即为 1 + min(dp[i-1],dp[i-4],dp[i-9]...)
            dp[i] = i;  // 最坏的情况: 所有被加起来的完全平方数都是1
            for (int j = 1; i - j * j >= 0; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);  // dp[i] 表示数字i最少可以由几个完全平方数相加构成
            }
        }
        return dp[n];
    }

    /**
     * 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; // dp[i]=1 表示长度为i的字符串s可以拆分成字典中的单词
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) { // 从1开始是因为substring()是左闭右开区间
            for (int j = 0; j < i; j++) { //
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {  // dp[j] == true且s[j,i]出现在字典中那么dp[i]==true
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] += Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }


    /**
     * 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {

        if (nums.length == 1) return nums[0];

        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    public static int robRange(int[] nums, int start, int end) {
        if (end == start) return nums[start];
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    /**
     * 337. 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     * @param root
     * @return
     */
    public static int rob3(TreeNode root) {
        int[] res = robActions(root);
        return Math.max(res[0], res[1]);
    }

    // 树形dp
    private static int[] robActions(TreeNode root) {
        int[] res = new int[2];  // res[0]表示不抢劫该节点的能获取的最大值
        // res[1]表示抢劫该节点的能获取的最大值
        if (root == null) return res;
        //首先进行后续遍历然后再对根节点进行相应的逻辑处理
        int[] left = robActions(root.left);
        int[] right = robActions(root.right);
        // 对根节点有两种选择
        // 1.选择抢劫该节点，那么左右儿子都不能抢劫 res[1] = root.val + left[0] + right[0]
        // 2.不抢劫该节点，那么选择左右儿子中抢和不抢的最大值 res[0] = max(left[0], left[1]) + max(right[0], right[1])
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        // dp[i][0] 表示第i天持有股票获得的能最大价值
        // dp[i][1]表示第i天没有持有股票能获得的最大价值
        // 当持有股票时 有两种情况：    第i-1天已经持有股票 那么第i天的状态就是dp[i-1][0]
        //                         在第i天买入股票 那么获得的最大价值就是-price[i]  因此dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
        // 当没有持有股票时也有两种情况：第i-1天也没有持有股票 那么第i天的状态就是dp[i-1][1]
        //                         在第i天卖出股票,说明i-1天已经买入股票，那么当天能够获得最大价值就是dp[i-1][0] + pricesp[i]
        //                          dp[i][1] = Math.max(dp[i-1][1], prices[i] + dp[i-1][0]);
        dp[0][0] -= prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 1) return 1;
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    /**
     * 674. 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }

            if (res < dp[i]) res = dp[i];
        }
        return res;
    }

    /**
     * 718. 最长重复子数组
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        // dp[i][j] ：以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]。
        // （特别注意： “以下标i - 1为结尾的A” 标明一定是 以A[i-1]为结尾的字符串 ）
        int res = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                if (res < dp[i][j]) res = dp[i][j];
            }
        }
        return res;
    }

    public int findLength1(int[] nums1, int[] nums2) {
        int[] dp = new int[nums1.length + 1];
        int res = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }

                if (res < dp[j]) res = dp[j];
            }
        }
        return res;
    }

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length() - 1][text2.length() - 1];
    }

    /**
     * 1035. 不相交的线
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
     *  nums1[i] == nums2[j]
     * 且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        /**
         * 1,4,2
         * 1,2,4
         *可以画出两条不交叉的线
         * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
         * 什么情况下连线才不会出现相交的线呢？？
         * 从上面的例子可以看出1,4是两个数组的最长公共子序列
         * 同理1,2也是，当连1和2时两条线也时不相交的，所以这里就是求两个数组的最长公共子序列
         */
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];


        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);

            if (sum[i] > res) {
                res = sum[i];
            }
        }
        return res;
    }

    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        /**
         * 双指针
         */
        int p1 = 0, p2 = 0;
        int len1 = s.length();
        int len2 = t.length();
        // len1 <= len2
        while (p1 < len1 && p2 < len2) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p2++;
            }
        }
        return p1 == len1;
    }


    public boolean isSubsequence2(String s, String t) {
        /**
         * dp
         */
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]
        // 当s.charAt(i - 1) == t.charAt(j-1)相同时，t中找到了一个字符在s中也出现了
        // dp[i][j] = dp[i - 1][j - 1] + 1
        //if (s[i - 1] != t[j - 1])，此时相当于t要删除元素，t如果把当前元素t[j - 1]删除，
        // 那么dp[i][j] 的数值就是 看s[i - 1]与 t[j - 2]的比较结果了，
        // 即：dp[i][j] = dp[i][j - 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2] == len1;
    }

    /**
     * 115. 不同的子序列
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        /**
         * dp[i][j]表示以i-1长s和j-1长的t 中s的子序列中t出现的次数
         */
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if(t.charAt(i - 1) == s.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        // for (int i = 0; i < dp.length; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[t.length()][s.length()];
    }

    /**
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     * 每步 可以删除任意一个字符串中的一个字符。
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // dp[i][j]以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。

        // 初始化
        // dp[0][j]是指 word1为空字符串，那么长j-1的word2删除到空字符串的次数自然为j
        // dp[i][0]同上
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length() ; i++) {
            for (int j = 1; j <=word2.length() ; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j] + 1,dp[i][j-1] + 1), dp[i-1][j-1] + 2);
                }
            }
        }
        return dp[word1.length()][word2.length()];

    }


    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        System.out.println(bag(weight, value, bagsize));
        System.out.println(bag1(weight, value, bagsize));
    }
}
