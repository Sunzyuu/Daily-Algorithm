import javax.print.DocFlavor;
import java.util.*;

public class day13 {


    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < nums.size(); i++) {
            res = Math.min(res, Math.abs(nums.get(i) - nums.get(i-1)));
        }
        return res;
    }

    private void inorder(TreeNode root, ArrayList<Integer> nums) {
        if(root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }


    /**
     * 面试题 01.09. 字符串轮转
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        if(s1.equals(s2)){
            return true;
        }
        char[] chars = s1.toCharArray();
        for (int i = 1; i < s1.length(); i++) {
            StringBuilder tempSb = new StringBuilder();
            tempSb.append(chars, i, s1.length() - i);
            tempSb.append(chars, 0, i);
            String tempStr = tempSb.toString();

            if(tempStr.equals(s2)){
                return true;
            }
        }
        return false;
    }

    public boolean isFlipedString1(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        String ss = s1 + s1;
        // 如果两个字符串为旋转字符串，那么s1+s1后的字符串的一个子串一定包含s2
        if(ss.indexOf(s2) != -1){
            return true;
        }
        return false;
    }

    public static int maxSubSum(int[] nums){
        int sum = nums[0];
        int left = 0;
        int right = 0;
        int res = 0;
        int index = 0;

        for (int i = 1; i < nums.length; i++) {
            if(sum > 0){      //  如果当前累加的结果大于0 则说明可能是最大值 那么就继续加当前元素
                sum += nums[i];
            }else {
                sum = nums[i];
                index = i;    // 记录重新累加时的起始index
            }
            if(sum > res){
                res = sum;  // 更新结果
                left = index; // 更新左右边界
                right = i;
            }
        }

        System.out.println("maxsum is:" + res);
        System.out.println("startIndex is:" + left);
        System.out.println("endIndex is:" + right);
        return res;
    }

    public static void matrixMultiply(int[] p, int n){
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int i = 0; i < n; i++)	//规模为1时，i=j,计算量m[i][j]=m[i][i]=0;
            m[i][i] = 0;
        for (int r = 2; r <= n; r++)	//规模为r时，i<j,计算量m[i][j]=min(m[i][k] + m[k][j] + p[i - 1] * p[k] * p[j])
        {
            for (int i = 0; i < n + 1 - r; i++)		//规模为r，开始元素为i,末尾元素j=i
                // +r-1,末尾元素j<=n,等价于i<=n+1-r ,而i是从0开始，后面不加等号
            {
                int j = i + r -1;
                //p数组为连乘矩阵数组，i从0开始，第i+1个元素，其行数为p[i],列数为p[i+1]
                //从第一个位置断开的m[i][j]
                m[i][j] = m[i][i] + m[i+1][j] + p[i] * p[i+1] * p[j+1];
                s[i][j] = i;
                //从第k个位置断开的m[i][j]
                for (int k = 1+i; k < j; k++)
                {

                    //用t表示不同断开位置的连乘次数，t最小时候，对应为当前m[i][j]
                    int t = m[i][k] + m[k+1][j] + p[i] * p[k+1] * p[j+1];
                    if (t < m[i][j])
                    {
                        m[i][j] = t;
                        s[i][j] = k;  //用s记录断开位置
                    }
                }
            }
        }
        
    }

    /**
     *1694. 重新格式化电话号码
     * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
     * 请你按下述方式重新格式化电话号码。
     * 首先，删除 所有的空格和破折号。
     * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
     * 2 个数字：单个含 2 个数字的块。
     * 3 个数字：单个含 3 个数字的块。
     * 4 个数字：两个分别含 2 个数字的块。
     * 最后用破折号将这些块连接起来。注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
     *
     * 返回格式化后的电话号码。
     * @param number
     * @return
     */
    public static String reformatNumber(String number) {
        char[] charArray = number.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] != ' ' && charArray[i] != '-'){
                stringBuilder.append(charArray[i]);
            }
        }
        char[]  numStr = stringBuilder.toString().toCharArray();
        StringBuilder res = new StringBuilder();

        int len = numStr.length;
        int trueLen = 0;
        if(len % 3 == 1) {
            trueLen = len - 4;
        }else if(len % 3 == 2){
            trueLen = len - 2;
        }else {
            trueLen = len;
        }
        for (int i = 0; i < trueLen; i = i + 3) {
            res.append(numStr, i, 3).append('-');
        }

        if(len % 3 == 1){
            res.append(numStr, trueLen,2).append('-').append(numStr, trueLen + 2, 2);
        }else if(len % 3 == 2){
            res.append(numStr, trueLen, 2);
        }else {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    /**
     * 777. 在LR字符串中交换相邻字符
     * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
     * 现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        /**
         * 根据题意，我们每次移动要么是将 XL 变为 LX，要么是将 RX 变为 XR，而该两者操作可分别看做将 L 越过多个 X 向左移动，将 R 越过多个 X 向右移动。
         * 因此在 start 和 end 中序号相同的 L 和 R 必然满足坐标性质：
         * 序号相同的 L : start 的下标不小于 end 的下标（即 L 不能往右移动）
         * 序号相同的 R : start 的下标不大于 end 的下标（即 R 不能往左移动）
         */
        int index1 = 0,index2 = 0;
        while(index1 < start.length() || index2 < end.length()){
            while(index1 < start.length() && start.charAt(index1)== 'X'){
                index1++;
            }
            while(index2 < start.length() && end.charAt(index2) == 'X'){
                index2++;
            }
            if(index1 == start.length() || index2 == start.length()){
                return index1 == index2;
            }
            if(start.charAt(index1) != end.charAt(index2)){
                return false;
            }
            if(start.charAt(index1) == 'L' && index1 < index2){
                return false;
            }
            if(start.charAt(index1) == 'R' && index1 > index2){
                return false;
            }
            index1 ++;
            index2 ++;
        }
        return true;
    }


    /**
     * 1784. 检查二进制字符串字段
     * 给你一个二进制字符串 s ，该字符串 不含前导零 。
     * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
     * 如果 s 中 由连续若干个 '1' 组成的字段 数量不超过 1，返回 true​​​ 。否则，返回 false 。
     * @param s
     * @return
     */
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }


    /**
     * 921. 使括号有效的最少添加
     * 只有满足下面几点之一，括号字符串才是有效的：
     * 它是一个空字符串，或者
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int needRight = 0, needLeft = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++needRight;
            } else {
                if (needRight > 0) {
                    --needRight;
                } else {
                    ++needLeft;
                }
            }
        }
        return needLeft + needRight;
    }

    /**
     * 811. 子域名访问计数
     * 网站域名 "discuss.leetcode.com" 由多个子域名组成。顶级域名为 "com" ，二级域名为 "leetcode.com" ，最低一级为 "discuss.leetcode.com" 。当访问域名 "discuss.leetcode.com" 时，同时也会隐式访问其父域名 "leetcode.com" 以及 "com" 。
     * 计数配对域名 是遵循 "rep d1.d2.d3" 或 "rep d1.d2" 格式的一个域名表示，其中 rep 表示访问域名的次数，d1.d2.d3 为域名本身。
     * 例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
     * 给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        int[] counts = new int[cpdomains.length];
        String[] domains = new String[cpdomains.length];

        for (int i = 0; i < cpdomains.length; i++) {
            counts[i] = Integer.parseInt(cpdomains[i].split(" ")[0]);
            domains[i] = cpdomains[i].split(" ")[1];
        }

        for (int i = 0; i < cpdomains.length; i++) {
            String[] strings = domains[i].split("\\.");
            String temp = "";
            for (int j = strings.length - 1; j >= 0; j--) {
                temp = strings[j] + temp;
                hashMap.put(temp, hashMap.getOrDefault(temp, 0) + counts[i]);
                temp ="." + temp;
            }
        }

        List<String> res = new ArrayList<>();
        Set<String> strings = hashMap.keySet();
        for (String string : strings) {
            res.add(hashMap.get(string) + " " + string);
        }
        return res;
    }

    /**
     * 927. 三等分
     * 给定一个由 0 和 1 组成的数组 arr ，将数组分成  3 个非空的部分 ，使得所有这些部分表示相同的二进制值。
     * 如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来：
     * arr[0], arr[1], ..., arr[i] 为第一部分；
     * arr[i + 1], arr[i + 2], ..., arr[j - 1] 为第二部分；
     * arr[j], arr[j + 1], ..., arr[arr.length - 1] 为第三部分。
     * 这三个部分所表示的二进制值相等。
     * 如果无法做到，就返回 [-1, -1]
     * 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的，所以 [0,1,1] 和 [1,1] 表示相同的值。
     * @param arr
     * @return
     */
    public static int[] threeEqualParts(int[] arr) {
        int[] record = new int[arr.length + 1];

        int oneCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1){
                record[++oneCount] = i;
            }
        }

        if(oneCount == 0){
            return new int[]{0, arr.length - 1};
        }
        if(oneCount % 3 != 0){
            return new int[]{-1, -1};
        }

        int gn = oneCount / 3;  // 每组中1的个数
        int lastZeroCount = arr.length - 1 - record[oneCount];  // 最后一组中末尾0的个数
        // 每组中末尾0的个数小于lastZeroCount 则没有答案
        if(record[gn * 2] - record[gn] - 1 < lastZeroCount ||  record[gn * 3] - record[gn * 2] - 1 < lastZeroCount){
            return new int[]{-1, -1};
        }

        int tail1 = record[gn] + lastZeroCount;
        int tail2 = record[gn * 2] + lastZeroCount;
        int tail3 = record[gn * 3] + lastZeroCount;

        int nums = Math.min(Math.min(tail1 + 1, tail2 - tail1), tail3 - tail2);

        int[] res = new int[]{tail1, tail2 + 1};
        while(nums-- > 0){
            if(arr[tail1] != arr[tail2] || arr[tail3] != arr[tail2] ||arr[tail1] != arr[tail3]){
                return new int[]{-1, -1};
            }
            tail1--;
            tail2--;
            tail3--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        maxSubSum(nums);
        reformatNumber("1-23-45 6");

        threeEqualParts(nums);
    }
}
