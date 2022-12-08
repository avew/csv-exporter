package io.github.avew;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvWriter {

    private String process = "CSV Write";
    private char SEPARATOR = ';';
    private final String[] HEADER;
    private final List<String> values;
    private final List<Integer> percentages = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    public CsvWriter(String process, char SEPARATOR, String[] HEADER, List<String> values) {
        this.process = process;
        this.SEPARATOR = SEPARATOR;
        this.HEADER = HEADER;
        this.values = values;
    }

    public File write(File out) throws IOException {
        FileWriter writer = new FileWriter(out);
        CsvExport.writeLine(writer, Arrays.asList(HEADER), SEPARATOR);
        int total = values.size();
        AtomicInteger index = new AtomicInteger(1);
        for (String value : values) {
            int no = index.getAndIncrement();
            int percentage = Math.round(100 * no / total);
            percentages.add(percentage);
            String message = new StringBuilder().append("PROCESS OF ").append(process).append(" INDEX ").append(no).append(" OF ").append(total).append(" PERCENTAGE ").append(percentage).toString();
            messages.add(message);
            CsvExport.writeLine(writer, Collections.singletonList(value), SEPARATOR);
        }
        writer.flush();
        writer.close();
        return out;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Integer getLastPercentage() {
        if (percentages.isEmpty()) return 0;
        return percentages.get(percentages.size() - 1);
    }

    public List<Integer> getPercentages() {
        return percentages;
    }

    public List<String> getValues() {
        return values;
    }
}
