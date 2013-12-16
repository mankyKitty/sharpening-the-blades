
class BinaryTree {

  struct Tree {
    int value = 0;
    Tree* left = 0;
    Tree* right = 0;

    bool right_is_empty();
    bool left_is_empty();
  };

  Tree* root = new Tree;

  void insert(int value, Tree* root);
  bool contains(int value, Tree* root);

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

bool BinaryTree::Tree::left_is_empty() {
  return left == 0;
}

bool BinaryTree::Tree::right_is_empty() {
  return right == 0;
}

void BinaryTree::pacman(Tree* root) {
  if (!root->left_is_empty()) {
    pacman(root->left);
  }
  if (!root->right_is_empty()) {
    pacman(root->right);
  }
  delete root;
}

bool BinaryTree::contains(int value) {
  return contains(value, root);
}

bool BinaryTree::contains(int value, Tree* root) {
  if (value == root->value) {
    return true;
  }
  if (value < root->value && !root->left_is_empty()) {
    return contains(value, root->left);
  }
  if (value > root->value && !root->right_is_empty()) {
    return contains(value, root->right);
  }
  return false;
}

void BinaryTree::insert(int value) {
  insert(value, root);
}

void BinaryTree::insert(int value, Tree* root) {
  if (value > root->value) {
    if (root->right_is_empty()) {
      Tree* tmp = new Tree;
      tmp->value = value;
      root->right = tmp;
    }
    else {
      insert(value, root->right);
    }
  }
  else {
    if (root->left_is_empty()) {
      Tree* tmp = new Tree;
      tmp->value = value;
      root->left = tmp;
    }
    else {
      insert(value, root->left);
    }
  }
}
