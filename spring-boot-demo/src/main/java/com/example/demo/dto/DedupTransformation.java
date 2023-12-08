package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DedupTransformation extends Transformation {
    private Argument keepevents;
    private Argument keepempty;
    private Argument consecutive;
    private List<String> sortby;
    private Integer intValue;
    private List<String> fieldList;
    private Argument field;
}
