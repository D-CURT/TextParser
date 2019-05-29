package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;
import com.BigId.service.Reader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {

    private static final ThreadLocal<Integer> STARTING_LINE_OFFSET = new ThreadLocal<>();

    @Override
    public List<MatchingResult> readFile(String fileName) {
        List<MatchingResult> results = new ArrayList<>();
        try (LineIterator iterator = FileUtils.lineIterator(new File(fileName), "UTF-8")) {
            StringBuilder currentPart = new StringBuilder();
            List<TextPart> parts = new ArrayList<>();
            int count = 0;
            while (iterator.hasNext()) {
                currentPart.append(iterator.nextLine());
                if (++count % TextPart.FULL_SIZE == 0) {
                    generateTextPart(parts, currentPart, count);
                }
            }
            if (currentPart.length() > 0) generateTextPart(parts, currentPart, count);
            parts.parallelStream().forEach(textPart -> results.addAll(Matchers.getMatcher(textPart).find()));
        } catch (IOException e) {
            System.err.println("File not found: " + fileName);
        }
        return results;
    }

    private void generateTextPart(List<TextPart> parts, StringBuilder builder, int count) {
        STARTING_LINE_OFFSET.set(count);
        parts.add(TextPart.of(builder.toString(), STARTING_LINE_OFFSET.get()));
        builder.setLength(0);
    }

}
