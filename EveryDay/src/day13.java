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

    public static void main(String[] args) {

    }
}
