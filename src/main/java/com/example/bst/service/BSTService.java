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
        record.setTreeStructure(convertTreeToJson(bst.getRoot()));

        treeRecordRepository.save(record);
        return record;
    }

    public List<TreeRecord> getPreviousTrees() {
        return treeRecordRepository.findAll();
    }

    private String convertTreeToJson(TreeNode node) {
        if (node == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"value\": ").append(node.getValue()).append(",\n");
        sb.append("  \"left\": ").append(convertTreeToJson(node.getLeft())).append(",\n");
        sb.append("  \"right\": ").append(convertTreeToJson(node.getRight())).append("\n");
        sb.append("}");
        return sb.toString().replace("\\\"", "\"");  // Ensure proper JSON formatting
    }

}

