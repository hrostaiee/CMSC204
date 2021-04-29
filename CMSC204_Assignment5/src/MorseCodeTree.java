package cmsc204_assignment5;

import java.util.ArrayList;

/**A generic linked binary tree which inherits from the LinkedConverterTreeInterface.  
 * The class uses an external generic TreeNode class parameterized as a String: TreeNode<String>.  
 * This class uses the private member of root.  Nodes are added based on their morse code value.  
 * A '.' (dot) means to traverse left and a '-' (dash) means to traverse right.  
 * @author Hasib
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	
	//root node of the tree
	private TreeNode<String> root;
	
	//The constructor will call the method to “build the tree”
	public MorseCodeTree() {
		buildTree();
	}
	
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#addNode(cmsc204_assignment5.TreeNode, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if ( code.length() == 1) {
		      if (code.charAt(0) == '.') {
		        root.leftChild = new TreeNode<String>(letter);
		      } else if (code.charAt(0) == '-') {
		        root.rightChild = new TreeNode<String>(letter);
		      }
		    } else {
		      if (code.charAt(0) == '.') {
		        addNode(root.leftChild, code.substring(1), letter);
		      } else if (code.charAt(0) == '-') {
		        addNode(root.rightChild,  code.substring(1), letter);
		      }
		    }
		
	}
	
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#buildTree()
	 */
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#buildTree()
	 */
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#buildTree()
	 */
	@Override
	public void buildTree() {
		
		//Creating empty root;
		root = new TreeNode<String>("");
		//Insert First Level
		insert(".", "e");
	    insert("-", "t");

	    //Insert Second Level
	    insert("..", "i");
	    insert(".-", "a");
	    insert("-.", "n");
	    insert("--", "m");

	    //Insert 3rd Level
	    insert("...", "s");
	    insert("..-", "u");
	    insert(".-.", "r");
	    insert(".--", "w");
	    insert("-..", "d");
	    insert("-.-", "k");
	    insert("--.", "g");
	    insert("---", "o");

	    //Insert 4th level
	    insert("....", "h");
	    insert("...-", "v");
	    insert("..-.", "f");
	    insert(".-..", "l");
	    insert(".--.", "p");
	    insert(".---", "j");
	    insert("-...", "b");
	    insert("-..-", "x");
	    insert("-.-.", "c");
	    insert("-.--", "y");
	    insert("--..", "z");
	    insert("--.-", "q");
	}
	
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#getRoot()
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#setRoot(cmsc204_assignment5.TreeNode)
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
		
	}
	

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public LinkedConverterTreeInterface insert(String code, String result) {
	    addNode(root, code, result);
	    return this;
	}

	

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#fetch(java.lang.String)
	 */
	@Override
	public String fetch(String code) {
		 return fetchNode(root, code);
	}

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#fetchNode(cmsc204_assignment5.TreeNode, java.lang.Object)
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if ( code.length() == 1) {
		      if (code.charAt(0) == '.') {
		        return root.leftChild.data;
		      } else if (code.charAt(0) == '-') {
		        return root.rightChild.data;
		      }
		    } else {
		      if (code.charAt(0) == '.') {
		        return fetchNode(root.leftChild, code.substring(1));
		      } else if (code.charAt(0) == '-') {
		        return fetchNode(root.rightChild, code.substring(1));
		      }
		    }
		    return null;
	}

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#delete(java.lang.Object)
	 */
	@Override
	public LinkedConverterTreeInterface delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#update()
	 */
	@Override
	public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#toArrayList()
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
	    LNRoutputTraversal(root, list);
	    return list;
	}

	/* (non-Javadoc)
	 * @see cmsc204_assignment5.LinkedConverterTreeInterface#LNRoutputTraversal(cmsc204_assignment5.TreeNode, java.util.ArrayList)
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		 if (root == null) {
		      return;
		    }
		    if (root.leftChild != null) {
		      LNRoutputTraversal(root.leftChild, list);
		    }
		    list.add(root.data);
		    if (root.rightChild != null) {
		      LNRoutputTraversal(root.rightChild, list);
		    }
		  
		
	}



}
