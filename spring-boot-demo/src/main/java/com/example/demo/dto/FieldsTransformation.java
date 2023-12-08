package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FieldsTransformation extends Transformation {
//    { "key": "limit", "valueType": "int" },
//    { "key": "null", "valueType": "bool" },
//    { "key": "keeplast", "valueType": "bool" }

    @Data
    public static class FieldsArgs {
        private Argument limit;
        private Argument fnull;
        private Argument keeplast;
    }
}
