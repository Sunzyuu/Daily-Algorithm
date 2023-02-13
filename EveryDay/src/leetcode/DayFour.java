package leetcode;

import java.util.Arrays;

public class DayFour {
    public static void main(String[] args) {
        int[] nums1 = new int[]{8, 2, 5, 1, 7, 6, 3};
        int[] nums2 = new int[]{1, 4, 5, 6, 7, 3, 8};
        int[] nums3 = new int[]{1, 4, 5, 6, 7};
//        bubbleSort(nums3);
//        System.out.println(Arrays.toString(nums3));
//        selectSort(nums1);
//        System.out.println(Arrays.toString(nums1));
//        insertSort(nums1);

//        quickSort(nums1, 0, nums1.length-1);
//        shellSort(nums1);
        int[] ints = megerSort(nums1);
        System.out.println(Arrays.toString(ints));
//
//        int[] testNums1 = new int[]{2,5,7};
//        int[] testNums2 = new int[]{3,6,8};
//        int[] meger = meger(testNums1, testNums2);
//        System.out.println(Arrays.toString(meger));
    }


    /**
     * 冒泡排序
     *
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {
            // 每一轮将第i大的数移到正确的位置
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmep = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmep;
                    flag = true;
                }
            }

            if (!flag) {
                return nums;
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * 选择排序
     *
     * @param nums
     * @return
     */
    public static int[] selectSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {

            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * 插入排序
     *
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int currentValue; // 当前待排序的元素
        for (int i = 0; i < nums.length - 1; i++) {
            int preIndex = i;  // 已被排序的数据索引
            currentValue = nums[preIndex + 1];
            // 在已被排序过数据中倒序寻找合适的位置，如果当前待排序数据比比较的元素要小,将已排好序的元素整体向后移
            while (preIndex >= 0 && currentValue < nums[preIndex]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = currentValue;
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }


    /**
     * 快速排序
     *
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums, int start, int end) {
        if (nums.length < 1 || start < 0 || end >= nums.length || start > end) {
            return null;
        }
        int zoneIndex = partion(nums, start, end);
        if (zoneIndex > start) {
            quickSort(nums, start, zoneIndex - 1);
        }
        if (zoneIndex < end) {
            quickSort(nums, zoneIndex + 1, end);
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    private static int partion(int[] nums, int start, int end) {
        if (start == end) return start;
        // 基准数指针，这里采用的时随机获取指针的方式，可以避免有序数组的时间复杂度很高的情况
        int pivot = (int) (start + Math.random() * (end - start + 1));

        // 分区指针，一开始指向数组开始的下标-1
        int zoneIndex = start - 1;
        // 将基准数与数组末尾的数交换位置
        swap(nums, pivot, end);
        for (int i = start; i <= end; i++) {
            // 如果当前数小于等于基准数
            if (nums[i] <= nums[end]) {
                zoneIndex++;
                if (i > zoneIndex) {
                    swap(nums, i, zoneIndex);
                }
            }
        }
        return zoneIndex;
    }

    private static void swap(int[] nums, int pivot, int end) {
        int temp = nums[pivot];
        nums[pivot] = nums[end];
        nums[end] = temp;
    }


    /**
     * 希尔排序
     *
     * @param nums
     * @return
     */
    public static int[] shellSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        for (int gap = nums.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < nums.length; i++) {
                int j = i;
                while (j - gap >= 0 && nums[j] < nums[j - gap]) {
                    swap(nums, j, j - gap);
                    j -= gap;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public static int[] megerSort(int[] nums) {
        if (nums.length < 2) return nums;
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        return meger(megerSort(left), megerSort(right));
    }

    private static int[] meger(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int index = 0;
        int p1 = 0, p2 = 0;
        while (index < result.length) {
            if (p1 >= left.length) {
                result[index++] = right[p2++];
            } else if (p2 >= right.length) {
                result[index++] = left[p1++];
            } else if (left[p1] < right[p2]) {
                result[index++] = left[p1++];
            } else {
                result[index++] = right[p2++];
            }
        }
        return result;
    }

    /**
     * 计数排序
     * 适用于数组中数据较为连续，且数据之间跨度较小
     *
     * @param nums
     * @return
     */
    public static int[] countSort(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        int bias = 0 - min; // 偏移量
        int[] index = new int[max - min + 1];

        for (int i = 0; i < nums.length; i++) {
            index[nums[i] + bias] ++;
        }
        for (int i = 0 , p = 0; p < nums.length; ) {
            if(index[i] != 0){
                nums[p] = i - bias;
                index[i] --;
                p++;
            }else {
                i++;
            }
        }
        return nums;
    }

}
