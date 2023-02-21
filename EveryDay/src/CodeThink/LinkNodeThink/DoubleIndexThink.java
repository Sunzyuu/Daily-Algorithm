package CodeThink.LinkNodeThink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleIndexThink {


    public static void main(String[] args) {

    }
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;


        for (fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }
        }


        return slow;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 排序加双指针
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i - 1] == nums[i]){
                continue;
            }

            if(nums[i] > 0){
                return result;
            }
            // 双指针
            int left = i + 1;
            int right = nums.length -1;
            while(left < right){
                int sum = nums[left] + nums[right] + nums[i];
                if(sum == 0){

                    //去重
                    while (left < right && nums[left] == nums[left + 1]){
                        left --;
                    }
                    while(left < right && nums[right] == nums[right -1]){
                        right ++;
                    }
                    result.add(Arrays.asList(nums[i], nums[right], nums[left]));
                    left ++;
                    right --;
                } else if(sum > 0){
                    right --;
                }else {
                    left ++;
                }
            }
        }

        return result;
    }
}
