package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinTransformation extends Transformation  {
    private Argument type;
    private Argument usetime;
    private Argument earlier;
    private Argument overwrite;
    private Argument max;
    private Argument return_multivalue;
    private Argument left;
    private Argument right;
    private List<String> keywords;
    private List<String> fieldList;
    private Argument string;
    private Argument field;
}
