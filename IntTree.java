package hw3;

import java.util.LinkedList;

import edu.princeton.cs.algs4.StdOut;

/* ***********************************************************************
 * A simple BST with int keys and no values
 *
 * Complete each function below.
 * Write each function as a separate recursive definition (do not use more than one helper per function).
 * Depth of root==0.
 * Height of leaf==0.
 * Size of empty tree==0.
 * Height of empty tree=-1.
 *
 * TODO: complete the functions in this file.
 * DO NOT change the Node class.
 * DO NOT change the name or type of any function (the first line of the function)
 *
 * Restrictions:
 *  - no loops (you cannot use "while" "for" etc...)
 *  - only one helper function per definition
 *  - no fields (static or non static).  Only local variables
 *************************************************************************/
public class IntTree {
	private Node root;

	private static class Node {
		public int key;
		public Node left, right;

		public Node(int key) {
			this.key = key;
		}
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(Node n) {
		if (n == null)
			return;
		printInOrder(n.left);
		System.out.println(n.key);
		printInOrder(n.right);
	}

	// Recall the definitions of height and depth.
	// in the BST with level order traversal "41 21 61 11 31",
	// node 41 has depth 0, height 2
	// node 21 has depth 1, height 1
	// node 61 has depth 1, height 0
	// node 11 has depth 2, height 0
	// node 31 has depth 2, height 0
	// height of the whole tree is the height of the root

	// 20 points
	/*
	 * Returns the height of the tree. For example, the BST with level order
	 * traversal 50 25 100 12 37 150 127 should return 3.
	 * 
	 * Note that the height of the empty tree is defined to be -1.
	 */
	public int height() {
		return heightH(root);
	}
	private int heightH(Node top) {
		if (top == null) {
			return -1;
		}
		else {
			int tempL = heightH(top.left);
			int tempR = heightH(top.right);
			if(tempL > tempR) {
				return tempL + 1;
			} else {
				return tempR + 1;
			}
		}
	}

	// 20 points
	/*
	 * Returns the number of nodes with odd keys. For example, the BST with level
	 * order traversal 50 25 100 12 37 150 127 should return 3 (25, 37, and 127).
	 */
	public int sizeOdd() {
		return sizeOddH(root);
	}
	
	private int sizeOddH(Node top) {
		if (top == null) {
			return 0;
		} else {
			int tempL = sizeOddH(top.left);
			int tempR = sizeOddH(top.right);
			if(top.key % 2 != 0) {
				return tempL + tempR + 1;
			}else {
				return tempL + tempR;
			}
		}
	}

	// 20 points
	/*
	 * Returns true if this tree and that tree "look the same." (i.e. They have the
	 * same keys in the same locations in the tree.) Note that just having the same
	 * keys is NOT enough. They must also be in the same positions in the tree.
	 */
	public boolean treeEquals(IntTree that) {
		return treeEqualsH(root, that.root);
	}
	
	private boolean treeEqualsH(Node top, Node top2) {
		if (top == null && top2 == null) {
			return true;
		}
		if (top == null || top2 == null) {
			return false;
			
		} else {
			if(top.key != top2.key) {
				return false;
			}
			boolean tempL = treeEqualsH(top.left, top2.left);
			boolean tempR = treeEqualsH(top.right, top2.right);
			
			if (tempL || tempR) {
				return tempL && tempR;
			}
			return false;
			
		}
	}

	// 10 points
	/*
	 * Returns the number of nodes in the tree, at exactly depth k. For example,
	 * given BST with level order traversal 50 25 100 12 37 150 127 the following
	 * values should be returned t.sizeAtDepth(0) == 1 [50] t.sizeAtDepth(1) == 2
	 * [25, 100] t.sizeAtDepth(2) == 3 [12, 37, 150] t.sizeAtDepth(3) == 1 [127]
	 * t.sizeAtDepth(k) == 0 for k >= 4
	 */
	public int sizeAtDepth(int k) {
		return sizeAtDepthH(root, k);
	}
	
	private int sizeAtDepthH(Node top, int k) {
		if(top == null) {
			return 0;
		} else if (k == 0) {
			return 1;
		} else {
			int tempL = sizeAtDepthH(top.left, k - 1);
			int tempR = sizeAtDepthH(top.right, k - 1);
			return tempL + tempR;
		}
	}

	// 10 points
	/*
	 * Returns the number of nodes in the tree "above" depth k. Do not include nodes
	 * at depth k. For example, given BST with level order traversal 50 25 100 12 37
	 * 150 127 the following values should be returned t.sizeAboveDepth(0) == 0
	 * t.sizeAboveDepth(1) == 1 [50] t.sizeAboveDepth(2) == 3 [50, 25, 100]
	 * t.sizeAboveDepth(3) == 6 [50, 25, 100, 12, 37, 150] t.sizeAboveDepth(k) == 7
	 * for k >= 4 [50, 25, 100, 12, 37, 150, 127]
	 */
	public int sizeAboveDepth(int k) {
		return sizeAboveDepthH(root, k);
	}
	
	private int sizeAboveDepthH(Node top, int k) {
		if (top == null) {
			return 0;
		} else if (k == 0) {
			return 0;
		} else {
			int tempL = sizeAboveDepthH(top.left, k-1);
			int tempR = sizeAboveDepthH(top.right, k-1);
			return tempL + tempR + 1;
			
		}
	}

	// 10 points
	/*
	 * Returns true if for every node in the tree has the same number of nodes to
	 * its left as to its right. For example, the BST with level order traversal 50
	 * 25 100 12 37 150 127 is NOT perfectly balanced. Although most of the nodes
	 * (including the root) have the same number of nodes to the left as to the
	 * right, the nodes with 100 and 150 do not and so the tree is not perfeclty
	 * balanced.
	 * 
	 * HINT: In the helper function, change the return type to int and return -1 if
	 * the tree is not perfectly balanced otherwise return the size of the tree. If
	 * a recursive call returns the size of the subtree, this will help you when you
	 * need to determine if the tree at the current node is balanced.
	 */
	public boolean isPerfectlyBalanced() {
		if (isPerfectlyBalancedH(root) == -1) {
			return false;
		} else {
			return true;
		}
		
	}
	
	private int isPerfectlyBalancedH(Node top) {
		if (top == null) {
			return 0;
		} else {
			
			int tempL = isPerfectlyBalancedH(top.left);
			if (tempL == -1) {
				return -1;
			}
			int tempR = isPerfectlyBalancedH(top.right);
			if (tempR == -1) {
				return -1;
			}
			if (tempL != tempR) {
				return -1;
			}
			return tempL + tempR + 1;
		}
		
	}

	/*
	 * Mutator functions HINT: This is easier to write if the helper function
	 * returns Node, rather than void similar to recursive mutator methods on lists.
	 * compare size of left side and size of right side, return -1 if not equal, return total size if equal
	 */

	// 10 points
	/*
	 * Removes all subtrees with odd roots (if node is odd, remove it and its
	 * descendants) A node is odd if it has an odd key. If the root is odd, then you
	 * should end up with the empty tree. For example, when called on a BST with
	 * level order traversal 50 25 100 12 37 150 127 the tree will be changed to
	 * have level order traversal 50 100 150
	 */
	public void removeOddSubtrees() {
		root = removeOddSubtreesH(root);
	}
	
	private Node removeOddSubtreesH(Node top) {
		if (top == null) {
			return null;
		}
		if (top.key % 2 != 0) {
			return null;
		} else {
			top.left = removeOddSubtreesH(top.left);
			top.right = removeOddSubtreesH(top.right);
			
			if (top.key % 2 != 0) {
				top.left = null;
				top.right = null;
				top = null;
			}
			return top;
		}
	}

	/*
	 * ***************************************************************************
	 * Some methods to create and display trees Do not modify anything below!
	 *****************************************************************************/

	public void setElementsUsingArray(int[] data) {
		Node[] nodes = new Node[data.length];
		for (int i = 1; i < data.length; i++) {
			if (data[i] != 0)
				nodes[i] = new Node(data[i]);
		}
		for (int i = 1; i < nodes.length; i++) {
			if (nodes[i] != null) {
				if (2 * i < nodes.length)
					nodes[i].left = nodes[2 * i];
				if (2 * i + 1 < nodes.length)
					nodes[i].right = nodes[2 * i + 1];
			}
		}
		root = nodes[1];
	}

	/*
	 * ***************************************************************************
	 * Some methods to create and display trees
	 *****************************************************************************/

	// Do not modify "put"
	public void put(int key, String path) {
		if (path.length() == 0) {
			if (root == null)
				root = new Node(key);
			else
				root.key = key;
		} else {
			Node current = root;
			int pathIndex = 0;
			while (current != null & pathIndex < path.length() - 1) {
				char nextDirection = path.charAt(pathIndex);
				if (nextDirection == 'l')
					current = current.left;
				else if (nextDirection == 'r')
					current = current.right;
				else
					throw new RuntimeException("Illegal path: " + path);
				pathIndex++;
			}
			if (current == null) {
				throw new RuntimeException("Illegal path: " + path);
			}
			char direction = path.charAt(pathIndex);
			if (direction == 'l') {
				if (current.left == null)
					current.left = new Node(key);
				else
					current.left.key = key;
			} else if (direction == 'r') {
				if (current.right == null)
					current.right = new Node(key);
				else
					current.right.key = key;
			} else {
				throw new RuntimeException("Illegal path: " + path);
			}
		}
	}

	// Do not modify "levelOrder"
	public Iterable<Integer> levelOrder() {
		LinkedList<Integer> keys = new LinkedList<Integer>();
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node n = queue.remove();
			if (n == null)
				continue;
			keys.add(n.key);
			queue.add(n.left);
			queue.add(n.right);
		}
		return keys;
	}

	// Do not modify "toString"
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int key : levelOrder())
			sb.append(key + " ");
		return sb.toString();
	}

	public void prettyPrint() {
		if (root == null)
			System.out.println("<EMPTY>");
		else
			prettyPrintHelper(root, "");
	}

	private void prettyPrintHelper(Node n, String indent) {
		if (n != null) {
			System.out.println(indent + n.key);
			indent += "    ";
			prettyPrintHelper(n.left, indent);
			prettyPrintHelper(n.right, indent);
		}
	}

	// create a tree from a string
	public static IntTree fromString(String s) {
		return fromStrings(s.split(" "));
	}

	// create a tree from an array of strings
	public static IntTree fromStrings(String[] keys) {
		IntTree set = new IntTree();
		for (String s : keys) {
			int splitPoint = s.indexOf(":");
			int key = Integer.parseInt(s.substring(0, splitPoint));
			String path = s.substring(splitPoint + 1);
			set.put(key, path);
		}
		return set;
	}


	public static void main(String[] args) {
		System.out.println("**********************");
		int[] a = { 0, 7, 4, 2, 2, 5, 0, 9, 0, 0, 2 }; // tree from HW3.pdf page 2
		IntTree s = new IntTree();
		s.setElementsUsingArray(a);
		StdOut.println("height is " + s.height()); // should say 3
		StdOut.println("sizeOdd is " + s.sizeOdd()); // should say 3

		IntTree s2 = IntTree.fromString("7: 4:l 2:r 2:ll 5:lr 9:rr 2:lrl"); // tree from HW3.pdf page 2
		StdOut.println("height is " + s2.height()); // should say 3
		StdOut.println("sizeOdd is " + s2.sizeOdd()); // should say 3
	}

}


