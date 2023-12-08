package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class LookupTransformation extends Transformation {
    private Argument local;
    private Argument update;
    private Argument event_time_field;
    private List<String> keywords;
    private String lookupTableName;
    private Argument lookup;
    private Argument localDest;
    private Argument dest;
    private String asOutput;
    private String asOutputNew;
}
