package com.example.bst.service;

import com.example.bst.model.BinarySearchTree;
import com.example.bst.model.TreeNode;
import com.example.bst.model.TreeRecord;
import com.example.bst.repository.TreeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BSTService {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    public TreeRecord processNumbers(List<Integer> numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int number : numbers) {
            bst.insert(number);
        }

        TreeRecord record = new TreeRecord();
        record.setInputNumbers(numbers);
        record.setTreeStructure(convertTreeToString(bst.getRoot()));

        treeRecordRepository.save(record);
        return record;
    }

    public List<TreeRecord> getPreviousTrees() {
        return treeRecordRepository.findAll();
    }

    private String convertTreeToString(TreeNode node) {
        if (node == null) {
            return "";
        }
        return node.getValue() + "(" + convertTreeToString(node.getLeft()) + "," + convertTreeToString(node.getRight()) + ")";
    }
}

