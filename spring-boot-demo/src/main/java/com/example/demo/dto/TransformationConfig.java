package com.example.demo.dto;

import lombok.Data;

@Data
public class TransformationConfig {
    private Transformation dedup;
    private Transformation fields;
    private Transformation head;
    private Transformation join;
    private Transformation lookup;
    private Transformation regex;
    private Transformation rename;
    private Transformation search;
    private Transformation sort;
    private Transformation stats;
}
