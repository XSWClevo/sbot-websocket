package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegexTransformation extends Transformation {
    private Argument field;
    private Argument string;
}
