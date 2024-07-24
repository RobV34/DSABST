package com.example.bst;

import com.example.bst.model.TreeRecord;
import com.example.bst.service.BSTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BstApplicationTests {

    @Autowired
    private BSTService bstService;

    @Test
    void testProcessNumbers() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).isEqualTo(numbers);
    }

    @Test
    void testGetPreviousTrees() {
        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
        assertThat(previousTrees).isNotEmpty();
    }

    @Test
    void testConvertTreeToString() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        TreeRecord record = bstService.processNumbers(numbers);
        String treeStructure = record.getTreeStructure();
        assertThat(treeStructure).isEqualTo("5(3(2(,),4(,)),7(6(,),8(,)))");
    }
}

