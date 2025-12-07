package CodingNeetcodeLeetcode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leftViewBfs {
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // nodes in current level

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // first node of each level = left view
                if (i == 0) {
                    result.add(node.val);
                }

                // add left first, then right (important)
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

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
        root.right.right.right = new TreeNode(7);

        System.out.println(leftView(root)); // Output: [1, 2, 4]
    }
}
