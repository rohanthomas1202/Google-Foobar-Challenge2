import java.util.*;


public class Solution {
    public static void main(String[] args) {

        List<Integer> index_list = new LinkedList<>();

        index_list.add(7);
        index_list.add(3);
        index_list.add(5);
        index_list.add(1);


        /*


        3, {7, 3, 5, 1}


        {19, 14, 28})
        Output:
        21,15,29

                */
        List<Integer> flux_values = solution(3, index_list);


        //System.out.println(val.length);

        for (int i = 0; i < flux_values.size(); i++) {
            System.out.print(flux_values.get(i));
            if (i != flux_values.size() - 1)
                System.out.print(" ");
        }

    }




    public static List<Integer> solution(int h, List<Integer> q) {
        int list_size = q.size();

        List<Integer> flux_converter_label= new LinkedList<>();




        Node node = new Node(0);

        BinaryTree flux_chain_design_tree = new BinaryTree(node, h);


        flux_chain_design_tree.createTree();

        flux_chain_design_tree.post_order_labeling(flux_chain_design_tree.root);
        flux_chain_design_tree.post_order_indexing(flux_chain_design_tree.root);


        for (Integer integer : q) {
            flux_converter_label.add(flux_chain_design_tree.val_from_index(integer));
        }



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

        public void createTree() {                  // method to create a tree with random values with a given height

            Node curr_node = root;                  // curr_node will add left and right nodes until desired height is reached
            Queue<Node> flux_queue = new LinkedList<>();
            flux_queue.add(curr_node);


            for (int i = 1, j = 1; i < (Math.pow(2, height) - 1); i += 2, j++) {
                curr_node = flux_queue.remove();
                curr_node.left = new Node(j);
                flux_queue.add(curr_node.left);
                curr_node.right = new Node(-j);
                flux_queue.add(curr_node.right);

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


        // global variable to keep track of the index of each node
        int index = 0;

        /*
        Function to index flux chains in the desired order (Parent --> Left_Child --> Right_Child)
         */
       /* public void pre_order_indexing(Node node) {
            if (node == null)
                return;

            // update index values
            node.index = index;
            index++;

            // go down left subtree recursively
            pre_order_indexing(node.left);


            // go down right subtree recursively
            pre_order_indexing(node.right);
        }*/

       public void post_order_indexing(Node node) {
            if (node == null)
                return;



            // go down left subtree recursively
            post_order_indexing(node.left);


            // go down right subtree recursively
            post_order_indexing(node.right);
           // update index values
           node.index = index;
           index++;

        }


       /*public void in_order_indexing(Node node) {
            if (node == null)
                return;



            // go down left subtree recursively
           in_order_indexing(node.left);

           // update index values
           node.index = index;
           index++;

            // go down right subtree recursively
           in_order_indexing(node.right);


        }*/





        // find the flux_value given the index of node
        public int val_from_index(int index) {
            Node curr_node = root;
            Queue<Node> val = new LinkedList<>();

            val.add(curr_node);
            while (val.peek() != null) {
                curr_node = val.remove();

                if (curr_node.index == index) {
                    return curr_node.flux_converter_ID;
                }
                val.add(curr_node.left);
                val.add(curr_node.right);
            }
            return -1;
        }




    }

    public static class Node {

        int flux_converter_ID;
        int index;

        Node left;
        Node right;

        Node(int flux_converter_ID) {
            this.flux_converter_ID = flux_converter_ID;
        }
    }
}


