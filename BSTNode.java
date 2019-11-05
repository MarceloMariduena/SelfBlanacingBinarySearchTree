
/**
 * Node class for implementing the BST class
 *
 * DO NOT MODIFY THIS FILE!!
 *
 */

public class BSTNode<K extends Comparable<? super K>, V> {
	private K key;
    private V value;
    private BSTNode<K, V> left;
    private BSTNode<K, V> right;
    private int height;
    private int balanceFactor;
    private int size; // The number of nodes in the subtree rooted at the node

    /**
     * Constructs a BSTNode with the given key and value.
     *
     * @param key    the key stored in the new node
     * @param value  the value associated with the key
     */
    BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
        size = 1;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    K getKey() {
        return key;
    }
    
    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue() {
        return value;
    }

    /**
     * Gets the left child.
     *
     * @return the left child
     */
    BSTNode<K, V> getLeft() {
        return left;
    }

    /**
     * Gets the right child.
     *
     * @return the right child
     */
    BSTNode<K, V> getRight() {
        return right;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    void setValue(V value) {
        this.value = value;
    }

    /**
     * Sets the left child.
     *
     * @param left the new left child
     */
    void setLeft(BSTNode<K, V> left) {
        this.left = left;
    }

    /**
     * Sets the right child.
     *
     * @param right the new right child
     */
    void setRight(BSTNode<K, V> right) {
        this.right = right;
    }
    
    /**
     * Gets the height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the balance factor.
     *
     * @return the balance factor
     */
    public int getBalanceFactor() {
        return balanceFactor;
    }

    /**
     * Sets the height.
     *
     * @param height  the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the balance factor.
     *
     * @param balanceFactor the new balance factor
     */
    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }
    
    /**
     * Gets the size
     * 
     * @return  the size of the subtree rooted at the node
     */
    public int getSize() {
    	return size;
    }
    
    /**
     * Sets the size
     * 
     * @param size  the new size
     */
    public void setSize(int size) {
    	this.size = size;
    }
}
