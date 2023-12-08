package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RenameTransformation extends Transformation {
    private List<String> wcFieldList;
    private String asKeyword;
}
