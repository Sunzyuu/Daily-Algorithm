import java.util.ArrayList;

public class day13 {


    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < nums.size(); i++) {
            res = Math.min(res, Math.abs(nums.get(i) - nums.get(i-1)));
        }
        return res;
    }

    private void inorder(TreeNode root, ArrayList<Integer> nums) {
        if(root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }


    /**
     * 面试题 01.09. 字符串轮转
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        if(s1.equals(s2)){
            return true;
        }
        char[] chars = s1.toCharArray();
        for (int i = 1; i < s1.length(); i++) {
            StringBuilder tempSb = new StringBuilder();
            tempSb.append(chars, i, s1.length() - i);
            tempSb.append(chars, 0, i);
            String tempStr = tempSb.toString();

            if(tempStr.equals(s2)){
                return true;
            }
        }
        return false;
    }

    public boolean isFlipedString1(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        String ss = s1 + s1;
        // 如果两个字符串为旋转字符串，那么s1+s1后的字符串的一个子串一定包含s2
        if(ss.indexOf(s2) != -1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
