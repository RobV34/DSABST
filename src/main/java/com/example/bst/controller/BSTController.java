//package com.example.bst.controller;
//
//import com.example.bst.model.TreeRecord;
//import com.example.bst.service.BSTService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.stereotype.Controller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class BSTController {
//
//    private static final Logger logger = LoggerFactory.getLogger(BSTController.class);
//
//    @Autowired
//    private BSTService bstService;
//
//    @GetMapping("/enterNumbers")
//    public String showEnterNumbersPage() {
//        return "enterNumbers";
//    }
//
//    @PostMapping("/processNumbers")
//    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
//        logger.info("Received numbers: {}", numbers);
//        List<Integer> numberList = convertStringToList(numbers);
//        TreeRecord record = bstService.processNumbers(numberList);
//        model.addAttribute("inputNumbers", numberList);
//        model.addAttribute("treeRoot", record.getTreeStructure());
//        return "result";
//    }
//
//    @GetMapping("/previousTrees")
//    public String showPreviousTreesPage(Model model) {
//        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
//        model.addAttribute("previousTrees", previousTrees);
//        return "previousTrees";
//    }
//
//    private List<Integer> convertStringToList(String numbers) {
//        String[] numberStrings = numbers.split(",");
//        List<Integer> numberList = new ArrayList<>();
//        for (String numberString : numberStrings) {
//            numberList.add(Integer.parseInt(numberString.trim()));
//        }
//        return numberList;
//    }
//}

package com.example.bst.controller;

import com.example.bst.model.AVLTree;
import com.example.bst.model.TreeRecord;
import com.example.bst.service.BSTService;
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
        model.addAttribute("inputNumbers", record.getInputNumbers().toString());
        model.addAttribute("treeRoot", record.getTreeStructure()); // Tree root in JSON format
        model.addAttribute("treeStructure", record.getTreeStructure()); // Tree structure in JSON format
        return "result";
    }

    @GetMapping("/previousTrees")
    public String showPreviousTreesPage(Model model) {
        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previousTrees";
    }
}




