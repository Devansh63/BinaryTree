package Project2_CS260_DevanshAgrawal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Devansh Agrawal
 *
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> {

	BTSNode<E> root;

	/**
	 * Constructor
	 */
	BinaryTree() {
		root = null;
	}
	/**Used this to call the function that recursively add the Node at the correct position
	 * 
	 * @param key
	 */
	void addNode(E key) {
		if(key == null) {
			return;
		}
		
		root = insertRec(root, key);
	}
	/** Returns the Node 
	 * @return the node 
	 */
	public BTSNode<E> getRoot() {
		return root;
		}
		

	/**
	 * The Function uses recursion to add the required key at the correct position
	 * 
	 * @param btsNode
	 * @param key
	 * @return the new root which contains the added node 
	 */
	BTSNode<E> insertRec(BTSNode<E> btsNode, E key) {
		if (root == null) {
			root = new BTSNode<E>(key);   
			return root;
		}
		
		if(btsNode == null) {
			btsNode = new BTSNode<E>(key);
			return btsNode;
		}
		if (0 > (key.compareTo(btsNode.getKey())))
			btsNode.setLeft(insertRec(btsNode.getLeft(), key)); 
		else if (0 < (key.compareTo(btsNode.getKey())))
			btsNode.setRight(insertRec(btsNode.getRight(), key));
		
		return btsNode;
	}

	/**Used this to call the function that recursively remove the desired Node then corrects the position of the other NOdes
	 * 
	 * @param key
	 */
	void removeNodeWithData(E key) {
		root = deleteRec(root, key);
	}

	/** 
	 * The Function uses recursion to remove the required key at the correct position
	 * 
	 * @param btsNode
	 * @param key
	 * @return the new root which does not contains the desired node 
	 */
	BTSNode<E> deleteRec(BTSNode<E> btsNode, E key) {
		if(root == null) {
			return root;
		}
		if (btsNode == null)
			return root;
		if (0 > (key.compareTo(btsNode.getKey())))
			btsNode.setLeft(deleteRec(btsNode.getLeft(), key));  
		else if (0 < (key.compareTo(btsNode.getKey())))
			btsNode.setRight(deleteRec(btsNode.getRight(), key));

		else {
				if(btsNode.getLeft() == null || btsNode.getRight() == null ) {
					BTSNode<E> temp = null;
				if(temp == btsNode.getLeft()) {
					temp = btsNode.getRight();
				}
				else {
					temp =btsNode.getLeft();
				}
				
				if(temp == null) {
					temp =btsNode;
					btsNode = null;
				}
				
				else {
					btsNode = temp;
				}
				
				}
				else {
					BTSNode<E> temp = minimunValueOfTree(btsNode.getRight());
					btsNode.setKey(temp.getKey());
					
					btsNode.setRight(deleteRec(btsNode.getRight(), temp.getKey()));
				}
		}

		return btsNode;
	}

	
//	    public BTSNode<E> search(BTSNode<E> root, E key) 
//	    { 
//	        
//	        if (root==null || root.key==key) 
//	            return root;
//	        if (0<(root.key.compareTo(key))) 
//	            return search(root.left, key); 
//	       
//	        return search(root.right, key); 
//	    } 
	/**Used the function to call the binary search function
	 * 
	 * @param key
	 * @return the desired node
	 */
	BTSNode<E> findNodeWithData(E key) {
		return binarysearch(root, key);

	}


	/** Performs a binary search on all the nodes in the tree to find the desired node
	 * @param currentNode
	 * @param key
	 * @return The desired node
	 */
	public BTSNode<E> binarysearch(BTSNode<E> currentNode, E key) {
		if (currentNode == null || currentNode.key.equals(key))
			return currentNode;
		if (0 > (currentNode.key.compareTo(key)))
			return binarysearch(currentNode.left, key);

		return binarysearch(currentNode.right, key);
	}
	
	/**Returns the node with the smallest value
	 * 
	 * @param btsNode
	 * @return the node with the smallest value
	 */
	BTSNode<E> minimunValueOfTree(BTSNode<E> btsNode){
		BTSNode<E> currentNode = btsNode;
		while(currentNode.getLeft() != null) {
			currentNode = currentNode.getLeft();
		}
		
		return currentNode;
	}


	/**Compile the data in the form of a Binary Tree into a string
	 * 
	 * @return Returns the string that has the desired print format. 
	 */
	public String printTreeDiagram() {
		String display = " ";
		Queue<BTSNode<E>> queuedList = new LinkedList<BTSNode<E>>();
		queuedList.add(root);
		queuedList.add(null);
		while (!queuedList.isEmpty()) {
			BTSNode<E> temporaryNode = queuedList.poll();
			if(temporaryNode == null) {
				if(!queuedList.isEmpty()) {
					queuedList.add(null);
				}
			}
			else {
				if(temporaryNode.getLeft() != null) {
					queuedList.add(temporaryNode.getLeft());
				}
				if(temporaryNode.getRight() != null) {
					queuedList.add(temporaryNode.getRight());
				}
				display += temporaryNode.getKey().toString();
			}
		}
		return display;
	}

	
	/** To print out the data in the form of an array
	 * @param root
	 * @return the data of the tree in the format of an array 
	 */
	public String inOrderTraversal(BTSNode<E> root) {
		String displayNodes = "";
		if (root != null) {
			displayNodes = displayNodes + this.inOrderTraversal(root.getLeft());
			displayNodes = displayNodes + root.toString() + " ";
			displayNodes = displayNodes + this.inOrderTraversal(root.getRight());
		}
		return displayNodes;
	}

	/** Used to print the string
	 * @param string
	 */
	void toString(String string) {
		System.out.print(string);
	}

}

