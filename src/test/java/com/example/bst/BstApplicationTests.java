package com.example.bst;

import com.example.bst.model.TreeRecord;
import com.example.bst.service.BSTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BstApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BSTService bstService;

    @BeforeEach
    void setUp() {
        // Ensure at least one tree is processed and stored before running tests
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        bstService.processNumbers(numbers);
    }

    @Test
    void testProcessNumbers() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).containsExactlyElementsOf(numbers);
    }


    @Test
    void testGetPreviousTrees() {
        List<TreeRecord> previousTrees = bstService.getPreviousTrees();
        assertThat(previousTrees).isNotEmpty();
    }

    @Test
    void testEmptyInput() {
        List<Integer> numbers = Collections.emptyList();
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).isEmpty();
        assertThat(record.getTreeStructure()).isEqualTo("null");
    }

    @Test
    void testConvertTreeToString() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        TreeRecord record = bstService.processNumbers(numbers);
        String treeStructure = record.getTreeStructure();

        String expectedStructure = """
        {
          "value": 5,
          "left": {
            "value": 3,
            "left": {
              "value": 2,
              "left": null,
              "right": null
            },
            "right": {
              "value": 4,
              "left": null,
              "right": null
            }
          },
          "right": {
            "value": 7,
            "left": {
              "value": 6,
              "left": null,
              "right": null
            },
            "right": {
              "value": 8,
              "left": null,
              "right": null
            }
          }
        }
        """;

        try {
            JsonNode expectedJson = objectMapper.readTree(expectedStructure);
            JsonNode actualJson = objectMapper.readTree(treeStructure);
            assertThat(actualJson).isEqualTo(expectedJson);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON structures", e);
        }
    }

    @Test
    void testSingleElementInput() {
        List<Integer> numbers = Collections.singletonList(5);
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).containsExactly(5);

        String expectedStructure = """
        {
          "value": 5,
          "left": null,
          "right": null
        }
        """;

        try {
            JsonNode expectedJson = objectMapper.readTree(expectedStructure);
            JsonNode actualJson = objectMapper.readTree(record.getTreeStructure());
            assertThat(actualJson).isEqualTo(expectedJson);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON structures", e);
        }
    }

    @Test
    void testDuplicateElements() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 3, 5, 7);
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).containsExactlyElementsOf(numbers);

        // Assuming the BST implementation handles duplicates in a specific manner
        // e.g., ignoring duplicates or placing them in a specific order

        String expectedStructure = """
        {
          "value": 5,
          "left": {
            "value": 3,
            "left": null,
            "right": null
          },
          "right": {
            "value": 7,
            "left": null,
            "right": null
          }
        }
        """;

        try {
            JsonNode expectedJson = objectMapper.readTree(expectedStructure);
            JsonNode actualJson = objectMapper.readTree(record.getTreeStructure());
            assertThat(actualJson).isEqualTo(expectedJson);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON structures", e);
        }
    }
    @Test
    void testBalancedTree() {
        List<Integer> numbers = Arrays.asList(4, 2, 6, 1, 3, 5, 7);
        TreeRecord record = bstService.processNumbers(numbers);
        assertThat(record.getInputNumbers()).containsExactlyElementsOf(numbers);

        String expectedStructure = """
        {
          "value": 4,
          "left": {
            "value": 2,
            "left": {
              "value": 1,
              "left": null,
              "right": null
            },
            "right": {
              "value": 3,
              "left": null,
              "right": null
            }
          },
          "right": {
            "value": 6,
            "left": {
              "value": 5,
              "left": null,
              "right": null
            },
            "right": {
              "value": 7,
              "left": null,
              "right": null
            }
          }
        }
        """;

        try {
            JsonNode expectedJson = objectMapper.readTree(expectedStructure);
            JsonNode actualJson = objectMapper.readTree(record.getTreeStructure());
            assertThat(actualJson).isEqualTo(expectedJson);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON structures", e);
        }
    }

}


