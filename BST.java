
/**
 * Your implementation of the BST class
 *
 * @author Marcelo Mariduena
 *
 * Collaborators: Dr Ding's javaFX code was edited and utilized for visualization :)
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BST<K extends Comparable<? super K>, V> {
	private BSTNode<K, V> root;
	
	/**
     * This constructor initializes an empty BST.
     *
     * There is no need to do anything for the constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Adds a new entry to the tree or updates the value of an existing key in the tree
     * 
     * Traverse the tree to find the appropriate location. If the key is
     * already in the tree, then update its value to the new value. Otherwise
     * create a new node consisting the new (key, value) pair and add it to the tree.
     * The new node becomes a leaf. Then go back up the tree from the new leaf to the root. 
     * Upon seeing an imbalanced node on the path, balance it with proper rotations. 
     * Update the height, balance factor and the new size instance variable of every affected node.
     * 
     * This method is essentially the same as its counterpart in SimpleBST. Therefore
     * you can use the code for put(), its helper method putHelper() and the helper's helpers - 
     * balance(), rotateLeft(), rotateRight(), update() and height(), from the SimpleBST class.
     * The only new items you need to incorporate are the following:
     * 		1. Modify the code properly to handle the generic types K and V for the key and value.
     * 		2. Provide the code for rotateLeft() which mirrors rotateRight(). You can get 
     * 		   lots of help from Slide 33 for the BST lectures when completing this method.
     * 		3. In update(), update the size instance variable of a BST node in addition to 
     *         height and balance factor. You need to think of the relation between the size of a node 
     *         and the sizes of its left and right children, and how to define the size of a null node.
     *      4. Handle the exceptions properly.
     * 
     * Time Complexity: O(log n)
     * 
     * @param key    the key of the entry to add or update
     * @param value  the value associated with key
     * @throws       IllegalArgumentException if key or value is null
     */
    public void put(K key, V value) throws IllegalArgumentException
    {
        if(key == null || value == null)
            throw new IllegalArgumentException();
        else
            root = putHelper(root, key, value);
    }
    private BSTNode<K, V> putHelper(BSTNode<K, V> node, K key, V value)
    {
        if (node == null)
            return new BSTNode<K, V>(key, value);

        if (key.compareTo(node.getKey()) == 0)
        {
            node.setValue(value);
            return node;
        }

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(putHelper(node.getLeft(), key, value));
        else
            node.setRight(putHelper(node.getRight(), key, value));

        return balance(node);
    }


    /**
     * Returns the height of a node in the tree
     *
     * @param node  the node to return the height for
     * @return      the height of node if it's valid, -1 if it's null
     */
    private int height(BSTNode<K, V> node)
    {
        if (node == null)
            return -1;
        else
            return node.getHeight();
    }


    /**
     * Updates the height and balance factor of a node
     *
     * @param node  the node to update the height and balance factor for
     */
    private void update(BSTNode<K, V> node)
    {
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
        node.setBalanceFactor(leftHeight - rightHeight);

        node.setSize(size(node));
    }
    int size(BSTNode<K, V> node)
    {
        if (node == null)
            return 0;
        else
            return (size(node.getLeft()) + 1 + size(node.getRight()));
    }


    /**
     * Performs a right rotation on a node
     *
     * @param node  the node to rotate
     * @return      the left child
     *              which is the new root of the subtree after rotation
     */
    private BSTNode<K, V> rotateRight(BSTNode<K, V> node)
    {
        BSTNode<K, V> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        update(node);
        update(left);
        return left;
    }


    /**
     * Performs a left rotation on a node
     *
     * @param node  the node to rotate
     * @return      the right child
     *              which is the new root of the subtree after rotation
     */
    private BSTNode<K, V> rotateLeft(BSTNode<K, V> node)
    {
        BSTNode<K, V> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        update(node);
        update(right);
        return right;
    }


    /**
     * Balances an imbalanced node by left and right rotations
     *
     * @param node  the node to balance
     * @return      the new root of the subtree after balance
     */
    private BSTNode<K, V> balance(BSTNode<K, V> node)
    {
        update(node);
        if (Math.abs(node.getBalanceFactor()) <= 1)
            return node;

        if (node.getBalanceFactor() > 1)
        {
            if (node.getLeft().getBalanceFactor() >= 0)
                return rotateRight(node);
            else
            {
                node.setLeft(rotateLeft(node.getLeft()));
                return rotateRight(node);
            }
        }
        else
        {
            if (node.getRight().getBalanceFactor() <= 0)
                return rotateLeft(node);
            else
            {
                node.setRight(rotateRight(node.getRight()));
                return rotateLeft(node);
            }
        }
    }


    /**
     * Returns the value associated with a given key.
     *
     * Traverse the tree to find the appropriate location. If the key is
     * in the tree, then return its value. Otherwise return null.
     *
     * Time Complexity: O(log n)
     *
     * @param key  the key to search for
     * @return     the value associated with key if key is in the tree, null otherwise
     * @throws     IllegalArgumentException if key is null
     */
    public V get(K key)
    {
        if(key == null)
            throw new IllegalArgumentException();
        else
        {
            BSTNode<K, V> match = getHelper(root, key);
            if(match == null)
                return null;
            else
                return match.getValue();
        }
    }
    private BSTNode<K, V> getHelper(BSTNode<K, V> node, K key)
    {
        if (key.compareTo(node.getKey()) == 0)
            return node;

        if (key.compareTo(node.getKey()) < 0)
            return node.getLeft() == null ? null : getHelper(node.getLeft(), key);
        else
            return node.getRight() == null ? null : getHelper(node.getRight(), key);
    }


    /**
     * Traverses the tree by an in-order traversal
     * Sorts data by key
     *
     * @return  a list consisting of all keys in the tree in the ascending order
     */
    public List<K> inOrder()
    {
        LinkedList<K> order = new LinkedList<K>();
        inOrderHelper(root, order);
        return order;
    }
    public List<K> inOrder(BSTNode<K, V> node) //for subtrees
    {
        LinkedList<K> order = new LinkedList<>();
        inOrderHelper(node, order);
        return order;
    }
    private void inOrderHelper(BSTNode<K, V> node, LinkedList<K> order)
    {
        if (node == null)
            return;

        inOrderHelper(node.getLeft(), order);
        order.add(node.getKey());
        inOrderHelper(node.getRight(), order);
    }


    /**
     * Finds and returns all keys in the tree in descending order
     * 
     * Time Complexity: O(n)
     * 
     * Note: You would NOT receive credit if you perform an in-order traversal of the tree and 
     * then reverse the returned list, as this is unnecessary. Instead your method should 
     * directly obtain the list of all keys in descending order. 
     * 
     * Hint: Modify in-order traversal by changing the order in which the nodes are visited.
     * 
     * @return  the list of all keys in the tree in descending order
     */
    public List<K> reverseOrder()
    {
        LinkedList<K> reverseOrder = new LinkedList<>();
        reverseOrderHelper(root, reverseOrder);
        return reverseOrder;
    }
    private void reverseOrderHelper(BSTNode<K, V> node, LinkedList<K> reverseOrder)
    {
        if (node == null)
            return;

        reverseOrderHelper(node.getRight(), reverseOrder);
        reverseOrder.add(node.getKey());
        reverseOrderHelper(node.getLeft(), reverseOrder);
    }


    /**
     * Finds and returns the k smallest keys in ascending order
     * 
     * Ex:
     * For the following BST
     * 
     *                50
     *              /    \
     *            25      75
     *           /  \     / \
     *          12   37  70  80
     *         /  \    \      \
     *        10  15    40    85
     *           /
     *          13
     *          
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25]
     * kSmallest(3) should return the list [10, 12, 13]
     * kSmallest(20) should cause java.lang.IllegalArgumentException to be thrown
     * 
     * Time Complexity: O(log n + k)
     * 
     * Note: The required time complexity does NOT allow you to perform an in-order traversal
     * on the entire tree and then return the k smallest keys. Instead you should only traverse the 
     * branches of the tree necessary to get the data you need.
     * 
     * @param k  the number of smallest keys to find
     * @return   the list of k smallest keys in ascending order
     * @throws   IllegalArgumentException if k < 0 or k > the size of the tree
     */
    public List<K> kSmallest(int k) throws IllegalArgumentException //FIX THIS
    {
        if(k < 0 || k > root.getSize())
            throw new IllegalArgumentException();
        else
        {
            BSTNode<K, V> subtree = kSmallestHelper(root, k);
            return inOrder(subtree).subList(0, k);
        }
    }
    private BSTNode<K, V> kSmallestHelper(BSTNode<K, V> node, int k)
    {
        return node.getSize() == k ? node :
                node.getLeft() == null ? node :
                        node.getLeft().getSize() < k ? node : kSmallestHelper(node.getLeft(), k);
    }


    /**
     * Finds and returns the predecessor of the given key, i.e. the largest key 
     * in the tree that is smaller than the given key.
     * 
     * Note: The given key may or may not be in the tree.
     * 
     * Time Complexity: O(log n)
     * 
     * Note: The required time complexity does NOT allow you to traverse the tree to sort the keys  
     * 
     * Hint: Start by searching for the key in the tree.
     * If the key is not in the tree, then its predecessor is the lowest key on the
     * search path that is smaller than the key. If the key is in the tree, 
     * then there are two cases:
     * 
     * 1. The left subtree of the key is empty. In this case, its predecessor is the lowest key 
     * on the search path that is smaller than the key.
     * 
     * 2. The left subtree of the key is not empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 
     * Ex:
     * For the following BST
     * 
     *                50
     *              /    \
     *            25      75
     *           /  \     / \
     *          12   37  70  80
     *         /  \    \     / \
     *        10  15    40  78  85
     *        
     * predecessor(82) should return 80
     * predecessor(78) should return 75
     * predecessor(50) should return 40
     * predecessor(10) should return null
     * 
     * @param key  the key to find the predecessor for
     * @return     the predecessor of key if the predecessor exists, null otherwise
     * @throws     IllegalArgumentException if key is null
     */
    public K predecessor(K key) //FIX THIS
    {
        if(key == null)
            throw new IllegalArgumentException();
        else
        {
            BSTNode<K, V> match = predecessorHelper(root, key);
            if(match == null)
                return null;
            else
                return match.getKey();
        }
    }
    private BSTNode<K, V> predecessorHelper(BSTNode<K, V> node, K key) //FIX THIS
    {
        //      20
        //  15      25

        // input: 17
        // output: 15

        //input:    27
        //output:   25

        return null;

//        return node.getLeft() == null ? node :
//                node.getRight() == null ? node :
//                        node.getRight().getKey().compareTo(key) > 0 ? node :
//                            predecessorHelper(node.getRight(), key);
    }


    /**
     * Finds and returns the successor of the given key, i.e. the smallest key 
     * in the tree that is larger than the given key.
     * 
     * This method mirrors predecessor()
     * 
     * Time Complexity: O(log n)
     * 
     * @param key  the key to find the successor for
     * @return     the successor of key if the successor exists, null otherwise
     * @throws     IllegalArgumentException if key is null
     */
    public K successor(K key) //FIX THIS
    {
        if(key == null)
            throw new IllegalArgumentException();
        else
        {
            BSTNode<K, V> match = successorHelper(root, key);
            if(match == null)
                return null;
            else
                return match.getKey();
        }
    }
    private BSTNode<K, V> successorHelper(BSTNode<K, V> node, K key) //FIX THIS
    {
        if(node.getKey().compareTo(key) > 0)
        {
            System.out.println("Case 1: Current node is greater.");
            if(node.getLeft() == null)
            {
                System.out.println("Case 1.1: There is no node to the left.");
                return node;
            }
            else if(node.getLeft().getKey().compareTo(key) > 0)
            {
                System.out.println("Case 1.2: Node to left exists and is greater. Dropping to the left.");
                return successorHelper(node.getLeft(), key);
            }
            else if(node.getLeft().getRight() == null)
            {
                System.out.println("Case 1.3: Node to the left is lesser but... there is no node to the right of that one.");
                return node;
            }
            else if(node.getLeft().getRight().getKey().compareTo(key) > 0)
            {
                System.out.println("Case 1.4: Node to the right of the left child is greater than input. Dropping down there.");
                return successorHelper(node.getLeft().getRight(), key);
            }
            else
            {
                System.out.println("Case 1.5: Node to the right of the left child is less than input. Returning current node.");
                return node;
            }
        }
        else
        {
            System.out.println("Case 2: Current node is lesser.");
            if(node.getRight() == null)
            {
                System.out.println("Case 2.1: There is not node to the right. No successor.");
                return null;
            }
            else if(node.getRight().getKey().compareTo(key) < 0)
            {
                System.out.println("Case 2.2: Node to the right is still lesser. Going right.");
                return successorHelper(node.getRight(), key);
            }
            else
            {
                System.out.println("Case 2.3: Node to the right is greater.");
                return successorHelper(node.getRight(), key);
            }
        }
    }


    /**
     * For Extra Credit ONLY
     * 
     * Removes the data whose key matches the given key and returns its associated value
     * if the key is in the tree; returns null otherwise.
     * 
     * This is a hard method and I did not show you how to do this in the lectures.
     * If you are interested and ambitious enough to attempt this, come to see me and I will
     * explain to you how to remove a node from a BST and how to balance the tree after the removal. 
     * 
     * Time Complexity: O(log n)
     * 
     * @param key  the key of the data to remove
     * @return     the value associated with key if key is in the tree, null otherwise
     * @throws     IllegalArgumentException if key is null
     */
    public V remove(K key) {
    	
    	// Replace this line with your return statement
    	return null;
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. 
     *
     * @return the root of the tree
     */
    public BSTNode<K, V> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Used for testing purposes.
     */
    public static void main(String[] args) //For testing purposes
    {
        BST tree = new BST();
        tree.put(60, 6);
        tree.put(20, 2);
        tree.put(70, 7);
        tree.put(90, 9);
        tree.put(95, 8);
        tree.put(10, 1);
        tree.put(40, 4);
        tree.put(30, 3);
        tree.put(50, 5);
        tree.put(100, 5);
        tree.put(110, 5);
        tree.put(230, 5);
        tree.put(340, 5);

//        try {
//            System.out.printf("Using kSmallest of %d: %s\n", 7, tree.kSmallest(7));
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }

        System.out.println(tree.successor(25));
        System.out.println("Input: 25. Proper output: 30\n");

        System.out.println(tree.successor(91));
        System.out.println("Input: 91. Proper output: 95\n");

        System.out.println(tree.successor(5));
        System.out.println("Input: 05. Proper output: 10\n");

        System.out.println(tree.successor(89));
        System.out.println("Input: 89. Proper output: 90\n");
    }
}
