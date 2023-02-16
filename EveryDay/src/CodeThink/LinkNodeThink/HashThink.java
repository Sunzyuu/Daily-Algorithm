package CodeThink.LinkNodeThink;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashThink {
    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        int[] recored = new int[26];

        for (int i = 0; i < s.length(); i++) {
            recored[s.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < t.length(); i--) {
            recored[t.charAt(i) - 'a'] --;
        }

        for (int i = 0; i < recored.length; i++) {
            if(recored[i] != 0){
                return false;
            }
        }
        return true;
    }


    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hashSet.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if(hashSet.contains(nums2[i])){
                resSet.add(nums2[i]);
            }
        }


        return resSet.stream().mapToInt(x -> x).toArray();
    }

    public boolean isHappy(int n) {
        HashSet<Integer> hash = new HashSet<>();
        while(n != 1 && !hash.contains(n)){
            hash.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while(n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n /10;
        }
        return res;
    }


}
