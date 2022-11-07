/*
 * Name: Kevin Chan
 * PID:  A16871142
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Kevin Chan
 * @since  11/5/2022
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {

            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this(left,right,new LinkedList<T>(), key);
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {

            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key key to be entered
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key){
        // references zybook's implementation
        if(key == null) throw new NullPointerException();
        if(root == null){
           root = new BSTNode(null,null,key);
           nelems++;
        } else {
            BSTNode currNode = root;
            while(currNode != null) {
                if (key.compareTo(currNode.getKey()) == 0) return false;
                else if (key.compareTo(currNode.getKey()) < 0) {
                    // If no left child exists, add the new node
                    // here; otherwise start again from the left child.
                    if (currNode.getLeft() == null) {
                        currNode.setleft(new BSTNode(null, null, key));
                        currNode = null;
                        nelems++;
                    } else {
                        currNode = currNode.getLeft();
                    }
                }
                else{
                    // If no right child exists, add the new node
                    // here; otherwise repeat from the right child.
                    if(currNode.getRight() == null){
                        currNode.setright(new BSTNode(null, null,key));
                        currNode = null;
                        nelems++;
                    }
                    else{
                        currNode = currNode.getRight();
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        // references zybook's implementation
        if(key == null) throw new NullPointerException();
        BSTNode currNode = root;
        while(currNode != null){
            // Return true if the key matches
            if(currNode.getKey().compareTo(key) == 0){
                return true;
            }
            // Navigate to the left if the key is
            // less than the BST node's key.
            else if (key.compareTo(currNode.getKey())<0){
                currNode = currNode.getLeft();
            }
            // Navigate to the right if the key is
            // greater than the node's key.
            else{
                currNode = currNode.getRight();
            }
        }
        // The key was not found in the tree.
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        // references zybook's implementation
        if((key == null) || (data == null)) throw new NullPointerException();
        BSTNode currNode = root;
        while(currNode != null){
            // Return true if the key matches
            if(currNode.getKey().compareTo(key) == 0){
                currNode.getDataList().add(data);
                return;
            }
            // Navigate to the left if the key is
            // less than the BST node's key.
            else if (key.compareTo(currNode.getKey())<0){
                currNode = currNode.getLeft();
            }
            // Navigate to the right if the key is
            // greater than the node's key.
            else{
                currNode = currNode.getRight();
            }
        }
        // The key was not found in the tree.
        throw new IllegalArgumentException();
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        // references zybook's implementation
        if(key == null) throw new NullPointerException();
        BSTNode currNode = root;
        while(currNode != null){
            // Return true if the key matches
            if(currNode.getKey().compareTo(key) == 0){
                return currNode.getDataList();
            }
            // Navigate to the left if the key is
            // less than the BST node's key.
            else if (key.compareTo(currNode.getKey())<0){
                currNode = currNode.getLeft();
            }
            // Navigate to the right if the key is
            // greater than the node's key.
            else{
                currNode = currNode.getRight();
            }
        }
        // The key was not found in the tree.
        throw new IllegalArgumentException();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if(root == null) return -1;
        else return findHeightHelper(root);
    }
    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root){
        if(root == null){
            return 0;
        }
        int leftHeight;
        if(root.getLeft() == null) leftHeight = 0;
        else{
            leftHeight = 1+ findHeightHelper(root.getLeft());
        }
        int rightHeight;
        if(root.getRight() == null) rightHeight = 0;
        else {
            rightHeight = 1+ findHeightHelper(root.getRight());
        }
        if(leftHeight > rightHeight) return leftHeight;
        else return rightHeight;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> storage;

        /**
         * Initializes the storage stack with the left path
         */
        public BSTree_Iterator() {
            storage = new Stack<>();
            BSTNode currNode = getRoot();
            //add the whole left path.
            while(currNode != null){
                storage.push(currNode);
                currNode = currNode.getLeft();
            }
        }

        /**
         * Checks if there are still things left to iterate through
         * @return false if stack is empty, true otherwise.
         */
        public boolean hasNext() {
            return !storage.isEmpty();
        }

        /**
         * Iterates to the next element, if the element removed from the stack has a right element
         * then add that element's left path.
         * @return the top element in the stack
         */
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            //remove the top element, then check if the element to the right exists
            //if the right element does exist, then add its entire left path.
            BSTNode toRemove = storage.pop();
            if(toRemove.getRight() != null){
                BSTNode currNode = toRemove.getRight();
                while(currNode != null){
                    storage.push(currNode);
                    currNode = currNode.getLeft();
                }
            }

            return toRemove.getKey();
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> bst1 = new ArrayList<>();
        while(iter1.hasNext()){
            bst1.add(iter1.next());
        }
        ArrayList<T> bst2 = new ArrayList<>();
        while(iter2.hasNext()){
            bst2.add(iter2.next());
        }
        ArrayList<T> common = new ArrayList<>();
        for(T value1: bst1){
            for(T value2:bst2){
                if(value1.compareTo(value2) == 0){
                    if(!common.contains(value1)) common.add(value1);
                }
            }

        }
        return common;
    }

    public T levelMax(int level) {
        if(level > findHeight()) return null;
        if(level == 0)return root.getKey();
        BSTNode currNode = getRoot();
        for(int i = 0; i<level; i++){
            currNode.getKey();
        }
        return currNode.getKey();
    }

}
