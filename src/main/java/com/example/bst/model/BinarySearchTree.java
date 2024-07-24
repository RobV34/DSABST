package com.example.bst.model;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.getValue()) {
            root.setLeft(insertRec(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertRec(root.getRight(), value));
        }

        return root;
    }

    public TreeNode getRoot() {
        return root;
    }
}

