package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;
import com.BigId.service.MatchingService;
import com.BigId.service.Reader;
import com.BigId.service.Matcher;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {

    private final MatchingService matchingService;

    public ReaderImpl() {
        this.matchingService = new MatchingServiceImpl();
    }

    private static final ThreadLocal<Integer> startingLineOffset = new ThreadLocal<>();

    @Override
    public List<MatchingResult> readFile(String fileName) {
        List<MatchingResult> results = new ArrayList<>();
        try (LineIterator iterator = FileUtils.lineIterator(new File(fileName), "UTF-8")) {
            int count = 0;
            Matcher matcher;
            StringBuilder currentPart = new StringBuilder();
            while (iterator.hasNext()) {
                currentPart.append(iterator.nextLine());
                if (++count % TextPart.FULL_SIZE == 0) {
                    startingLineOffset.set(count);
                    matcher = matchingService.getMatcher(TextPart.of(currentPart.toString(), startingLineOffset.get()));
                    results.addAll(matcher.find());
                    currentPart.setLength(0);
                }
            }
        } catch (IOException e) {
            System.err.println("File not found: " + fileName);
        }
        return results;
    }

}
