package CodeThink.LinkNodeThink;

import java.util.*;

public class StackAndQueueThink {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);

//        Arrays.asList()
        ListNode listNode2 = new ListNode(3);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(3);
//        Merge(listNode, listNode2);

        Stack<Character> stack = new Stack<>();

        solve("100+100");
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
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == '{') {
                stack.push('}');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
                //
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
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
            if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
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
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                stack.push(-stack.pop() + stack.pop());
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
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
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);

            if (i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }

        }
        return res;

    }

    public static int[] test(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int idx = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }
        }
        return res;
    }


    /**
     * 前K个高频的元素
     * 基于大顶堆实现
     *
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
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((x, y) -> y[1] - x[1]);

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

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
//        PriorityQueue<Integer> queue  = new PriorityQueue<>((x, y) -> x - y);

        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);

        ArrayList<Integer> res = new ArrayList<>();

        for (int x : input) {
            queue.add(x);
        }

        for (int i = 0; i < k; i++) {
            res.add(queue.poll());
        }
        return res;
    }


    public int[] twoSum(int[] numbers, int target) {
        // write code here
//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.add();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{i, map.get(target - numbers[i])};
            }
            map.put(numbers[i], i);
        }
        return new int[]{};
    }


    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode res = null;
        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode temp = null;
        if (list1.val <= list2.val) {
            res = list1;
//            list1 = list1.next;
        } else {
            res = list2;
//            list2 = list2.next;
        }

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                while (list1.next != null && (list1.val == list1.next.val || list1.next.val <= list2.val )) {
                    list1 = list1.next;
                }
                temp = list1.next;
                list1.next = list2;
                list1 = temp;
            } else {
                while (list2.next != null && (list2.val == list2.next.val || list2.next.val <= list1.val )) {
                    list2 = list2.next;
                }
                temp = list2.next;
                list2.next = list1;
                list2 = temp;
            }
        }

        while (list1 != null) {
            list2 = list1;
            list1 = list1.next;
            list2 = list2.next;
        }


        while (list2 != null) {
            list1 = list2;
            list1 = list1.next;
            list2 = list2.next;
        }
        return res;
    }

    public static int solve (String s) {
        // write code here
        // 存放各种符号
        Stack<Character> stack1 = new Stack<>();

        // 存放数字
        Stack<Integer> stack2 = new Stack<>();


        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) <= '9' && s.charAt(i) >= '0'){
                int sum = s.charAt(i) - '0';
                while(i + 1 < s.length() && s.charAt(i + 1)  <= '9' && s.charAt(i + 1) >= '0'){
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i ++;
                }
                stack2.push( sum);
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '('){
                stack1.push(s.charAt(i));
            } else {
                int num2 = stack2.pop();
                int num1 = stack2.pop();
                char symbol = stack1.pop();
                if(symbol == '-'){
                    int res = num1 - num2;
                    stack2.push(res);
                } else if(symbol == '+'){
                    int res = num1 + num2;
                    stack2.push(res);
                } else if(symbol == '*'){
                    int res = num1 * num2;
                    stack2.push(res);
                }
                // 弹出(
                if(!stack1.isEmpty() && stack1.peek() == '('){
                    stack1.pop();
                }
            }
        }
        while(!stack1.isEmpty()){
            int num2 = stack2.pop();
            int num1 = stack2.pop();
            char symbol = stack1.pop();
            if(symbol == '-'){
                int res = num1 - num2;
                stack2.push(res);
            } else if(symbol == '+'){
                int res = num1 + num2;
                stack2.push(res);
            } else if(symbol == '*'){
                int res = num1 * num2;
                stack2.push(res);
            }
            // 弹出(
            if(!stack1.isEmpty() && stack1.peek() == '('){
                stack1.pop();
            }
        }

        return stack2.peek();
    }


        public ArrayList<Integer> function(String s, int index){
            Stack<Integer> stack = new Stack<Integer>();
            int num = 0;
            char op = '+';
            int i;
            for(i = index; i < s.length(); i++){
                //数字转换成int数字
                //判断是否为数字
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    num = num * 10 + s.charAt(i) - '0';
                    if(i != s.length() - 1)
                        continue;
                }
                //碰到'('时，把整个括号内的当成一个数字处理
                if(s.charAt(i) == '('){
                    //递归处理括号
                    ArrayList<Integer> res = function(s, i + 1);
                    num = res.get(0);
                    i = res.get(1);
                    if(i != s.length() - 1)
                        continue;
                }
                switch(op){
                    //加减号先入栈
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        //相反数
                        stack.push(-num);
                        break;
                    //优先计算乘号
                    case '*':
                        int temp = stack.pop();
                        stack.push(temp * num);
                        break;
                }
                num = 0;
                //右括号结束递归
                if(s.charAt(i) == ')')
                    break;
                else
                    op = s.charAt(i);
            }
            int sum = 0;
            //栈中元素相加
            while(!stack.isEmpty())
                sum += stack.pop();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(sum);
            temp.add(i);
            return temp;
        }
        public int solve1 (String s) {
            ArrayList<Integer> res = function(s, 0);
            return res.get(0);
        }




}
