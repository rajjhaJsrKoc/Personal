package CodingNeetcodeLeetcode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InvertATree {
    public static void main(String[] args) {

        // Build tree:
        //         1
        //       /   \
        //      2     3
        //     / \     \
        //    4   5     6

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(invertATree(root));
    }

    private static TreeNode invertATree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // push existing children into queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
