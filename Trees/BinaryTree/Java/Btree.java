/***
* Super basic Binary Tree implementation that can be used for storing Ints.
* Very useful yes? ... okay fine it isn't, but I did this from scratch without
* having to consult my own old source code or anyone else's implementation. I
* know it's not great, eat me. :)
***/
public class Btree {

	public static class Node {

		int val;
		Node left;
		Node right;

		Node(int startingValue) {
			val = startingValue;
		}
	}

	public static boolean search(int val, Node root) {
		if (root.val == val) {
			return true;
		}

		if (val < root.val && null != root.left) {
			return search(val, root.left);
		}
		if (val > root.val && null != root.right) {
			return search(val, root.right);
		}
		return false;
	}

	public static void delete(int delVal, Node root) {
		_delete(delVal, root, null);
	}

	private static void _delete(int del, Node current, Node previous) {
		if (del == current.val) {

			if (null == previous && null == current.left && null == current.right) {
				current = new Node(0); // I guess this resets the tree? O.o
			}
			else if (null == current.left && null == current.right) {
				deleteHelper(del, previous, null);
			}
			else if (null != current.left) {
				Node tmp = current.left;
				tmp.right = current.right;

				deleteHelper(tmp.val, previous, tmp);
			}
			else if (null != current.right) {
				Node tmp = current.right;

				deleteHelper(tmp.val, previous, tmp);
			}
		}
		else {
			if (del > current.val) {
				_delete(del, current.right, current);
			}
			else {
				_delete(del, current.left, current);
			}
		}
	}
	
	private static void deleteHelper(int val, Node target, Node targetValue) {
		if (val > target.val) {
			target.right = targetValue;
		}
		else {
			target.left = targetValue;
		}
	}

	public static void insert(int newVal, Node root) {

		if (newVal > root.val) {
			if (null == root.right) {
				root.right = new Node(newVal);
			}
			else if (newVal < root.right.val){
				Node n = new Node(newVal);
				n.right = root.right;
				root.right = n;
			}
			else {
				insert(newVal, root.right);
			}
		}
		else {
			if (null == root.left) {
				root.left = new Node(newVal);
			}
			else if (newVal > root.left.val) {
				Node n = new Node(newVal);
				n.left = root.left;
				root.left = n;
			}
			else {
				insert(newVal, root.left);
			}
		}
	}
}
