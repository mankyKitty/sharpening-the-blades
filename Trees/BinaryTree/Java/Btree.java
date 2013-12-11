/***
* Super basic Binary Tree implementation that can be used for storing Ints.
* Very useful yes? ... okay fine it isn't, but I did this from scratch without
* having to consult my own old source code or anyone else's implementation. I
* know it's not great, eat me. :)
***/
public class Btree {

	public static Node root = new Node(12);

	public static void main(String[] args) {

		insert(3, root);
		insert(15, root);
		insert(1, root);
		insert(83, root);

		delete(15, root);

		if (search(15, root)) {
			System.out.println("Delete is broken!! ..or possible search.");
		}
		else {
			System.out.println("Search still works! ...I guess");
		}

		printAll(root);
	}

	public static void printAll(Node root) {

		System.out.println("Node value: " + root.val);

		if (null != root.left) {
			System.out.println("Printing Left Values");
			printAll(root.left);
		}
		if (null != root.right) {
			System.out.println("Printing Right Values");
			printAll(root.right);
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
				if (del > previous.val) {
					previous.right = null;
				}
				else {
					previous.left = null;
				}
			}
			else if (null != current.left) {
				Node tmp = current.left;
				tmp.right = current.right;

				if (tmp.val > previous.val) {
					previous.right = tmp;
				}
				else {
					previous.left = tmp;
				}
			}
			else if (null != current.right) {
				Node tmp = current.right;

				if (tmp.val > previous.val) {
					previous.right = tmp;
				}
				else {
					previous.left = tmp;
				}
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

	public static class Node {

		int val;
		Node left;
		Node right;

		Node(int startingValue) {
			val = startingValue;
		}
	}}
