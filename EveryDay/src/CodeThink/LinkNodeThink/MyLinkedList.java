package CodeThink.LinkNodeThink;

public class MyLinkedList {
    // 链表的长度
    int size;
    // 链表的头节点
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        // 创建一个虚拟头节点 所以循环次数要加一
        ListNode currentLink = head;
        for (int i = 0; i <= index; i++) {
            currentLink = currentLink.next;
        }

        return currentLink.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        // 首先对index进行判断 是否是合法数据
        if(index > size) {
            return;
        }
        if(index < 0){
            index = 0;
        }

        size ++;
        // 找到待插入节点的前驱节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = pre.next;
        pre.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        size--;
        // 如果要删除头节点
        if (index == 0){
            head = head.next;
        }
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }
}
