package io.github.avew;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvWriterResult {

    private File file;
    private String message;
    private boolean success;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Csv Writer Result")
                .append(" Is Success: ")
                .append(success)
                .append(" And Message: ")
                .append(message)
                .toString();
    }
}
