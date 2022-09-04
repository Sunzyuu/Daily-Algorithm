import java.util.HashSet;

public class string {

    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            chars[temp - 'a']++;
        }

        char target;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (chars[i] == 1) {
                ans = Math.min(ans, s.indexOf(i + 'a'));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = 0;
        boolean flag = x > 0;
        if (!flag) {
            x = Math.abs(x);
        }
        int res = ans;
        while (x > 0) {
            int count = x % 10;
            ans = ans * 10 + count;
            if ((ans - count) / 10 != res) {
                return 0;
            }
            x = x / 10;
            res = ans;
        }

        if (!flag) {
            ans = -ans;
        }


        return ans;
    }


    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnoilh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        /**
         * 执行用时: 1 ms ,在所有Java提交中击败了100.00%的用户
         * 内存消耗:41.3 MB ，在所有Java提交中击败了57.93%的用户
         */
        s = s.trim();
        if(s.length() == 0 || s == null){
            return 0;
        }
        long ans = 0;
        boolean flag = false, isPostive = true;
        int carry = 1;
        int i=0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+'){
            isPostive = s.charAt(0) == '+';
            i++;
        }
        long temp = ans;
        for (; i < s.length(); i++) {

            if (s.charAt(i) == '-' || s.charAt(i) == '+' || !Character.isDigit(s.charAt(i))) {
                break;
            } else if (Character.isDigit(s.charAt(i))) {
                ans = (s.charAt(i) - '0') + ans * carry;
                carry = 10;
                flag = true;
                if((ans/10) != temp){
                    return isPostive ? Integer.MAX_VALUE: Integer.MIN_VALUE;
                }
                temp = ans;
            } else if(!Character.isDigit(s.charAt(i)) && flag){
                break;
            }
        }
        if(!isPostive){
            ans = -ans;
        }
        if(ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE){
            return isPostive ? Integer.MAX_VALUE: Integer.MIN_VALUE;
        }
        return (int) ans;
    }


    /**
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * 描述前一项，这个数是 111221 即 “ 三 个 1 + 二 个 2 + 一 个 1 “， 记作 "312211"
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        if(n == 2)
            return "11";
        String str = "11";
        for (int i = 3; i <= n; i++) {
            StringBuilder res = new StringBuilder();
            int count = 1 ,j;
            char temp = str.charAt(0);
            for (j = 1; j <= str.length(); j++) {

                while(j < str.length() && temp == str.charAt(j)){
                    count++;
                    j++;
                }
                if(j < str.length()){
                    temp = str.charAt(j);
                }
                res.append(count).append(str.charAt(j-1));
                count = 1;
            }
            str = res.toString();

        }
        return str;
    }


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        String pre = strs[0]; // 作为模式串
        int index = 1;
        while(index < strs.length){
            while(strs[index].indexOf(pre) != 0){
                int value = strs[index].indexOf(pre);
                pre = pre.substring(0, pre.length() - 1);
            }
            index++;
        }
        return pre;
    }


    public static void main(String[] args) {

//        reverse(1534236469);
//        myAtoi("-91283472332");
        System.out.println(countAndSay(5));
        String s1 = "aaabbcc";
        String s2 = "bbaaabbccddd";
        String s3 = "aaabbcc";
        System.out.println(s2.indexOf(s1));

        String[] strs = new String[]{"flower","flow","flight"};
        longestCommonPrefix(strs);
    }
}
