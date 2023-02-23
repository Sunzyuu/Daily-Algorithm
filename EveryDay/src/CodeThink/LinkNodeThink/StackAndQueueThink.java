package CodeThink.LinkNodeThink;

import java.util.*;

public class StackAndQueueThink {
    public static void main(String[] args) {

    }


    public boolean isValid(String s) {
        /**
         * 三种不合法的情况
         * 1.左括号多于括号
         * 2.左右括号不匹配
         * 3.右括号的数量多于左括号
         */
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                stack.push(')');
            } else if(s.charAt(i) == '{'){
                stack.push('}');
            } else if(s.charAt(i) == '['){
                stack.push(']');
                //
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)){
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }


    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if(stack.isEmpty() || stack.peek() != s.charAt(i)){
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
// 这种速度反而会慢
//        while (!stack.isEmpty()){
//            sb.append(stack.pop());
//        }

        return sb.reverse().toString();
    }

    public int evalRPN(String[] tokens) {
        int result = 0;

        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if("+".equals(token)){
                stack.push(stack.pop() + stack.pop());
            }else if("-".equals(token)){
                stack.push( -stack.pop() + stack.pop());
            }else if("*".equals(token)){
                stack.push(stack.pop() * stack.pop());
            }else if("/".equals(token)){
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            }else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(); // 存放的是数组下表而非元素
        int len = nums.length;

        int[] res = new int[len - k + 1];
        int idx = 0;
        for(int i = 0; i < len; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1 ){
                deque.poll();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }

            deque.offer(i);

            if(i >= k - 1){
                res[idx ++] = nums[deque.peek()];
            }

        }
        return res;

    }

    public static int[] test(int[] nums, int k){
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int idx = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1){
                deque.poll();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            if(i >= k - 1){
                res[idx ++] = nums[deque.peek()];
            }
        }
        return res;
    }


    /**
     * 前K个高频的元素
     * 基于大顶堆实现
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        // (pair1, pair2)->pair2[1]-pair1[1]是比较器，实现大顶堆 用pair2中的第二个元素 - pair1中的元素 按顺序排序
        // 若为(pair1, pair2)->pair1[1]-pair2[1] 则为小顶堆
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((x, y)->y[1]-x[1]);

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        for (Map.Entry<Integer, Integer> entry : entries) {
            // 将entries中的key value 以数组的形式保存到优先队列
            priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] res = new int[k];
        // 从优先队列中取出 前k个元素
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll()[0];
        }
        return res;
    }


}
