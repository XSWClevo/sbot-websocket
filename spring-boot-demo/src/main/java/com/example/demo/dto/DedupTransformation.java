package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DedupTransformation extends Transformation {

    @Data
    public static class DedupArgs {
        private Argument keepevents;
        private Argument keepempty;
        private Argument consecutive;
    }

}
