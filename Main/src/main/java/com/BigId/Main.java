package com.BigId;

import com.BigId.model.MatchingResult;
import com.BigId.service.impl.AggregatorImpl;
import com.BigId.service.impl.ReaderImpl;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String FILE_PATH = Paths.get("").toAbsolutePath().toString() + Paths.get("\\Main\\src\\main\\resources\\input.txt");

    public static void main(String[] args) {
        List<MatchingResult> results = new ReaderImpl().readFile(FILE_PATH, false);
        new AggregatorImpl().combineAndPrint(results);
    }

}
