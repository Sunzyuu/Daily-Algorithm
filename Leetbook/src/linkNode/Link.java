package linkNode;

public class Link {


    /**
     * 请编写一个函数，用于删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点head ，只能直接访问 要被删除的节点 。
     * 题目数据保证需要删除的节点 不是末尾节点 。
     *
     * @param node
     */
    public static void deleteNode(ListNode node) {
        if (node.next == null) {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 删除链表的倒数第N个节点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = new ListNode(0);
        ListNode fast = new ListNode(0);
        slow.next = head;
        fast.next = head;
        ListNode res = slow;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if(slow.next.next == null){
            slow.next = null;
        }else{
            slow.next = slow.next.next;
        }

        return res.next;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * 快慢指针，如果存在环形链表，快指针会在其中追到满指针，否则快慢指针都会执向null
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        removeNthFromEnd(listNode, 1);
    }
}
