package cmsc204_assignment5;

/**
 * @author Hasib
 *This generic class is used in the MorseCodeTree classes. 
 *The class consists of a reference to the data and a reference to the left and right child.  
 * @param <T> Generic object type
 */
public class TreeNode<T> {
	T data;
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	//Constructor which takes data and assign it to dat field and sent the left and right null
	public TreeNode(T dataNode) {
		data = dataNode;
		leftChild = null;
		rightChild = null;
	}
	
	//Constructor which takes another TreeNode and make a copy of it to the current object
	public TreeNode (TreeNode<T> node) {
		data = node.data;
		leftChild=node.leftChild;
		rightChild = node.rightChild;
	}

	
	/** This method returns data 
	 * @return data;
	 */
	public T getData() {
		return data;
	}
}
