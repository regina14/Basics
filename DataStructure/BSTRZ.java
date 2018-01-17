package DataStructure;

class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    public TreeNode(int val){
        this.val = val;
    }
}

public class BSTRZ {
    TreeNode root = null;

    public boolean isEmpty(){
        return root == null;
    }

    //增
    public void insert(int val, TreeNode cur){
        if (cur == null){
            cur = new TreeNode(val);
        }

        if (cur.val < val){
            insert(val, cur.right);
        } else if(cur .val > val) {
            insert(val, cur.left);
        } else {
            return; //no duplicates allowed
        }
    }

    //删
    public void delete(TreeNode value) {
        if (value == null || isEmpty()) {
            return;
        }

        root = delete(root, value);
    }

    public TreeNode delete(TreeNode root, TreeNode node) {
        if (node == null) return node;

        if (node.val < root.val){
            root.left = delete(root.left, node);
        } else if (node.val > root.val){
            root.right = delete(root.right, node);
        } else{
            if (root.left != null && root.right != null){
                TreeNode temp = findMin(root.right);
                root = temp;
                root.right = delete(root.right, temp);
            } else{
                if (root.left != null){
                    root = root.left;
                }

                if (root.right != null){
                    root = root.right;
                }
            }
        }
        return root;
    }

    //查
    public TreeNode search(int val) {
        TreeNode cur = root;

        while (cur != null){
            if (cur.val == val){
                return cur;
            } else if (cur.val > val){
                cur = cur.left;
            } else{
                cur = cur.right;
            }
        }
        return null;
    }

    //util
    public TreeNode findMin(){
        if (isEmpty()) return null;
        return findMin(root);
    }

    private TreeNode findMin(TreeNode node) {
        TreeNode temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

}
