import java.util.ArrayDeque;

public class Day14 {

    /**
     * 856. 括号的分数
     * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
     * () 得 1 分。
     * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
     * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
     * @param s
     * @return
     */
    public int scoreOfParentheses(String s) {
        /**
         * 初始化将答案 0 放入栈中，从前往后处理整个 s，当遇到 ( 则存入一个占位数值 0，遇到 ) 取出栈顶元素 cur，根据栈顶元素数值值分情况讨论：
         * 栈顶元素 cur = 0cur=0，即当前的 ) 的前一元素即是 ( ，根据 () 得一分的规则可知，我们本次操作得到的分值为 11；
         * 栈顶元素 cur \neq 0cur
         *  =0，即当前 ) 与其匹配的 ( 中间相隔了其他字符，根据 (A) 的得分规则，此时可知得分为 cur \times 2cur×2；
         * 将两者结合可统一为 max(cur * 2, 1)max(cur×2,1)。
         */
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if(c == '('){
                deque.addLast(0);
            }else {
                Integer curr = deque.pollLast();
                deque.addLast(deque.pollLast() + Math.max(curr * 2, 1));
            }
        }

        return deque.peekLast();

    }
    public static void main(String[] args) {

    }
}
