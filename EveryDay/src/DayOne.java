import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DayOne {
    public static void main(String[] args) {

    }

    public static int[] twoSum() {
        int target = 9;
        int[] nums = new int[]{2, 7, 11, 15};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        if (nums.length < 3) {
            return arr;
        }

        Arrays.sort(nums);
        if (nums[0] > 0) {  // 排完序后若第一个数大于零则说明三数之和不可能小于0
            return arr;
        }
        // 注意数组的边界，由于左右指针的存在，边界是数组长度-2
        for (int i = 0; i < nums.length - 2; i++) {
            int traget = 0 - nums[i];

            // 跳过数组中重复的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 定义左右指针， 寻找 nums[left]+nums[right] == target
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                // 判断nums[left]+nums[right]与target的大小，根据大小移动左右指针
                if (nums[left] + nums[right] > traget) {
                    right--;
                } else if (nums[left] + nums[right] < traget) {
                    left++;
                } else {
                    // 注意添加 left<right条件，否则会出现数组下标越界错误
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    arr.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                }
            }
        }
        return arr;
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head=null, tail=null;    // 用于存放结果的链表的头和尾
        int value1 = 0, value2 = 0;
        int more = 0;  //value1与value2相加产生的进位
        // 遍历两个链表
        while(l1 != null || l2 !=null ){
            // 分别判断l1和l2是否为null
//            value1 = l1!=null ? l1.val : 0;
//            value2 = l2!=null ? l2.val : 0;
            if(l1 != null){
                value1 = l1.val;
                l1 = l1.next;
            }else {
                value1 = 0;
            }

            if(l2 != null){
                value2 = l2.val;
                l2 = l2.next;
            }else {
                value2 = 0;
            }

            int sum = value1 + value2 + more;

            if(head == null){
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);;
                tail = tail.next;
            }
            more = sum / 10; // 算出进位值
            if(more > 0){
                tail.next = new ListNode(more);
            }
        }

        return head;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode head = null, tail =null;
        int v1 = 0, v2=0;
        int more = 0;
        while(l1 != null || l2 != null){
            if(l1 != null){
                v1 = l1.val;
                l1 = l1.next;
            }else {
                v1 = 0;
            }

            if(l2 != null){
                v2 = l2.val;
                l2 = l2.next;
            }else {
                v2 = 0;
            }
            // 计算当前位上的数
            int sum = v1 + v2 + more;
            if(head == null){
                head = tail = new ListNode(sum%10);
            }else {
                tail = new ListNode(sum%10);
                tail = tail.next;
            }
            more = sum / 10; // 向前的进位

            if(more > 0){
                tail.next = new ListNode(more);
            }

        }

        return head;
    }


    /**
     * 在遍历链表时，将当前节点的next指针改为指向前一个节点(prve表示的就是next指针的前一节点)。 curr.next = prev
     * 由于节点没有引用其前一个节点，因此必须事先存储其前一个节点(prev)。
     * 在更改引用之前，还需要存储后一个节点
     * 最后返回新的头引用。
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        // 首先申请两个两个指针，第一个指针叫 pre，最初是指向 null 的。
        // 第二个指针 cur 指向 head，然后不断遍历 cur。
        // 然后遍历curr指针，每次遍历后将curr.next指向prev， 然后 prev 和 cur 前进一位。
        // 遍历完成后，prev指向的就是原来链表的末尾
        // https://pic.leetcode-cn.com/7d8712af4fbb870537607b1dd95d66c248eb178db4319919c32d9304ee85b602-%E8%BF%AD%E4%BB%A3.gif
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public static ListNode reverseList2(ListNode head) {
        ListNode ans = null;

        while(head != null){
            ans = new ListNode(head.val, ans);
            head = head.next;
        }
        return ans;
    }

    /**
     * 递归调用
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {

        // 递归终止条件
        if(head == null || head.next == null){
            return head;
        }
        ListNode curr = reverseList3(head.next);  // 当递归到链表的末尾时，返回末尾的节点 此后遍历该值也不会改变
        head.next.next = head;
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next = null;  // 断开链表
        return curr;
    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 因为每次只能爬1 或2 个台阶，如果第一次爬1个台阶，则剩下n-1个台阶，相当于求n-1个台阶的爬法数，
     * 如果第一次爬2个台阶，则剩下n-2个台阶，相当于求n-2个台阶的爬法数
     * 那么n个台阶的爬法就是n-1个台阶与n-2个台阶的爬法总和
     * 相当于f(n) = f(n-1) + f(n-2) 即斐波那契数列
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        // 递归
        // 如果单纯的使用递归会 时间超限 因此需要对算法进行优化
        if(n == 1) return 1;
        if(n == 2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }


    private HashMap<Integer, Integer> map = new HashMap<>();
    public int climbStairs1(int n) {
        // 递归
        // 如果单纯的使用递归会 时间超限 因此需要对算法进行优化
        // 使用hashMap 将已经计算过的f(n)以 n,f(n)的形式保存到map中
        // 计算之前先判断是否已经计算过该数，如果已经计算过则直接取出即可，否则正常递归计算，并将计算的结果保存到map
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(this.map.get(n) != null){
            return map.get(n);
        }else {
            map.put(n, climbStairs1(n-1) + climbStairs1(n-2));
            return climbStairs1(n-1) + climbStairs1(n-2);
        }
    }

    public static int climbStairs2(int n) {
        // 循环计算
        if(n == 1) return 1;
        if(n == 2) return 2;
        int pre1 = 1;
        int pre2 = 2;

        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = pre1 + pre2;
            pre1 = pre2;
            pre2 = sum;
        }

        return sum;

    }







}
