package BTree;

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

        public void inorder(Node node){
            if(node == null){
                return;
            }
            inorder(node.left);
            System.out.print(node.data + ", ");
            inorder(node.right);
        }

        public void postorder(Node node){
            if(node == null){
                return;
            }
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + ", ");
        }
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };

        Btree btree = new Btree();

        // Build the tree starting from the root
        Node root = btree.buildTree(nodes);

        System.out.println("\npreorder");  

        btree.preorder(root);
        System.out.println("\n\ninorder");
        btree.inorder(root);
        System.out.println("\n\npost order");
        btree.postorder(root);
        // Expected Tree Structure:
        // 1
        // / \
        // 2 3
        // / \ \
        // 4 5 6
    }
}
