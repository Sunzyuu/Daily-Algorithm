import java.util.*;

public class Day12 {

    /**
     * 129. 求根节点到叶节点数字之和
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     * 叶节点 是指没有子节点的节点。
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        return sumNumbers(root, list, res);
    }

    private static int sumNumbers(TreeNode root, ArrayList<Integer> list, int res) {
        if (root.right == null && root.left == null) {
            res += listToInt(list);
            return res;
        }
        if (root.left != null) {
            list.add(root.left.val);
            sumNumbers(root.left, list, res);
            list.remove(list.size() - 1);
        }


        if (root.right != null) {
            list.add(root.right.val);
            sumNumbers(root.right, list, res);
            list.remove(list.size() - 1);
        }
        return res;
    }

    private static int listToInt(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum = sum * 10 + integer;
        }
        return sum;
    }


    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = count.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement2(int[] nums) {
        int res = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    res = nums[i + 1];
                }
            }
        }
        return res;
    }


    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        for (int[] nums : matrix) {
            search(nums, target);
        }

        return false;
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return true;
            }
            mid = left + (right - left) / 2;
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        // 从矩阵的右上角搜索 (0, matrix[0].length)
        int m = matrix[0].length;
        int n = matrix.length;
        int x = 0;
        int y = m - 1;
        while (x < n && y >= 0) {
            if (target == matrix[x][y]) {
                return true;
            } else if (target < matrix[x][y]) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }


    private Random random = new Random(System.currentTimeMillis());

    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 第 1 大的数，下标是 len - 1;
        // 第 2 大的数，下标是 len - 2;
        // ...
        // 第 k 大的数，下标是 len - k;
        int len = nums.length;
        int target = len - k;

        int left = 0;
        int right = len - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                // pivotIndex > target
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);
        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int le = left + 1;
        int ge = right;

        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }

            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }

            if (le >= ge) {
                break;
            }
            swap(nums, le, ge);
            le++;
            ge--;
        }

        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public static void main(String[] args) {

    }
}
