//package com.example.bst.service;
//
//import com.example.bst.model.AVLTree;
//import com.example.bst.model.TreeNode;
//import com.example.bst.model.TreeRecord;
//import com.example.bst.repository.TreeRecordRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.List;
//
//@Service
//public class BSTService {
//
//    @Autowired
//    private TreeRecordRepository treeRecordRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    public TreeRecord processNumbers(List<Integer> numbers) {
//        AVLTree avlTree = new AVLTree();
//        for (int number : numbers) {
//            avlTree.insert(number);
//        }
//
//        TreeRecord record = new TreeRecord();
//        record.setInputNumbers(numbers);
//        try {
//            record.setTreeStructure(objectMapper.writeValueAsString(avlTree.getRoot()));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        treeRecordRepository.save(record);
//        return record;
//    }
//
//    public List<TreeRecord> getPreviousTrees() {
//        return treeRecordRepository.findAll();
//    }
//}



package com.example.bst.service;

import com.example.bst.model.AVLTree;
import com.example.bst.model.TreeNode;
import com.example.bst.model.TreeRecord;
import com.example.bst.repository.TreeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class BSTService {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public TreeRecord processNumbers(List<Integer> numbers) {
        AVLTree avlTree = new AVLTree();
        for (int number : numbers) {
            avlTree.insert(number);
        }

        TreeRecord record = new TreeRecord();
        record.setInputNumbers(numbers);
        try {
            record.setTreeStructure(objectMapper.writeValueAsString(avlTree.getRoot()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        treeRecordRepository.save(record);
        return record;
    }

    public List<TreeRecord> getPreviousTrees() {
        return treeRecordRepository.findAll();
    }
}
