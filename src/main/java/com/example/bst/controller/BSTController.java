package com.example.bst.controller;

import com.example.bst.model.TreeRecord;
import com.example.bst.service.BSTService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BSTController {

    @Autowired
    private BSTService bstService;

    @GetMapping("/enterNumbers")
    public String showEnterNumbersPage() {
        return "enterNumbers";
    }

    @PostMapping("/processNumbers")
    public String processNumbers(@RequestParam("numbers") List<Integer> numbers, Model model) {
        TreeRecord record = bstService.processNumbers(numbers);

        // Extract root value from the JSON structure
        String treeRoot = record.getTreeStructure();
        int rootValue = extractRootValue(treeRoot);

        model.addAttribute("inputNumbers", record.getInputNumbers().toString());
        model.addAttribute("treeRoot", rootValue);
        model.addAttribute("treeStructure", prettyPrintJson(record.getTreeStructure()));
        return "result";
    }

    @GetMapping("/previousTrees")
    public String showPreviousTreesPage(Model model) {
        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previousTrees";
    }

    // Method to extract root value from JSON
    private int extractRootValue(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            return rootNode.get("value").asInt();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Method to pretty print JSON
    private String prettyPrintJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return json;
        }
    }
}
//
//
//package com.example.bst.controller;
//
//import com.example.bst.model.AVLTree;
//import com.example.bst.model.TreeRecord;
//import com.example.bst.service.BSTService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class BSTController {
//
//    @Autowired
//    private BSTService bstService;
//
//    @GetMapping("/enterNumbers")
//    public String showEnterNumbersPage() {
//        return "enterNumbers";
//    }
//
////    @PostMapping("/processNumbers")
////    public String processNumbers(@RequestParam("numbers") List<Integer> numbers, Model model) {
////        TreeRecord record = bstService.processNumbers(numbers);
////        model.addAttribute("inputNumbers", record.getInputNumbers().toString());
////        model.addAttribute("treeRoot", record.getTreeStructure()); // Tree root in JSON format
////        model.addAttribute("treeStructure", record.getTreeStructure()); // Tree structure in JSON format
////        return "result";
////    }
//
//    @PostMapping("/processNumbers")
//    public String processNumbers(@RequestParam("numbers") List<Integer> numbers, Model model) {
//        TreeRecord record = bstService.processNumbers(numbers);
//        model.addAttribute("inputNumbers", record.getInputNumbers().toString());
//        model.addAttribute("treeRoot", record.getTreeStructure()); // Assuming treeStructure is already in JSON format
//        model.addAttribute("treeStructure", record.getTreeStructure());
//        return "result";
//    }
//
//
//    @GetMapping("/previousTrees")
//    public String showPreviousTreesPage(Model model) {
//        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
//        model.addAttribute("previousTrees", previousTrees);
//        return "previousTrees";
//    }
//}




