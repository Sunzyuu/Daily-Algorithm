import java.util.Arrays;

public class SortTest {



    public static void main(String[] args) {
        testQuickSort();

    }
    /**
     * 快速排序
     */
    private static void testQuickSort() {
        int[] array = {5, 9, 1, 9, 5, 3, 7, 6, 1};

        int[] result = randomCommon(0, 1000, 100);
        System.out.println(Arrays.toString(result));
        QuickSort quickSort = new QuickSort(result);
        quickSort.sort();
        quickSort.print();
    }



    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n 随机数个数
     */
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}