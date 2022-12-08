package io.github.avew;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;

    public String toCsv(int no, String separator) {
        return new StringBuilder()
                .append(no)
                .append(separator)
                .append(firstName)
                .append(separator)
                .append(lastName)
                .toString();
    }

    public String toCsv(String separator) {
        return new StringBuilder()
                .append(firstName)
                .append(separator)
                .append(lastName)
                .toString();
    }
}
