package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;
import com.BigId.service.Matcher;

import java.util.*;

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
        if (part != TextPart.EMPTY) {
            List<MatchingResult> results = new ArrayList<>();
            lineOffset = part.getStartingLineOffset();
            try (Scanner scanner = new Scanner(part.getContent())) {
                while (scanner.hasNextLine()) {
                    scanLine(scanner.nextLine(), results);
                }
            }
            return results;
        }
        return Collections.emptyList();
    }

    private void scanLine(String line, List<MatchingResult> results) {
        if (!line.isEmpty()) {
            int charOffset = 0;
            try (Scanner scanner = new Scanner(line).useDelimiter(" ")) {
                while (scanner.hasNext()) {
                    String rawWord = scanner.next();
                    for (String name : NAMES) {
                        if (rawWord.contains(name)) {
                            int nameFirstIndex = rawWord.lastIndexOf(name);
                            results.add(MatchingResult.of(name, lineOffset, charOffset += nameFirstIndex));
                            charOffset += rawWord.substring(nameFirstIndex).length();
                        }
                    }
                    charOffset += rawWord.length();
                }
            }
        }
    }

}
