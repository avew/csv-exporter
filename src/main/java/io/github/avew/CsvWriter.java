package io.github.avew;

import java.io.File;
import java.io.FileWriter;
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
    private final List<CsvProgress> progress = new ArrayList<>();
    private List<Integer> percentages = new ArrayList<>();

    public CsvWriter(String process, char SEPARATOR, String[] HEADER, List<String> values) {
        this.process = process;
        this.SEPARATOR = SEPARATOR;
        this.HEADER = HEADER;
        this.values = values;
    }

    public CsvWriterResult write(File out) {
        CsvWriterResult build = CsvWriterResult.builder().build();
        try {
            FileWriter writer = new FileWriter(out);
            /* header */
            CsvExport.writeLine(writer, Arrays.asList(HEADER), SEPARATOR);
            int total = values.size();
            AtomicInteger index = new AtomicInteger(1);
            for (String value : values) {
                int no = index.getAndIncrement();
                int percentage = Math.round(100 * no / total);
                String message = new StringBuilder().append("PROCESS OF ").append(process).append(" COUNTER ").append(no).append(" OF ").append(total).append(" PERCENTAGE ").append(percentage).toString();
                progress.add(CsvProgress.builder()
                        .counter(no)
                        .message(message)
                        .percentage(percentage)
                        .values(Collections.singletonList(value))
                        .build());
                percentages.add(percentage);
                /* body */
                CsvExport.writeLine(writer, Collections.singletonList(value), SEPARATOR);
            }
            writer.flush();
            writer.close();

            build.setFile(out);
            build.setSuccess(true);
            build.setMessage("Write process of " + process + " is success");

        } catch (Exception e) {
            e.printStackTrace();
            build.setSuccess(false);
            build.setMessage("Write process of " + process + " is failed, cause " + e.getMessage());
        }

        return build;

    }

    public Integer getLastPercentage() {
        if (percentages.isEmpty()) return 0;
        return percentages.get(percentages.size() - 1);
    }

    public List<CsvProgress> getProgress() {
        return progress;
    }
}
