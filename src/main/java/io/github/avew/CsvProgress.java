package io.github.avew;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvProgress {

    private String message;
    private int percentage;
    @Builder.Default
    private List<String> values = new ArrayList<>();
}
