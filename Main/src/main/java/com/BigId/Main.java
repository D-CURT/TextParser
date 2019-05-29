package com.BigId;

import com.BigId.model.MatchingResult;
import com.BigId.service.impl.AggregatorImpl;
import com.BigId.service.impl.ReaderImpl;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String PREFIX = Paths.get("").toAbsolutePath().toString();

    public static void main(String[] args) {
        List<MatchingResult> results = new ReaderImpl().readFile(PREFIX + "\\Main\\src\\main\\resources\\input.txt");
        new AggregatorImpl().combineAndPrint(results);
    }

}
