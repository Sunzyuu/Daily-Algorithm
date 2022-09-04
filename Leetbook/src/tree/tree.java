package tree;


import com.sun.org.apache.bcel.internal.generic.FSUB;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class tree {


    /**
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }

        if((root.left != null && root.left.val > root.val) ||(root.right != null && root.right.val < root.val)){
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isSymmetric(TreeNode root) {

        if(root == null){
            return true;
        }

        if(root.right == null && root.left==null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode u = root.left;
        TreeNode v = root.right;

        queue.offer(u);
        queue.offer(v);
        while (!queue.isEmpty()){
            u = queue.poll();
            v = queue.poll();

            if(u == null && v == null){
                continue;
            }
            if((u == null || v == null) || u.val != v.val){
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
     * 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null){
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();

        bfs(root, 0, list);  // 中间的参数是level
        return list;

    }

    private static void bfs(TreeNode root, int level, List<List<Integer>> list) {
        if(root == null) return;

        if(level >= list.size()){
            ArrayList<Integer> subList = new ArrayList<>();
            subList.add(root.val);
            list.add(subList);
        }else {
            list.get(level).add(root.val);
        }
        bfs(root.left, level+1, list);
        bfs(root.right, level+1, list);
    }


    /**
     * 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {

        if(nums == null || nums.length == 0){
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int start, int left) {
        if(start > left){
            return null;
        }
        int mid = (start + left) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid-1);
        root.right = sortedArrayToBST(nums, mid+1, left);
        return root;
    }






    public static void main(String[] args) {

    }
}
