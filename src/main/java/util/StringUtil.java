package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class StringUtil {
    public static String escape(String input) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        StringBuilder builder = new StringBuilder();
        boolean isInSingleQuotes = false;
        for (char c : input.toCharArray()) {
            if (c == '\'') {
                isInSingleQuotes = !isInSingleQuotes;
            } else {
                if (c == ' ' && !isInSingleQuotes) {
                    if (!builder.isEmpty()) {
                        stringJoiner.add(builder.toString());
                        builder.setLength(0);
                    }
                } else {
                    builder.append(c);
                }
            }
        }

        if (!builder.isEmpty()) {
            stringJoiner.add(builder.toString());
        }

        return stringJoiner.toString();
    }

    public static List<String> escapeToList(String input) {
        List<String> stringList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean isInSingleQuotes = false;
        for (char c : input.toCharArray()) {
            if (c == '\'') {
                isInSingleQuotes = !isInSingleQuotes;
            } else {
                if (c == ' ' && !isInSingleQuotes) {
                    if (!builder.isEmpty()) {
                        stringList.add(builder.toString());
                        builder.setLength(0);
                    }
                } else {
                    builder.append(c);
                }
            }
        }

        if (!builder.isEmpty()) {
            stringList.add(builder.toString());
        }

        return stringList;
    }
}
