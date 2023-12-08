package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class RenameTransformation {
    private List<String> wcFieldList;
    private String asKeyword;
}
/*

json
"key": {}
 */