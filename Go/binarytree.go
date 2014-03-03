// This is a simple package for Golang that provides a very
// rudimentary implementation of a Binary Tree, provides insertion,
// deletion, and a search. To try to support some custom types instead
// of just limiting the usage to ints or strings, I've addded an
// interface that asks you to implement three comparison functions to
// enable the tree comparison steps. This could probably be done
// better but I'm just trying to get back into the swing of my
// favourite data structures.ma
package BinaryTree

import (
	"fmt"
)

type BTreeCompare interface {
	LessThan(other BTreeCompare) bool
	GreaterThan(other BTreeCompare) bool
	EqualTo(other BTreeCompare) bool
}

type BinaryTree struct {
	value BTreeCompare // value of this node
	left  *BinaryTree  // next smaller value
	right *BinaryTree  // next larger value
}

func (tree *BinaryTree) resetLeaf(value BTreeCompare, newChild *BinaryTree) {
	if value.GreaterThan(tree.value) {
		tree.right = newChild
	} else {
		tree.left = newChild
	}
}

func New(value BTreeCompare) *BinaryTree {
	t := new(BinaryTree)
	t.value = value
	return t
}

func (root *BinaryTree) Preorder() {
	preorder_print(root)
}

func preorder_print(root *BinaryTree) {
	if root != nil {
		fmt.Println(root.value)
		preorder(root.left)
		preorder(root.right)
	}
}

func (root *BinaryTree) Contains(value BTreeCompare) bool {
	switch {
	case value.EqualTo(root.value):
		return true
	case value.LessThan(root.value) && root.left != nil:
		return root.left.Contains(value)
	case value.GreaterThan(root.value) && root.right != nil:
		return root.right.Contains(value)
	}
	return false
}

func (root *BinaryTree) Delete(value BTreeCompare) {
	if root.Contains(value) {
		delete(value, root, nil)
	}
}

func (root *BinaryTree) Insert(value BTreeCompare) {
	switch {
	case value.GreaterThan(root.value) && root.right == nil:
		n := new(BinaryTree)
		n.value = value
		root.right = n
	case value.LessThan(root.value) && root.left == nil:
		n := new(BinaryTree)
		n.value = value
		root.left = n
	case value.GreaterThan(root.value):
		root.right.Insert(value)
	case value.LessThan(root.value):
		root.left.Insert(value)
	}
}

func delete(value BTreeCompare, current, previous *BinaryTree) {
	if value.EqualTo(current.value) {
		switch {
		case previous == nil && current.left == nil && current.right == nil:
			// Errmmmmmmm....
			current = new(BinaryTree)
		case current.left == nil && current.right == nil:
			previous.resetLeaf(value, nil)

		case current.left != nil:
			tmp := current.left
			tmp.right = current.right
			previous.resetLeaf(tmp.value, tmp)

		case current.right != nil:
			tmp := current.right
			previous.resetLeaf(current.right.value, current.right)
		}
	} else {
		switch {
		case value.LessThan(current.value) && current.left != nil:
			delete(value, current.left, current)
		case value.GreaterThan(current.value) && current.right != nil:
			delete(value, current.right, current)
		}
	}
}
