package CodeThink;

import java.util.Arrays;

public class ArrayThink {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
//        System.out.println(removeElement(nums, 3));


        int[] nums2 = {-4, -1, 0, 3, 10};
        int[] ress = sortedSquares(nums);

        int[][] res = generateMatrix(3);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }


    public static int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[leftIndex] * nums[leftIndex] >= nums[rightIndex] * nums[rightIndex]) {
                res[i] = nums[leftIndex] * nums[leftIndex];
                leftIndex++;
            } else {
                res[i] = nums[rightIndex] * nums[rightIndex];
                rightIndex--;
            }
        }
        return res;
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;

        int sum = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            rightIndex++;
            while (sum >= s) {
                sum -= nums[leftIndex];
                leftIndex++;
                res = Math.min(rightIndex - leftIndex, res);
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int count = 1;
        while (count <= n * n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = count;
                count++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = count;
                count++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                res[bottom][i] = count;
                count++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                res[i][left] = count;
                count++;
            }
            left++;

        }
        return res;
    }

}
