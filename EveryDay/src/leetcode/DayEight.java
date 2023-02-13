package leetcode;

import java.util.*;

public class DayEight {


    /**
     * 93. 复原 IP 地址
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * <p>
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12) return result;
        parttion(s, 0, 0, result);
        return result;
    }

    private static void parttion(String s, int index, int pointNum, List<String> result) {
        if (pointNum == 3) { // 判断ip的最后一部分是否合法
            if (isValidIp(s, index, s.length() - 1)) {
                result.add(s);
            }
        }
        for (int i = index; i < s.length() - 1; i++) {
            if (isValidIp(s, index, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNum++;
                parttion(s, i + 2, pointNum, result);
                pointNum--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            } else {
                break;
            }
        }
    }

    private static boolean isValidIp(String s, int left, int right) {
        if (left > right) return false;
        if (left != right && s.charAt(left) == '0')
            return false;

        int num = 0;
        for (int i = left; i < right; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }

            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) {
                return false;
            }
        }
        return true;
    }


    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
//        result.add(new ArrayList<>());
        subsets(nums, 0, 1, result, path);
        return result;
    }

    private static void subsets(int[] nums, int index, int count, List<List<Integer>> result, LinkedList<Integer> path) {
        result.add(new ArrayList<>(path));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            subsets(nums, i + 1, count, result, path);
            path.removeLast();
        }
    }


    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        subsets2(nums, 0, result, path);
        return result;
    }

    private static void subsets2(int[] nums, int index, List<List<Integer>> result, LinkedList<Integer> path) {
        result.add(new ArrayList<>(path));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            subsets2(nums, i + 1, result, path);
            path.removeLast();
        }
    }


    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        permute(nums, 0, used, result, path);
        return null;
    }

    private void permute(int[] nums, int index, boolean[] used, List<List<Integer>> result, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            permute(nums, i + 1, used, result, path);
            used[i] = false;
            path.removeLast();
        }
    }


    private static HashSet<String> hashSet = new HashSet<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUnique(nums, 0, used, result, path);
        return result;
    }

    private static void permuteUnique(int[] nums, int index, boolean[] used, List<List<Integer>> result, LinkedList<Integer> path) {
        if (path.size() == nums.length && !hashSet.contains(new ArrayList<>(path).toString())) {
            result.add(new ArrayList<>(path));
            hashSet.add(new ArrayList<>(path).toString());
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (used[i])
                continue;
            used[i] = true;
            path.add(nums[i]);
            permuteUnique(nums, i + 1, used, result, path);
            used[i] = false;
            path.removeLast();
        }
    }

    private void permuteUnique2(int[] nums, int index, boolean[] used, List<List<Integer>> result, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
//            if(used[i])
//                continue;
            if (used[i] == false) {
                used[i] = true;
                path.add(nums[i]);
                permuteUnique2(nums, i + 1, used, result, path);
                used[i] = false;
                path.removeLast();
            }
        }
    }


    /**
     * 1669. 合并两个链表
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
     * 下图中蓝色边和节点展示了操作后的结果：
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode pre = list1;
        int i = 1;
        for (; i < a; i++) {
            pre = pre.next;
        }
        ListNode pre2 = pre.next;
        while (pre2 != null && i <= b) {
            pre2 = pre2.next;
            i++;
        }
        ListNode post = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        pre.next = list2;
        post.next = pre2;
        return list1;
    }


    /**
     * 51. N 皇后
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * <p>
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] charQueens = new char[n][n];
        for (char[] chars : charQueens) {
            Arrays.fill(chars, '.');
        }
        findResult(result, charQueens, 0, n);
        return result;
    }

    private static void findResult(List<List<String>> result, char[][] charQueens, int row, int n) {
        if (row == n) {
            result.add(Arrays2List(charQueens));
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, charQueens)) {
                charQueens[row][col] = 'Q';
                findResult(result, charQueens, row + 1, n);
                charQueens[row][col] = '.';
            }
        }
    }

    /**
     * 判断是否可以放皇后
     *
     * @param row
     * @param col
     * @param n
     * @param charQueens
     * @return
     */
    private static boolean isValid(int row, int col, int n, char[][] charQueens) {
        for (int i = 0; i < row; i++) {
            if (charQueens[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (charQueens[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (charQueens[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static List<String> Arrays2List(char[][] charQueens) {
        ArrayList<String> list = new ArrayList<>();
        for (char[] charQueen : charQueens) {
            list.add(String.copyValueOf(charQueen));
        }
        return list;
    }


    /**
     * 37. 解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * <p>
     * 数独的解法需 遵循如下规则：
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     *
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        solveSudoku2(board);
    }

    private static boolean solveSudoku2(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.')
                    continue;
                for (char k = '1'; k <= '9'; k++) {
                    if (isValidSudou(i, j, k, board)) {
                        board[i][j] = k;
                        if (solveSudoku2(board))
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSudou(int row, int col, char k, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == k)
                return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == k)
                return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == k)
                    return false;
            }
        }
        return true;
    }


    /**
     * 36.有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !isValidSudoku(i, j, board[i][j], board))
                    return false;
            }
        }
        return true;
    }

    private static boolean isValidSudoku(int row, int col, char k, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == k)
                return false;
        }

        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col] == k)
                return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (board[i][j] == k)
                    return false;
            }
        }
        return true;
    }
    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {

        String[] nums = {
                "",    // 0
                "",    // 1
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs",// 7
                "tuv", // 8
                "wxyz",// 9
                 };
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0 ){
            return result;
        }
        letterCombinations(result, digits, nums, 0);
        return result;
    }


    private static StringBuilder temp = new StringBuilder();
    private static void letterCombinations(List<String> result, String digits, String[] nums, int index) {
        if(index == digits.length()){
            result.add(temp.toString());
            return;
        }
        String str = nums[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            letterCombinations(result, digits, nums, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }


    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        System.out.println(subsets(nums));
        int[] nums = {1, 2, 2};
//        System.out.println(subsetsWithDup(nums));
        System.out.println(letterCombinations("234"));
    }
}
