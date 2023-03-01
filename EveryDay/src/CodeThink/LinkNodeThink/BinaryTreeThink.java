package CodeThink.LinkNodeThink;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class BinaryTreeThink {
    public static void main(String[] args) {

    }


    public TreeNode sortedArrayToBST(int[] nums) {

        return travel(nums, 0, nums.length - 1);
    }

    private TreeNode travel(int[] nums, int left, int right){
        if(left > right){
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = travel(nums, left, mid - 1);
        root.right = travel(nums, mid + 1, right);

        return root;
    }



    private int sum;

    /**
     * 反中序遍历 累加节点元素 作为新树的值
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return null;
        }

        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        TreeNode newRoot = root;
        TreeNode pre = root;

        while(root != null){
            pre = root;
            if(root.val > val){
                root = root.left;
            } else if(root.val < val){
                root = root.right;
            }
        }
        if(pre.val > val){
            pre.left = new TreeNode(val);
        } else if(pre.val < val){
            pre.right = new TreeNode(val);
        }

        return newRoot;
    }
//    HashMap<Integer, Integer> map;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        map = new HashMap<>();
//
//        for(int i = 0; i<inorder.length; i++){
//            map.put(inorder[i], i);
//        }
//
//        return findNode(inorder, 0, inorder.length, preorder, 0, preorder.length);
//    }
//
//    private TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] preorder, int preBegin, int preEnd) {
//        // 参数是否合法
//        if(inBegin >= inEnd || preBegin >=  preEnd){
//            return null;
//        }
//        // 后序遍历的最后一个值 就是根节点,从map中获取到该元素在前序遍历中的位置
//        int rootIndex = map.get(preorder[preBegin]);
//        TreeNode root = new TreeNode(inorder[rootIndex]);
//        int lenOfLeft = rootIndex - inBegin; // 左边剩余节点的数量
//        // 以下边界全部为左闭右开
//        root.left = findNode(inorder, inBegin, rootIndex, preorder, preBegin + 1, preBegin + lenOfLeft + 1);
//        root.right = findNode(inorder, rootIndex + 1, inEnd, preorder, preBegin + lenOfLeft + 1, preEnd);
//        return root;
//    }

    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;


    public int getMinimumDifference(TreeNode root) {
        if(root == null){
            return 0;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int res= Integer.MAX_VALUE;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) { // 中
                    res = Math.min(res, cur.val - pre.val);
                }

                pre = cur;
                cur = cur.right;
            }
        }
        return res;

    }

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while(cur!= null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                int rootVal = cur.val;
                if(pre == null || rootVal != pre.val){
                    count = 1;
                } else {
                    count ++;
                }

                if(count > maxCount){
                    resList.clear();
                    maxCount = count;
                    resList.add(rootVal);

                } else if(count == maxCount){
                    resList.add(rootVal);
                }

                pre = cur;
                cur = cur.right;
            }
        }


        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 遍历左子树
        findMode1(root.left);

        // 处理中间节点
        int rootVal = root.val;
        if(pre == null || rootVal != pre.val){
            count=1;
        } else{
            count++;
        }

        if(count > maxCount){
            resList.clear();
            maxCount = count;
            resList.add(rootVal);
        } else if(count == maxCount){
            resList.add(rootVal);
        }
        pre = root;


        // 遍历右子树
        findMode1(root.right);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return null;
        }

        int maxNum = nums[leftIndex];
        int maxIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxNum);

        root.left = constructMaximumBinaryTree(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree(nums, maxIndex + 1, rightIndex);
        return root;

    }

    HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return findNode(inorder, 0, inorder.length, preorder, 0, preorder.length);
    }

    private TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] preorder, int preBegin, int preEnd) {
        // 参数是否合法
        if (inBegin >= inEnd || preBegin >= preEnd) {
            return null;
        }
        // 后序遍历的最后一个值 就是根节点,从map中获取到该元素在前序遍历中的位置
        int rootIndex = map.get(preorder[preBegin]);
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int lenOfLeft = rootIndex - inBegin; // 左边剩余节点的数量
        // 以下边界全部为左闭右开
        root.left = findNode(inorder, inBegin, rootIndex, preorder, preBegin + 1, preBegin + lenOfLeft + 1);
        root.right = findNode(inorder, rootIndex + 1, inEnd, preorder, preBegin + lenOfLeft + 1, preEnd);
        return root;
    }

    public int findBottomLeftValue(TreeNode root) {
        if(root == null){
            return -1;
        }
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int len = queue.size();

            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                if(i == 0){
                    res = node.val;
                }
                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    public List<Integer> preOrder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        travel(root, result);
        return result;
    }


    private void travel(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        // 前序遍历
        result.add(root.val);
        travel(root.left, result);
        // 中序遍历
//        result.add(root.val);
        // 后序遍历
//        travel(root.right, result);
    }

    public List<Integer> preOrderWithStacking(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        //先加入根节点 再加入右子节点最后加入左子节点
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            res.add(peek.val);
            stack.pop();
            if (peek.right != null) {
                stack.push(peek.right);
            }
            if (peek.left != null) {
                stack.push(peek.left);
            }
        }

        Integer[] resArr = new Integer[res.size()];

        res.toArray(resArr);
        int[] ints = res.stream().mapToInt(i -> i).toArray();
        return res;
    }

    public List<Integer> inOrderWithStacking(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {    // 此分支 一直沿着左子节点到树的底部 并将所有根节点放入stack
                stack.push(cur); //
                cur = cur.left;
            } else {            // 从stack中取出根节点并将数组存放到res
                cur = stack.pop();
                res.add(cur.val);   //
                cur = cur.right;    // 遍历右子节点
            }
        }
        return res;
    }

    public List<Integer> postOrderWithStacking(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);


            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }


    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                len--;
            }
            res.add(temp);

        }
        return res;
    }

    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public List<Integer> rightSideView(TreeNode root) {
        // write code here
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                if (len == 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                len--;
            }

        }
        return res;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();

        Stack<TreeNode> stack = new Stack<>();

        if (root == null) {
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            int len = stack.size();
            double sum = 0;
            while (len > 0) {
                TreeNode node = stack.pop();
                sum += node.val;
//                if(len == 1){
//                    double temp = sum / len;
//                    res.add(temp);
//                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
                len--;
            }
            res.add(sum / len);
        }
        return res;
    }

    /**
     * 使用层序遍历能够解决的题目如下
     * 102.二叉树的层序遍历
     * 107.二叉树的层次遍历II
     * 199.二叉树的右视图
     * 637.二叉树的层平均值
     * 429.N叉树的层序遍历
     * 515.在每个树行中找最大值
     * 116.填充每个节点的下一个右侧节点指针
     * 117.填充每个节点的下一个右侧节点指针II
     * 104.二叉树的最大深度
     * 111.二叉树的最小深度
     */

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swap(root);

        return root;
    }


    private void swap(TreeNode root) {

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    // 使用层序遍历 交换每个根节点的左右子节点
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                swap(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                len--;
            }
        }

        return root;
    }


    public boolean isBalanced(TreeNode root) {
        return getheight(root) != -1;
    }

    private int getheight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getheight(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getheight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;

    }


}
