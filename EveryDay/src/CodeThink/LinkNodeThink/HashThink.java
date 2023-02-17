package CodeThink.LinkNodeThink;

import java.util.*;

public class HashThink {
    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        int[] recored = new int[26];

        for (int i = 0; i < s.length(); i++) {
            recored[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i--) {
            recored[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < recored.length; i++) {
            if (recored[i] != 0) {
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
            if (hashSet.contains(nums2[i])) {
                resSet.add(nums2[i]);
            }
        }


        return resSet.stream().mapToInt(x -> x).toArray();
    }

    public boolean isHappy(int n) {
        HashSet<Integer> hash = new HashSet<>();
        while (n != 1 && !hash.contains(n)) {
            hash.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }


    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /**
         * 首先定义 一个unordered_map，key放a和b两数之和，value 放a和b两数之和出现的次数。
         * 遍历大A和大B数组，统计两个数组元素之和，和出现的次数，放到map中。
         * 定义int变量count，用来统计 a+b+c+d = 0 出现的次数。
         * 在遍历大C和大D数组，找到如果 0-(c+d) 在map中出现过的话，就用count把map中key对应的value也就是出现次数统计出来。
         * 最后返回统计值 count 就可以了
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
//                map.put(sum, map.getOrDefault(map.get(sum), 0) + 1);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int count = 0;

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int subNum = -nums3[i] - nums4[j];
                if (map.containsKey(subNum)) {
                    count += map.get(subNum);
                }
            }
        }
        return count;
    }

    public static boolean canConstruct(String ransom, String magazine) {
        if (ransom.length() > magazine.length()) {
            return false;
        }

        int[] recoreds = new int[26];
        for (int i = 0; i < ransom.length(); i++) {
            recoreds[ransom.charAt(i) - 'a']++;
        }

        for (int i = 0; i < magazine.length(); i++) {
            recoreds[magazine.charAt(i) - 'a']--;
        }

        for (int recored : recoreds) {
            if (recored < 0) {
                return false;
            }
        }

        return true;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        // 排序加双指针解法
        Arrays.sort(nums); // 将数组排序后在遍历 方便剪枝
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {                      // 因为数组有序，所以当nums[i] >0 后 后面不可能找到三个元素的和为0
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) { //去重逻辑
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重逻辑
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    right--;
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int twoSum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {    // 对nums[i]去重
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) { // 对nums[j]去重
                    continue;
                }
                twoSum = nums[i] + nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = twoSum + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right < left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }

}
