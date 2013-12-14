
class BinaryTree {

  struct Tree {
    int value = 0;
    Tree* left = 0;
    Tree* right = 0;
  };

  Tree* root = new Tree;

  void insert(int value, Tree* root);
  bool contains(int value, Tree* root);
  Tree* newTree(int value);

  void pacman(Tree* root);

public:
  BinaryTree(int rootValue);
  ~BinaryTree();

  void insert(int value);
  bool contains(int value);
};

BinaryTree::BinaryTree(int rootValue) {
  root->value = rootValue;
}

BinaryTree::~BinaryTree() {
  BinaryTree::pacman(root);
}

void BinaryTree::pacman(Tree* root) {
  if (root->left != 0) {
    pacman(root->left);
  }
  if (root->right != 0) {
    pacman(root->right);
  }
  delete root;
}

Tree* BinaryTree::newTree(int value) {
  Tree n;
  n.value = value;
  return &n;
}

bool BinaryTree::contains(int value) {
  return BinaryTree::contains(value, root);
}

bool BinaryTree::contains(int value, Tree* root) {
  if (value == root->value) {
    return true;
  }
  if (value < root->value && root->left != 0) {
    return BinaryTree::contains(value, root->left);
  }
  if (value > root->value && root->right != 0) {
    return BinaryTree::contains(value, root->right);
  }
  return false;
}

void BinaryTree::insert(int value) {
  BinaryTree::insert(value, root);
}

void BinaryTree::insert(int value, Tree* root) {
  if (value > root->value) {
    if (root->right == 0) {
      root->right = BinaryTree::newTree(value);
    }
    else {
      insert(value, root->right);
    }
  }
  else {
    if (root->left == 0) {
      root->left = BinaryTree::newTree(value);
    }
    else {
      insert(value, root->left);
    }
  }
}
