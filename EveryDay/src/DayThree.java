import java.util.*;

public class DayThree {
    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(0);
//        head.next.next = new ListNode(0);
//        DayThree.isPalindrome(head);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("c").append('d').append('d');
//        System.out.println(stringBuilder.toString());
//        stringBuilder.insert(0,'a');
//        System.out.println(stringBuilder.toString());
//        String str = "abc";
//        str = str + "acc";
//        System.out.println(str);
//
//        char c = stringBuilder.charAt(0);
//        stringBuilder.deleteCharAt(0);
//        System.out.println(stringBuilder.toString());
//        System.out.println(c);

        String str = "3[a]2[bc]";

//        System.out.println(DayThree.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);


        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

//        System.out.println(isSymmetric(root));

        System.out.println(maxDepth(root));
        System.out.println(maxDepth2(root));

    }

    public static ListNode reverseList(ListNode head) {

        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // 使用快慢指针p1和p2（步长为1和步长为2的指针），当步长为2的指针遍历到指针末尾时，步长为1的指针刚好走完全链表的一半
        // 再使用反转链表函数将p1后面的链表反转，之后遍历两个链表比较即可
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // 判断链表的长度时奇数还是偶数
        // 1->2->2->1
        // 1->2->3->3->2->1
        // 1->2->3->2->1

        if (p2.next != null) { //链表节点个数为偶数时
            p1 = p1.next; //将p1指针指向链表右半边的开始
        }
        p1 = reverseList(p1);
        while (p1 != null) {
            if (head.val != p1.val) {
                return false;
            }
            head = head.next;
            p1 = p1.next;
        }
        return true;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {

        if (head == null) return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        if (p2.next != null) { //链表节点个数为偶数时
            p1 = p1.next; //将p1指针指向链表右半边的开始
            return p1;
        }
        return p1;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        /**
         * 反转链表解题 内存消耗比较大
         * 执行用时：
         * 0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：39.7 MB, 在所有 Java 提交中击败了21.36%的用户
         */
        // [1,2,3,4,5] k=2
/*        if (head == null) return null;
        head = reverseList(head);
        // [5,4,3,2,1] k=2
        int index = 1;
        ListNode pre = head;
        while(index < k){
            head = head.next;
            index++;
        }

        head.next = null;
        pre = reverseList(pre);
        return pre;*/

        /**
         * 双指针解题
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：39.2 MB, 在所有 Java 提交中击败了80.02%的用户
         */
        if (head == null) return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (k > 0) {
            p1 = p1.next;
            k--;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }


    /**
     * 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * "3[a2[c]]"
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        /**
         * 执行用时：1 ms, 在所有 Java 提交中击败了76.41%的用户
         * 内存消耗：39.5 MB, 在所有 Java 提交中击败了55.94%的用户
         */
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        Stack<Character> letterStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 将所有的非']'字符压栈
            if (c == '[' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
                letterStack.push(c);
            } else if (c == ']') {
                StringBuilder stringBuilder = new StringBuilder();
                // 弹出所有的字母
                // !letterStack.empty() 条件需要放在最前面 否则会出现空栈帧错误
                while (!letterStack.empty() && letterStack.peek() <= 'z' && letterStack.peek() >= 'a') {
                    stringBuilder.insert(0, letterStack.pop());
                }
                letterStack.pop();  // 从stack中弹出 '['
                int sum = 0;
                int times = 1;
                // 将数字字符转化数字
                while (!letterStack.empty() && letterStack.peek() <= '9' && letterStack.peek() >= '0') {
                    char num = letterStack.pop();
                    sum += Character.getNumericValue(num) * times;
                    times *= 10;
                }
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < sum; j++) {
                    temp.append(stringBuilder.toString());
                }
                if (letterStack.empty()) {
                    res.append(temp.toString());
                } else {
                    // 这里不写入栈也可以吧  偷懒了
                    // 如果letterStack不为空，说明此时处里的字符串在另外一对[]中
                    // 如 aa2[a3[b]] 的b位置
                    for (int j = 0; j < temp.length(); j++) {
                        letterStack.push(temp.charAt(j));
                    }
                }
            }
        }
        // 如果栈中还有字母 直接取出即可
        StringBuilder stringBuilder = new StringBuilder();
        while (!letterStack.empty()) {
            stringBuilder.insert(0, letterStack.pop());
        }
        String string = stringBuilder.toString();
        res.append(string);
        return res.toString();
    }


    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
//        ArrayList<Integer> res = new ArrayList<>();
//        inorder(root, res);
//        return res;
        /**
         *
         */
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }

        return res;

    }


    public List<Integer> preorderTraversal(TreeNode root) {
//        ArrayList<Integer> res = new ArrayList<>();
//        inorder(root, res);
//        return res;


        /**
         *
         */
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }

        return res;

    }

    public List<Integer> postorderTraversal(TreeNode root) {
//        ArrayList<Integer> res = new ArrayList<>();
//        inorder(root, res);
//        return res;

        TreeNode preNode = null;
        ArrayList<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == preNode) {
                res.add(root.val);
                preNode = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return res;

    }


    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }


    /**
     * 101. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode u = root.left;
        TreeNode v = root.right;
        if (root == null || (u == null && v == null)) {
            return true;
        }

        queue.offer(u);
        queue.offer(v);
        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            queue.offer(u.left);
            queue.offer(v.right);
            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    public static int maxDepth2(TreeNode root) {
        /**
         * 循环迭代的方法不如用递归的方法
         * 执行用时：1 ms, 在所有 Java 提交中击败了20.74%的用户
         * 内存消耗：41.4 MB, 在所有 Java 提交中击败了19.34%的用户
         */
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int deepth = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 首先将本层的节点全部加入queue中，size记录每一层的节点数量
            // 当当前层级的节点全部遍历完成则depth++
            int size = queue.size();
            while (size > 0) {
                root = queue.poll();
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
                size--;
            }
            deepth++;

        }
        return deepth;
    }


    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        /**
         * 使用中序遍历加树的最大深度求解：
         * 执行用时:1 ms ,在所有Java提交中击败了44.94%的用户
         * 内存消耗:40.7MB ，在所有Java提交中击败了95.37%的用户
         */
        if (root == null)
            return true;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (Math.abs(maxDepth(root.right) - maxDepth(root.left)) > 1) {
                return false;
            }
            root = root.right;
        }
        return true;
    }

    public static boolean isBalanced2(TreeNode root) {
        /**
         * 执行用时:0 ms ,在所有Java提交中击败了100.00%的用户
         * 内存消耗:41.1 MB，在所有Java提交中击败了52.78%的用户
         */
        return height(root) >= 0;
    }

    private static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeith = height(root.left);
        int rightHeith = height(root.right);

        if (leftHeith == -1 || rightHeith == -1 || (Math.abs(leftHeith - rightHeith) > 1)) {
            return -1;
        } else {
            return Math.max(leftHeith, rightHeith) + 1;
        }
    }


    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 只有当树为完全二叉树时可以使用下面解法
//        if(root == null){
//            return root;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        TreeNode left = root.left;
//        TreeNode right = root.right;
//        if(left == null && right == null){
//            return root;
//        }
//        queue.offer(left);
//        queue.offer(right);
//        while(!queue.isEmpty()){
//            left = queue.poll();
//            right = queue.poll();
//            if(right == null && left == null){
//                continue;
//            }
//            int temp = left.val;
//            left.val = right.val;
//            right.val = temp;
//            left.val = Integer.parseInt(null);
//
//            queue.offer(left.left);
//            queue.offer(right.right);
//            queue.offer(left.right);
//            queue.offer(right.left);
//        }

        /**
         * 递归解法 大问题转换成小问题
         * 执行用时:0 ms ,在所有Java提交中击败了100.00%的用户
         * 内存消耗:39.3 MB ，在所有Java提交中击败了14.08%的用户
         */
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }


}
