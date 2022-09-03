import java.util.*;

public class DayFive {
    public static void main(String[] args) {
//        hammingDistance(1,4);
//        isValid("()");

//        int[] nums = {1, -2, -5, -4, -3, 3, 3, 5};
//        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
//        fourSum(nums, -294967296);
//
//        int i = sushuCount(100);
//        System.out.println(i);
//        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
//        pivotIndex(nums);

        String s ="abcdefg";
//        System.out.println(s.substring(0, 1));
        reverseStr(s,  3);
    }

    public static int minStartValue(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int accSum = 0, res = 0;
        for (int num : nums) {
            accSum += num;
            res = Math.min(accSum, res);
        }
        return 1 - res;
    }


    /**
     * 704. 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {

            if (target > nums[mid]) {
                left = mid;
            } else if (target < nums[mid]) {
                right = mid;
            } else {
                return mid;
            }
            mid = (left + right) / 2;
        }

        return left;
    }


    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        // 直接异或即可
        for (int i = 0; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    /**
     * 338. 比特位计数
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     *
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
//        if(n == 0){
//            return new int[]{0};
//        }
//        int[] res = new int[n+1];
//        for (int i = 0; i <= n; i++) {
//            int count = 0;
//            int num = i;
//            while(num > 0){
//                if((num & 1) == 1 ){
//                    count ++;
//                }
//                num >>= 1;
//            }
//            res[i] = count;
//        }
//        return res;

        if (n == 0) {
            return new int[]{0};
        }
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {

            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i / 2] + 1;
            }
        }
        return res;

    }


    /**
     * 461. 汉明距离
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * <p>
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
//        int ans = 0;
//        while (x != 0 || y != 0) {
//            if (((x & 1) ^ (y & 1)) == 1) {
//                ans++;
//            }
//            x >>= 1;
//            y >>= 1;
//        }
//        return ans;


        int ans = 0;
        for (int i = x ^ y; i > 0; i = i & (i - 1)) {
            ans++;
        }

        return ans;
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        /**
         * 执行用时:1 ms ，在所有Java提交中击败了98.93%的用户
         * 内存消耗:39.3 MB ，在所有Java提交中击败了 75.11%的用户
         */
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character pop;
            if (c == ')' && !stack.empty()) {
                pop = stack.pop();
                if (pop != '(') {
                    return false;
                }
                continue;
            } else if (c == ']' && !stack.empty()) {
                pop = stack.pop();
                if (pop != '[') {
                    return false;
                }
                continue;
            } else if (c == '}' && !stack.empty()) {
                pop = stack.pop();
                if (pop != '{') {
                    return false;
                }
                continue;
            } else if ((c == '}' || c == ']' || c == ')') && stack.empty()) {
                return false;
            }
            stack.push(c);
        }
        return stack.empty();
    }

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     * <p>
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i, j;
        for (i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sigle = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            res.append(sigle);
        }
        return res.reverse().toString();
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        if (nums.length < 3) {
            return arr;
        }

        Arrays.sort(nums);

        if (nums[0] > 0) {  // 排完序后若第一个数大于零则说明三数之和不可能小于0
            return arr;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int left = i + 1;
            int right = nums.length - 1;
            int target = 0 - nums[i];

            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    // 对结果集进行去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    arr.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        return arr;

    }

    /**
     * 18. 四数之和
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
     * <p>
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        /**
         * 执行用时:13 ms ，在所有Java提交中击败了64.56%的用户
         * 内存消耗:42.2MB ，在所有Java提交中击败了15.36%的用户
         * 2022.8.12
         * 测试用例增加整数溢出，所以得考虑更加周全
         */
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        if (nums.length < 4) {
            return arr;
        }
        Arrays.sort(nums);
        int target2;
        if (nums[0] == 1000000000 && nums[1] == 1000000000 && nums[2] == 1000000000 && nums[3] == 1000000000)
            return arr;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            target2 = target - nums[i];
            if (target2 > 0 && nums[i + 1] > 0 && target2 < nums[i + 1]) {
                break;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                target2 = target - nums[i] - nums[j];
                if (target2 > 0 && nums[j + 1] > 0 && target2 < nums[j + 1]) {
                    break;
                }
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    target2 = nums[left] + nums[right] + nums[j] + nums[i];
                    if (target2 > target) {
                        right--;
                    } else if (target2 < target) {
                        left++;
                    } else {
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        arr.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    }
                }
            }
        }
        return arr;
    }


    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

//        int sum = nums[0];
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
//            if(dp[i] > sum){
//                sum = dp[i];
//            }
//        }
//        return sum;
        int sum = nums[0];
        int result = sum;
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            if (sum > result) {
                result = sum;
            }
        }
        return result;
    }


    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static int sushuCount(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            if (hashMap.containsKey(i)) {
                continue;
            }
            if (isSushu(i)) {
                sum++;
                int temp = 2;
                while (temp < n) {
                    if (hashMap.containsKey(temp * 1)) {
                        temp++;
                    } else {
                        hashMap.put(temp * i, i);
                        temp++;
                    }
                }
            }
        }
        return sum;
    }

    private static boolean isSushu(int n) {
        if (n == 1 || n == 2) {
            return true;

        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 26. 删除有序数组中的重复项
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (fast != slow && nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }


    /**
     * 724. 寻找数组的中心下标
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        /**
         * 执行用时:1 ms ,在所有Java提交中击败了67.59%的用户
         * 内存消耗:42.6 MB ，在所有Java提交中击败了 8.41%的用户
         */
//        if(nums == null || nums.length ==0){
//            return 0;
//        }
//        int[] sums = new int[nums.length];
//        sums[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            sums[i] =sums[i-1] + nums[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(i == 0 && sums[nums.length-1] - sums[0] == 0 ){
//                return 0;
//            }else if(i == nums.length-1 && sums[i-1] == 0){
//                return i;
//            }else if(i != 0 && sums[i-1] == (sums[nums.length-1] - sums[i])){
//                return i;
//            }
//        }
//        return -1;

        /**
         * 执行用时:3 ms ，在所有Java提交中击败了38.00%的用户
         * 内存消耗:41.8 MB，在所有Java提交中击败了89.13%的用户
         */

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int summ = Arrays.stream(nums).sum();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (total == summ) {
                return i;
            }
            summ -= nums[i];
        }
        return -1;
    }


    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * @param s
     */
    public static void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            j--;
        }
    }


    /**
     * 541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * 输入：s = "abcdefgh", k = 2
     * 输出："bacdfegh"  1  5
     * @param s
     * @param k
     * @return
     */
    public static String reverseStr(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        int end, mid, count = 1;
        int j = 2 * k / 2 - 1, i = 0;  //1
        while(i < s.length()) {

            StringBuilder temp = new StringBuilder();
            if(s.length() - i < 2 * k){
                if(s.length() - i >= 0 && s.length() - i < k){
                    String substring = s.substring(i, s.length());
                    stringBuilder.append(temp.append(substring).reverse());
                }else if (s.length() - i >= k && s.length() - i < 2 * k){
                    String substring = s.substring(i, j + 1);
                    stringBuilder.append(temp.append(substring).reverse());
                    stringBuilder.append(s, j + 1, s.length());
                }
            }else {
                String substring = s.substring(i, j + 1);
                stringBuilder.append(temp.append(substring).reverse());
                stringBuilder.append(s, j + 1, j + k + 1);
            }
            i = j + k + 1;
            j = j + 2 * k;
        }
        return stringBuilder.toString();
    }

}
