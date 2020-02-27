import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

        // check whether function solution works
        int[] flux_values = solution(3, new int[]{79, 33, 52, 1});


        // printing correct
        for (int i = 0; i < flux_values.length; i++) {
            System.out.print(flux_values[i]);
            if (i != flux_values.length - 1)
                System.out.print(" ");
        }

    }


    /*function which takes in an integer(height) and a set of
     * */
    public static int[] solution(int h, int[] q) {
        int list_size = q.length;

        int[] flux_converter_label = new int[list_size];


        Node node = new Node(0);

        BinaryTree flux_chain_design_tree = new BinaryTree(node, h);


        flux_chain_design_tree.createTree();

        flux_chain_design_tree.post_order_labeling(flux_chain_design_tree.root);
        //flux_chain_design_tree.post_order_indexing(flux_chain_design_tree.root);


        for (int i = 0; i < q.length; i++) {
            flux_converter_label[i] = flux_chain_design_tree.find_parent(q[i]);
        }
        // returning flux converter's label
        return flux_converter_label;
    }


    // Binary Tree class which holds information about nodes in the tree;
    public static class BinaryTree {
        // address of the root node
        Node root;
        // height of the current tree which is given
        int height;

        // constructor to initialize the root node and to set value of the height for later use
        BinaryTree(Node root, int height) {
            this.root = root;
            this.height = height;
        }

        // method to create a tree with random values with a given height
        public void createTree() {
            // curr_node will add left and right nodes until desired height is reached
            Node curr_node = root;
            Node curr_left, curr_right;
            // queue to keep track of the nodes to insert children
            Queue<Node> flux_queue = new LinkedList<>();
            // adding the root node to the queue to add children
            flux_queue.add(curr_node);

            // adding (2^(h) - 1) nodes, where h is the height of the complete binary tree
            for (int i = 1, j = 1; i < (Math.pow(2, height) - 1); i += 2, j++) {
                // dequeueing first element element to add children
                curr_node = flux_queue.remove();

                // creating left child and adding it to the queue
                curr_node.left = new Node(j);
                flux_queue.add(curr_node.left);

                // creating right child and adding it to the queue
                curr_node.right = new Node(-j);
                flux_queue.add(curr_node.right);

                // linking each node to its parent, to retrieve value later
                curr_left = curr_node.left;
                curr_right = curr_node.right;
                curr_left.parent = curr_node;
                curr_right.parent = curr_node;

            }

        }


        // global variable to keep track of the label number
        int flux_val = 1;

        /*
        Function to label flux chains in the desired order (Left_Child --> Right_Child --> Parent)
        */
        public void post_order_labeling(Node node) {

            // base case
            if (node == null)
                return;

            // do down the left subtree recursively
            post_order_labeling(node.left);

            // after we visit all left subtree, recurse through the right branch
            post_order_labeling(node.right);

            // once we finish traversing both right and left, add values in
            node.flux_converter_ID = flux_val;
            // after labeling, increment global variable
            flux_val++;
        }

        // find the flux_value of parent, given flux value of its child
        public int find_parent(int check_val) {
            Node curr_node = root;
            Queue<Node> queue = new LinkedList<>();

            queue.add(curr_node);
            while (queue.peek() != null) {
                curr_node = queue.remove();

                if (check_val == curr_node.flux_converter_ID) {
                    if (curr_node != root) {
                        return (curr_node.parent.flux_converter_ID);
                    } else {
                        return -1;
                    }
                }
                queue.add(curr_node.left);
                queue.add(curr_node.right);
            }
            return -1;
        }
    }

    public static class Node {

        int flux_converter_ID;
        int parent_val;


        Node parent;
        Node left;
        Node right;

        Node(int flux_converter_ID) {
            this.flux_converter_ID = flux_converter_ID;
        }
    }
}


