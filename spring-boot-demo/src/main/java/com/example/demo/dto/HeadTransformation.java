package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeadTransformation extends Transformation {

    @Data
    public static class HeadFunctions {
        private Argument abs;
        private Argument avg;
        private Argument lookup;
        private Argument trim;
        private Argument num;
        private Argument round;
    }

}
