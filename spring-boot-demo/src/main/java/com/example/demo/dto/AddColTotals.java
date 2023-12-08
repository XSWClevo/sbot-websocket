package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddColTotals extends Transformation {
    private Argument labelfield;
    private String label;
    private List<String> fieldList;
}
