package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;
import com.BigId.service.Matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatcherImpl implements Matcher {

    private static final List<String> NAMES = Arrays.asList(
            "James", "John", "Robert", "Michael", "William", "David", "Richard",
            "Charles", "Joseph", "Thomas", "Christopher", "Daniel", "Paul", "Mark",
            "Donald", "George", "Kenneth", "Steven", "Edward", "Brian", "Ronald",
            "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy", "Jose",
            "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew",
            "Raymond", "Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick",
            "Peter", "Harold", "Douglas", "Henry", "Carl", "Arthur", "Ryan", "Roger"
    );

    private final TextPart part;

    private int lineOffset;

    MatcherImpl() {
        this.part = TextPart.EMPTY;
    }

    MatcherImpl(TextPart part) {
        this.part = part;
    }

    @Override
    public List<MatchingResult> call() {
        return find();
    }

    @Override
    public List<MatchingResult> find() {
        List<MatchingResult> results = new ArrayList<>();
        lineOffset = part.getStartingLineOffset();
        scanLine(part.getContent(), results);
        return results;
    }

    private void scanLine(String line, List<MatchingResult> results) {
        if (!line.isEmpty()) {
            String rawWord;
            int charOffset = 0;
            StringBuilder word = new StringBuilder();
            for (char symbol : line.toCharArray()) {
                if (symbol == ' ') {
                    if (word.length() > 0) {
                        rawWord = word.toString();
                        for (String name : NAMES) {
                            if (rawWord.contains(name)) {
                                int nameFirstIndex = rawWord.lastIndexOf(name);
                                results.add(MatchingResult.of(name, lineOffset, charOffset += nameFirstIndex));
                                charOffset += rawWord.substring(nameFirstIndex).length();
                            }
                        }
                        charOffset += rawWord.length();
                        word.setLength(0);
                    }
                    charOffset++;
                } else word.append(symbol);
            }
        }
    }

}
