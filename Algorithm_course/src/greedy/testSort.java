package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class testSort {
    public static void main(String[] args) {
        test();
    }


    public static void test(){
        int[][] arr = new int[3][2];
        arr[0] = new int[]{1, 3};
        arr[1] = new int[]{2, 2};
        arr[2] = new int[]{4, 1};

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
//        System.out.println(Arrays.toString(arr));
    }
}
