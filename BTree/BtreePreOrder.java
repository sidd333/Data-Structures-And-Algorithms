package BTree;

import java.util.LinkedList;
import java.util.Queue;

public class BtreePreOrder {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class Btree {
        static int index = -1;

        public Node buildTree(int nodes[]) {
            // Step through the recursive process in building the binary tree.
            /*
             * Step-by-Step Visualization of the Recursive Call Stack:
             * 
             * Array Input: {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}
             * 
             * 1. buildTree(0): 1
             * ↳ create Node(1)
             * ↳ call buildTree(1) for left child of Node(1)
             * 
             * 2. buildTree(1): 2
             * ↳ create Node(2)
             * ↳ call buildTree(2) for left child of Node(2)
             * 
             * 3. buildTree(2): 4
             * ↳ create Node(4)
             * ↳ call buildTree(3) for left child of Node(4)
             * 
             * 4. buildTree(3): -1
             * ↳ return null (left child of Node(4))
             * ↳ call buildTree(4) for right child of Node(4)
             * 
             * 5. buildTree(4): -1
             * ↳ return null (right child of Node(4))
             * ↳ Node(4) is now complete
             * 
             * 6. Back to Node(2), build right child → Node(5) (index = 5)
             * ↳ create Node(5)
             * ↳ call buildTree(6) for left child of Node(5)
             * 
             * 7. buildTree(6): -1
             * ↳ return null (left child of Node(5))
             * ↳ call buildTree(7) for right child of Node(5)
             * 
             * 8. buildTree(7): -1
             * ↳ return null (right child of Node(5))
             * ↳ Node(5) is now complete
             * 
             * 9. Back to Node(1), build right child → Node(3) (index = 8)
             * ↳ create Node(3)
             * ↳ call buildTree(9) for left child of Node(3)
             * 
             * 10. buildTree(9): -1
             * ↳ return null (left child of Node(3))
             * ↳ call buildTree(10) for right child of Node(3)
             * 
             * 11. buildTree(10): 6
             * ↳ create Node(6)
             * ↳ call buildTree(11) for left child of Node(6)
             * 
             * 12. buildTree(11): -1
             * ↳ return null (left child of Node(6))
             * ↳ call buildTree(12) for right child of Node(6)
             * 
             * 13. buildTree(12): -1
             * ↳ return null (right child of Node(6))
             * ↳ Node(6) is now complete
             */
            index++;
            if (nodes[index] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[index]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public void preorder(Node node) {
            if (node == null) {
                return;
            }

            System.out.print(node.data + ", ");
            preorder(node.left);
            preorder(node.right);
        }

        public void inorder(Node node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            System.out.print(node.data + ", ");
            inorder(node.right);
        }

        public void postorder(Node node) {
            if (node == null) {
                return;
            }
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + ", ");
        }

        public void levelorder(Node node) {
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            q.add(null);
            while (!q.isEmpty()) {
                Node curr = q.remove();

                if (curr == null) {
                    // Print a newline to separate levels
                    System.out.println();
                    if (q.isEmpty()) {
                        break; // If the queue is empty, we are done
                    } else {
                        // Add another null marker for the next level
                        q.add(null);
                    }
                } else {
                    // Print the data of the current node
                    System.out.print(curr.data + " ");

                    // Add the left and right children to the queue
                    if (curr.left != null) {
                        q.add(curr.left);
                    }
                    if (curr.right != null) {
                        q.add(curr.right);
                    }
                }
            }
        }
    

        public int sumOfTree( Node root){
            if(root==null){
                return 0;
            }
           int leftSum = sumOfTree(root.left);
           int rightSum  = sumOfTree(root.right);

           return leftSum+ rightSum + root.data;
        }

        public int maxDepth(Node root) {
            if(root == null ){
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth  = maxDepth(root.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }

        public  int Diameter(Node root){

            if(root==null){
                return 0;
            }
                int left = Diameter(root.left);
                int right = Diameter(root.right);
                int diam = maxDepth(root.left) + maxDepth(root.right) +1;
                return Math.max(left , Math.max(right,diam));


        }

        public static class TreeInfo{
            int height;
            int diameter;
            TreeInfo(int height,int diameter){
                this.height = height;
                this.diameter = diameter;
            }
        }

        public static  TreeInfo DiamaterOp(Node root){
            if(root== null){
                return new TreeInfo(0, 0);
            }
            TreeInfo left = DiamaterOp(root.left);
            TreeInfo right = DiamaterOp(root.right);

            int myHeight = Math.max(left.height,right.height)+1;

            int leftDiameter = left.diameter;
            int rightDiameter = right.diameter;
            int diameter3 = left.height + right.height +1;

            int myDiameter = Math.max(diameter3,Math.max(rightDiameter,leftDiameter));

return new TreeInfo(myHeight,myDiameter);
            

        }
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };

        Btree btree = new Btree();

        // Build the tree starting from the root
        Node root = btree.buildTree(nodes);
        System.out.println("levelorder");
        btree.levelorder(root);
        System.out.println("\npreorder");

        btree.preorder(root);
        System.out.println("\n\ninorder");
        btree.inorder(root);
        System.out.println("\n\npost order");
        btree.postorder(root);

        System.out.println("\n\nsum of tree : "+ btree.sumOfTree(root));

        System.out.println("\n \nmax depth of tree : "+ btree.maxDepth(root));

        System.out.println("Diameter : " + Btree.DiamaterOp(root).diameter);
        // Expected Tree Structure:
        // 1
        // / \
        // 2 3
        // / \ \
        // 4 5 6
    }
}
