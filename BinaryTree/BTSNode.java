package Project2_CS260_DevanshAgrawal;

import java.io.Serializable;

/**
 * @author Devansh Agrawal
 *
 * @param <E>
 */
public class BTSNode<E extends Comparable<E>> implements Serializable{
	E key;
	int height;
	
	BTSNode<E> left, right;

	/**
	 * Constructor 
	 */
	public BTSNode() {
		key = null; 
		left = right = null;
	}

	/**Used to initialize a node
	 * 
	 * @param item
	 */
	public BTSNode(E item) {
		key = item;
		this.height = 1; 
		left = right = null;
	}

	
	/**
	 * Getters and Setters for all the variables. 
	 */
	public E getKey() {
		return key;
	}

	public void setKey(E key) {
		this.key = key;
	}

	public BTSNode<E> getLeft() {
		return left;
	}

	public void setLeft(BTSNode<E> left) {
		this.left = left;
	}

	public BTSNode<E> getRight() {
		return right;
	}

	public void setRight(BTSNode<E> right) {
		this.right = right;
	}

}
