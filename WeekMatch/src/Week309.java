import java.util.Arrays;

public class Week309 {

    /**
     * 6167. 检查相同字母间的距离
     * 给你一个下标从 0 开始的字符串 s ，该字符串仅由小写英文字母组成，s 中的每个字母都 恰好 出现 两次 。另给你一个下标从 0 开始、长度为 26 的的整数数组 distance 。
     * 字母表中的每个字母按从 0 到 25 依次编号（即，'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25）。
     * 在一个 匀整 字符串中，第 i 个字母的两次出现之间的字母数量是 distance[i] 。如果第 i 个字母没有在 s 中出现，那么 distance[i] 可以 忽略 。
     * 如果 s 是一个 匀整 字符串，返回 true ；否则，返回 false 。
     * @param s
     * @param distance
     * @return
     */
    public boolean checkDistances(String s, int[] distance) {
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(chars[charArray[i] - 'a']  == -1){
                chars[charArray[i] - 'a']  = i;
                charArray[i] = ' ';
            }
        }
        System.out.println(Arrays.toString(charArray));
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] != ' ' && i - chars[charArray[i] - 'a'] - 1 != distance[charArray[i] - 'a']){
                return false;
            }
        }

        return true;
    }


    public int numberOfWays(int startPos, int endPos, int k) {
        int mod = (int) 1E9 + 7;
        long[][] dp = new long[3005][1005];
        dp[startPos + 1 + 1000][1] = 1;
        dp[startPos - 1 + 1000][1] = 1;
        for (int i = 2; i <= k; i++) {
            for (int j = 1000 + startPos - k; j <=  1000 + startPos + k ; j++) {
                dp[j][i] = dp[j-1][i-1] + dp[j + 1][i - 1];
                dp[j][i] %= mod;
            }
        }
        return (int) dp[1000 + endPos][k];
    }

    public static void main(String[] args) {

    }
}
