package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeadTransformation extends Transformation {
    private Argument limit;
    private Argument nullArg;
    private Argument keeplast;
    private List<String> functions;
    private List<String> keywords;
    private Integer intValue;
    private Argument boolValue;
    private List<String> andOrXorList;
    private Argument field;
    private Argument num;
    private Argument string;
}
