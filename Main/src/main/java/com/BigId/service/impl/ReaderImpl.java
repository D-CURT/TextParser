package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;
import com.BigId.service.Matcher;
import com.BigId.service.Reader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReaderImpl implements Reader {

    private static final ThreadLocal<Integer> STARTING_LINE_OFFSET = new ThreadLocal<>();

    @Override
    public List<MatchingResult> readFile(String fileName) {
        List<MatchingResult> results = new ArrayList<>();
        try (LineIterator iterator = FileUtils.lineIterator(new File(fileName), "UTF-8")) {
            StringBuilder currentPart = new StringBuilder();
            List<Matcher> matchers = new ArrayList<>();
            int count = 0;
            while (iterator.hasNext()) {
                if (++count % TextPart.FULL_SIZE == 0) {
                    generateTextPart(matchers, currentPart, count);
                }
                currentPart.append(iterator.nextLine()).append(" ");
            }
            if (currentPart.length() > 0) generateTextPart(matchers, currentPart, count);
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (Future<List<MatchingResult>> future : executorService.invokeAll(matchers)) {
                results.addAll(future.get());
            }
            executorService.shutdown();
        } catch (IOException e) {
            System.err.println("File not found: " + fileName);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Concurrency exception");
        }
        return results;
    }

    private void generateTextPart(List<Matcher> matchers, StringBuilder builder, int count) {
        STARTING_LINE_OFFSET.set(count);
        matchers.add(Matchers.getMatcher(TextPart.of(builder.toString(), STARTING_LINE_OFFSET.get())));
        builder.setLength(0);
    }

}
