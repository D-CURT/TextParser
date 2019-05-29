package com.BigId;

import com.BigId.model.MatchingResult;
import com.BigId.service.impl.ReaderImpl;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<MatchingResult> results = new ReaderImpl().readFile(Paths.get("").toAbsolutePath().toString() + "\\Main\\src\\main\\resources\\input.txt");
        results.forEach(System.out::println);
    }

}
