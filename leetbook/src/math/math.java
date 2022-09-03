package math;

import java.util.HashSet;

public class math {

    /**
     * 计数质数
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     * @param n
     * @return
     */
    public static int countPrimes(int n) {

        if(n == 1 || n == 0){
            return 0;
        }
        int ans = 0;
        boolean[] arr = new boolean[n];
        /**
         * 埃筛法
         */
        for (int i = 2; i < n; i++) {
            if(!arr[i]){
                ans++;
                for (int j = 2; j * i < n; j++) {
                    arr[j*i] = true;
                }
            }
        }
        return ans;
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j*j <= i; j++) {
            if(i % j == 0){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(countPrimes(5000000));

//        HashSet<Integer> hashSet = new HashSet<>();
//        System.out.println(hashSet.add(1));
//        boolean add = hashSet.add(1);
//        System.out.println(add);
    }
}
