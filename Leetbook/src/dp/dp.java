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

    public static void main(String[] args) {
    }
}
