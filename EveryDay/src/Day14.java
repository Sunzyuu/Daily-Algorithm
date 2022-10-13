import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Day14 {

    /**
     * 856. 括号的分数
     * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
     * () 得 1 分。
     * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
     * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
     * @param s
     * @return
     */
    public int scoreOfParentheses(String s) {
        /**
         * 初始化将答案 0 放入栈中，从前往后处理整个 s，当遇到 ( 则存入一个占位数值 0，遇到 ) 取出栈顶元素 cur，根据栈顶元素数值值分情况讨论：
         * 栈顶元素 cur = 0cur=0，即当前的 ) 的前一元素即是 ( ，根据 () 得一分的规则可知，我们本次操作得到的分值为 11；
         * 栈顶元素 cur \neq 0cur
         *  =0，即当前 ) 与其匹配的 ( 中间相隔了其他字符，根据 (A) 的得分规则，此时可知得分为 cur \times 2cur×2；
         * 将两者结合可统一为 max(cur * 2, 1)max(cur×2,1)。
         */
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if(c == '('){
                deque.addLast(0);
            }else {
                Integer curr = deque.pollLast();
                deque.addLast(deque.pollLast() + Math.max(curr * 2, 1));
            }
        }

        return deque.peekLast();

    }

    /**
     * 801. 使序列递增的最小交换次数
     * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
     * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
     * 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
     * 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。
     * 注意：
     * 用例保证可以实现操作。
     * @param nums1
     * @param nums2
     * @return
     */
    public int minSwap(int[] nums1, int[] nums2) {
        /**
         * 题目说到，我们只可以交换两个数组同一个索引位置i，最终要使得两个数组都呈现递增，随后题目说到假设输入总是有效的
         * 这句话的意思是说，题目的输入总是至少存在一种方法可以使两个数组变成递增数组，不存在如下的数组：
         *
         *  A :  4 3
         *  B :  1 2
         *
         *  上述的输入无论怎么样尝试，按照题目的方法是没办法将其转化成一个两个递增数组的，既然题目说了输入都是有效的那必然是不存在
         *  上面的输入的。
         *
         *  A: a1,a2,a3,a4...an
         *  B: b1.b2.b3,b4...bn
         *
         *  其实我们可以根据上面的描述知道，对于任意一个位置i，必然有A[i] > A[i-1] || A[i] > B[i-1]
         *  可以简单证明一下 如果i位置有A[i] <= A[i-1] && A[i] <= B[i-1]由于我们只能在同一个索引两个数组
         *  对应位置进行交换，那么可以看出A[i] <= A[i-1] && A[i] <= B[i-1]时，无论怎么换在i位置都不能满足
         *  形成递增数组的要求，所以我们之前的假设成立。同理上面的公式对于B也成立，即B[i] > B[i-1] || B[i] > A[i-1]。
         *
         *  组合A,B的情况，合乎题意的无外乎两种 1. A[i] > A[i-1] && B[i] > B[i-1] 2. B[i] > A[i-1] && A[i] > B[i-1]
         *  对于A[i] > A[i-1] && B[i] > A[i-1] 和 A[i] > B[i-1] && B[i] > B[i-1]  都是一个问题 拿第一个举例，
         *  对于B[i-1]的情况我们无从知晓，这就导致了，没法确定一定在交换后可以形成递增序列，而题目又说输入一定是有效的
         *  那无外乎A[i]>A[i-1]>B[i-1] 或者 A[i] > B[i-1] > A[i-1] 而这又退化成了我们列举的两种情况，综上只需要讨论
         *  我们列举的两种情况即可。
         *  对于某个索引i，如果满足A[i] > A[i-1] && B[i] > B[i-1] 就认为在i位置时，是满足递增的要求的。
         *  对于某个索引i，如果满足A[i] > B[i-1] && B[i] > A[i-1] 就认为需要进行交换才可以满足递增的要求。
         *  在考虑时，每一次我们都只需要考虑当前位置是否可以和他的前面一个位置构成严格递增，针对每一个位置如此考虑最终我们会得到一个
         *  整个数组都是严格递增。
         *  在考虑每一个位置时，需要计算当前位置进行交换使当前位置达到递增的代价和不交换达到递增的代价和不交换使当前位置达到递增的代价。
         *  我们在某个位置上可能的序列有两种针对A[i] > A[i - 1] && B[i] > B[i - 1]这种序列
         *  在该位置i上不交换的使得两数组递增的代价为：dp[0][i] = Math.min(dp[0][i], dp[0][i-1])，因为保持就好不用动就已经满足了递增要求
         *  在该位置上交换使得数组递增的代价为dp[1][i] = Math.min(dp[1][i], dp[1][i-1] + 1)，那么需要一起动这样才能保持递增的要求。
         *  对于i位置序列为A[i] > B[i-1] && B[i] > A[i-1]时
         *  如果该位置不交换，使得两数组递增的代价为（思路就是i不换，就让i-1换，换完自然就满足两数组都递增了）dp[0][i] = Math.min(dp[0][i], dp[1][i-1]);
         *  对于i位置交换，使得两数组递增的代价为（思路就是i换，那么i-1就不要动，换完就满足了两数组都递增）：dp[1][i] = Math.min(dp[1][i], dp[0][i-1] + 1)
         *  我们可以看到对于每一个位置都有两种方法使其呈现递增，那么最终的结果就是两者中的最小值。
         */

        int n = nums1.length;
        int[][] dp = new int[n][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = n + 10;
        }
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            if(nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 2]){
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }
            if(nums1[i] > nums2[i-1] && nums2[i] > nums1[i - 1]){
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
        }

        return Math.min(dp[n-1][0], dp[n-1][1]);
    }

    /**
     *
     * 1790. 仅执行一次字符串交换能否使两个字符串相等
     * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
     * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
     * @param s1
     * @param s2
     * @return
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }

        ArrayList<Integer> diffList = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            if(diffList.size() > 2){
                return false;
            }
            if(s1.charAt(i) != s2.charAt(i)){
                diffList.add(i);
            }
        }

        if(diffList.size() == 0){
            return true;
        }

        if(diffList.size() != 2){
            return false;
        }

        return s1.charAt(diffList.get(0)) == s2.charAt(diffList.get(1)) && s1.charAt(diffList.get(1)) == s2.charAt(diffList.get(0));
    }


    /**
     * 817. 链表组件
     * 中等
     * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。
     *
     * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }
        while(head!= null){
            if(set.contains(head.val)){
                while (head != null && set.contains(head.val)){
                    head = head.next;
                }
                res ++;
            }else {
                head = head.next;
            }
        }
        return res;
    }

    /**
     * 769. 最多能完成排序的块
     * 中等
     * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
     * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
     * 返回数组能分成的最多块数量。
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int res = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);

            if(max == i){
                res ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
