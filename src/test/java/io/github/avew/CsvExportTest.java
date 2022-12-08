package io.github.avew;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class CsvExportTest {

    private static final char SEPARATOR = ';';
    private static final String[] HEADER = {"First Name", "Last Name",};
    private static final List<Person> persons = new ArrayList<>();


    @Ignore
    @Test
    public void testWrite() throws IOException {
        DirUtil dirUtil = new DirUtil();
        String filename = UUID.randomUUID() + ".csv";
        File file = dirUtil.createFile("D:\\TMP\\temp", filename);

        persons.add(Person.builder().firstName("John").lastName("Doe").build());
        persons.add(Person.builder().firstName("Elona").lastName("Holmes").build());

        AtomicInteger index = new AtomicInteger(1);
        List<String> strings = persons.stream()
                .map(person -> {
                    int i = index.getAndIncrement();
                    return person.toCsv(i, String.valueOf(SEPARATOR));
                })
                .collect(Collectors.toList());

        CsvWriter csvWriter = new CsvWriter("Write person to csv", SEPARATOR, HEADER, strings);
        csvWriter.write(file);
        for (String message : csvWriter.getMessages()) {
            log.debug(message);
        }
        log.debug("PERCENTAGE {}", csvWriter.getPercentage());
    }


}