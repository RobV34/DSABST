package com.example.bst.controller;

import com.example.bst.model.BinarySearchTree;
import com.example.bst.model.TreeRecord;
import com.example.bst.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;


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
    @ResponseBody
    public TreeRecord processNumbers(@RequestParam("numbers") List<Integer> numbers) {
        return bstService.processNumbers(numbers);
    }

    @GetMapping("/previousTrees")
    public String showPreviousTreesPage(Model model) {
        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previousTrees";
    }
}




