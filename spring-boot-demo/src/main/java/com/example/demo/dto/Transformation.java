package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Transformation {
    private boolean isList;
    private List<Argument> args;
    private List<String> functions;
    private List<String> keywords;
    private List<String> other;
    private List<String> list;
}
