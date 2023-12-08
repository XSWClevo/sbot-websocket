package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddTotals extends Transformation {
    private boolean row;
    private boolean col;
    private Argument labelfield;
    private String label;
    private Argument fieldname;
    private List<String> fieldList;

    // Getters and Setters
}