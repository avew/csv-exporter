package io.github.avew;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvExport {

    private static final char DEFAULT_SEPARATOR = ';';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }


    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value, char separator) {

        String result = value;

        if (result == null) {
            return "";
        }

        if (separator == ';')
            if (result.contains("\"")) {
                result = result.replace("\"", "\"\"");
            }

        return result;

    }

    public static void writeLine(Writer w,
                                 List<String> values,
                                 char separators,
                                 char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {

            if (value != null) {
                if (separators == ',')
                    if (value.contains(",")) {
                        value = "\"" + value + "\"";
                    }
            }

            if (!first) {
                sb.append(separators);
            }

            if (customQuote == ' ') {
                sb.append(followCVSformat(value, separators));
            } else {
                sb.append(customQuote).append(followCVSformat(value, separators)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());

    }

}
