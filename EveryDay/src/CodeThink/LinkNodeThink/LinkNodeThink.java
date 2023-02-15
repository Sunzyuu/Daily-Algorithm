package CodeThink.LinkNodeThink;

public class LinkNodeThink {

    public static void main(String[] args) {

    }


    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0, head);
//        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dummyHead.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;  // 保存结果
        ListNode cur = head;  // 当前节点
        ListNode temp = null; // 临时保存

        while (cur != null) {
            // 保存cur.next 用于将cur向后移动
            temp = cur.next;
            // 将cur的next指向pre
            cur.next = pre;
            // 向后移动pre
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode cur = dummyNode;
        ListNode temp1 = null;
        ListNode temp2 = null;
        // head为1->2->3->4
        // cur为 0->1->2->3->4
        while (cur.next != null && cur.next.next != null) {
            temp1 = cur.next;           // 1
            temp2 = cur.next.next.next; // 3

            // 步骤一 先指向temp2
            cur.next = cur.next.next;
            //     0->2->3->4
            // 此时 1->2->3->4
            // 步骤二 调整2的指向
            cur.next.next = temp1;
            // 0->2->1->2

            // 步骤三 调整1的指向
            cur.next.next.next = temp2;
            // 0->2->1->3->4
            //进行下一轮的交换
            cur = cur.next.next; // cur = 1
        }
        return dummyNode.next;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 首先先让fastIndex 走n
        for (int i = 0; i < n; i++) {
            fastIndex = fastIndex.next;
        }
        // 此时fastIndex比slowIndex 多走了n步
        // 所以当fastIndex slowIndex 都走到尾部时 slowIndex就处于倒数n的位置
        while (fastIndex.next != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 删除倒数第n个节点
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA, pB = headB;
        while (pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        // 定义快慢指针 如果存在环 当两个指针进入环中移动时，快指针一定会在某时间点追击到慢指针
        while (fastNode != null && fastNode.next != null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if(slowNode == fastNode){ // 存在环结构

                ListNode index1 = fastNode;
                ListNode index2 = head;
                while(index2 != index1){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }

        return null;
    }


}
