package dp;

import java.util.Arrays;

/**
 * 动态规划汇总
 */
public class dp {

    /**
     * 746. 使用最小花费爬楼梯
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(cost[i-1], cost[i-2]) + cost[i];
        }
        return Math.min(dp[cost.length-2], dp[cost.length-1]);
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
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
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < col; i++) {
            if(obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }


    /**
     * 343. 整数拆分
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        /*
        数论的方法
        */
        if(n == 2) return 2;
        if(n == 3) return 3;
        if(n == 4) return 4;
        int res = 1;
        while (n > 4){
            res *= 3;
            n-=3;
        }
        res = res * n;
        return res;
    }

    public int integerBreakDp(int n) {
        //dp[i] 为正整数 i 拆分后的结果的最大乘积
        int[]dp=new int[n+1];
        dp[2]=1;
        for (int i = 3; i <=n; i++) {
            for (int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j*dp[i-j]));
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
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
     * @param weight
     * @param value
     * @param bagSize
     * @return
     */
    public static int bag(int[] weight, int[] value, int bagSize){
        int[][] dp = new int[weight.length][bagSize + 1]; // dp[i][j] 表示在j容量的情况下i个物品时获取的最大价值
        // dp数组初始化  初始化时只有物品0，当背包容量>=weight[0]才需要填入value[0]
        for (int i = weight[0]; i < weight.length; i++) {
            dp[0][i] = value[0];
        }
        // 先遍历物品再遍历背包容量
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if(j < weight[i]) dp[i][j] = dp[i -1][j];
                else
                    dp[i][j] = Math.max(dp[i- 1][j], dp[i - 1][j - weight[i]] + value[i]);
              }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[weight.length - 1][bagSize];
    }


    public static int bag1(int[] weight, int[] value, int bagSize){
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
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1)return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
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
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        if(size < 0) size = -size;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        System.out.println(bag(weight, value, bagsize));
        System.out.println(bag1(weight, value, bagsize));
    }
}
