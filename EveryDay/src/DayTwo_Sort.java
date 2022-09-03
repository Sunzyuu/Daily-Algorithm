import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DayTwo_Sort {
    public static void main(String[] args) {
//        int[] arr = new int[]{1, 3, 7, 11, 12, 14, 18};

//        int[] arr1 = new int[]{1, 2, 3, 0, 0, 0};
//        int[] arr2 = new int[]{2, 5, 6};
//        int n = 3, m = 3;
//        DayTwo.merge(arr1, m, arr2, n);
//        System.out.println(Arrays.toString(arr1));
//        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
//        DayTwo.findDisappearedNumbers2(nums);
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);
        list1.next.next.next.next = new ListNode(5);
        list1.next.next.next.next.next = new ListNode(6);
        removeElements(list1, 4);

//        ListNode list2 = new ListNode(1);
//        list2.next = new ListNode(3);
//        list2.next.next = new ListNode(4);

//        DayTwo.mergeTwoLists(list1, list2);

//        ListNode list = new ListNode(1);
//        list.next = new ListNode(1);
//        list.next.next = new ListNode(2);
//        ListNode listNode = DayTwo.deleteDuplicates(list);
//        while (listNode != null) {
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }


    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }


    public static int lenLongestFibSubseq(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }

        int res = 0;
        for (int i = 2; i < arr.length; i++) {

        }


        return 0;

    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针解决
        int p1 = 0;
        int p2 = 0;
        int temp = 0, index = 1;
        int[] arr = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (p1 >= m) {
                arr[i] = nums2[p2++];
            } else if (p2 >= n) {
                arr[i] = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                temp = nums1[p1];
                arr[i] = temp;
                p1++;
            } else if (nums1[p1] >= nums2[p2]) {
                temp = nums2[p2];
                arr[i] = temp;
                p2++;
            }
        }
        if (m + n >= 0) System.arraycopy(arr, 0, nums1, 0, m + n);
    }

    /**
     * 使用双指针解题
     * 一开始事 两个指针（i,j）同时指向数组的开头
     * 先使用指针j遍历整个数组，当遇到不为0的数时，将其挪到i的指向的位置
     * 即nums[i] == nums[j]
     * 然后将指针i向后移动一位 i++
     * 之后将i之后数全部换成0即可完成
     * 时间复杂度o(2n) = o(n)
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        if (nums == null || nums.length == 0) {
            return;
        }

        for (j = 0; j < nums.length; ++j) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }

        for (j = i; j < nums.length; ++j) {
            nums[j] = 0;
        }
    }

    /**
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * 4,3,2,7,8,2,3,1
     * [5,6]
     * <p>
     * 第一种方法对数组进行排序后以索引：value的形式存入map
     * 之后遍历map， 如果value与index不相等，且map中不存在index，则该index值为缺失的数
     * 时间超限 29/33个通过测试用例
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(i, nums[i]);
        }
        System.out.println(hashMap);

        for (int i = 0; i < hashMap.size(); i++) {
            if (i != (hashMap.get(i) - 1) && !hashMap.containsValue(i + 1)) {
                res.add(i + 1);
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 根据题目的要求可以看出所有的数都在1-n之间，所以用x = nums[i]-1%n 一定在1-n之间，即对应一个位置（nums[i] < n）， 可以将nums[x]+n进行标记
     * 如果某个数没有在数组中，则其%n所对应的x为空，即对应位置无法进行nums[x]+n操作，该位置的值一定小于n
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;

        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = null;
        ListNode head = null;

        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }
        int value1 = 0, value2 = 0;
        while (list1 != null || list2 != null) {
            if (list1 != null) {
                value1 = list1.val;
            } else {
                value1 = Integer.MAX_VALUE;
            }

            if (list2 != null) {
                value2 = list2.val;
            } else {
                value2 = Integer.MAX_VALUE;
            }

            if (res == null) {
                if (value1 < value2) {
                    res = new ListNode(value1);
                    head = res;
                    list1 = list1.next;
                } else {
                    res = new ListNode(value2);
                    head = res;
                    list2 = list2.next;
                }
            } else {
                if (value1 < value2) {
                    res.next = new ListNode(value1);
                    res = res.next;
                    list1 = list1.next;
                } else {
                    res.next = new ListNode(value2);
                    res = res.next;
                    list2 = list2.next;
                }
            }
        }
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        return head;
    }


    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }
        ListNode listNode = new ListNode(0);
        ListNode p = listNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return listNode.next;
    }

    /**
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;


        ListNode p2 = head.next;
        ListNode res = head;

        while (head != null && p2 != null) {
            if (head.val == p2.val) {
                p2 = p2.next;
                continue;
            }
            head.next = p2;
            head = p2;
            p2 = p2.next;
        }
        if (p2 == null) {
            head.next = null;
        }
        return res;
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.val == currentNode.next.val) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
        return currentNode;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        // 使用hash表的时间复杂度为O(n)  5 ms在所有 Java 提交中击败了5.57%的用户

        //if (head == null){
        //            return false;
        //        }
        //
        //        HashMap<ListNode, Integer> hashMap = new HashMap<>();
        //        while (head != null){
        //            if(hashMap.containsKey(head)){
        //                return true;
        //            }
        //            hashMap.put(head, head.val);
        //            head = head.next;
        //        }
        //        return false;

        // 快慢指针 时间复杂度
        if (head == null) {
            return false;
        }

        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    public static ListNode detectCycle(ListNode head) {

//        if (head == null) {
//            return null;
//        }
//
//        HashMap<ListNode, Integer> hashMap = new HashMap<>();
//        while (head != null) {
//            if (hashMap.containsKey(head)) {
//                return head;
//            }
//            hashMap.put(head, head.val);
//            head = head.next;
//        }
//        return null;


        if (head == null) {
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;

        boolean cycleIs = false;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            if (p1 == p2) {
                cycleIs = true;
                break;
            }
        }

        // 题解 https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
        if (cycleIs) {
            p1 = head;
            while (p1 == p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
        return null;
    }


    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
     * 如果两个链表不存在相交节点，返回 null
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //使用hash表 耗时8ms
        // 8 ms在所有 Java 提交中击败了6.25%的用户
//
//        ListNode p1 = headA;
//        ListNode p2 = headB;
//        HashMap<ListNode, Integer> hashMap = new HashMap<>();
//        while(p1 != null ){
//            hashMap.put(p1, p1.val);
//            p1 = p1.next;
//        }
//
//        while(p2 != null){
//            if(hashMap.containsKey(p2)){
//                return p2;
//            }
//            p2 = p2.next;
//        }

        ListNode p1 = headA;
        ListNode p2 = headB;
        // 两个指针最后走的路程一样，如果有相交点，则肯定在相交点位置相遇
        // 如果没有相交的点 p1走到headB的末尾指向null
        // p2走到headA的末尾也指向null
        // 此时会跳出循环
        // 时间复杂度为O(m+n)
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public static ListNode reverseList3(ListNode head) {

        // 递归终止条件
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return curr;
    }





}
