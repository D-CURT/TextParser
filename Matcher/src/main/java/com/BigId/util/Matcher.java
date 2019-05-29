package com.BigId.util;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;

import java.util.*;

public class Matcher {

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

    public Matcher() {
        this.part = TextPart.EMPTY;
    }

    private Matcher(TextPart part) {
        this.part = part;
    }

    public List<MatchingResult> find() {
        if (part != TextPart.EMPTY) {
            List<MatchingResult> results = new ArrayList<>();
            lineOffset = part.getStartingLineOffset();
            try (Scanner scanner = new Scanner(part.getContent())) {
                while (scanner.hasNext()) {
                    scanLine(scanner.next(), results);
                }
            }
            return results;
        }
        return Collections.emptyList();
    }

    public Matcher applyFor(TextPart part) {
        return new Matcher(part);
    }

    private void scanLine(String line, List<MatchingResult> results) {
        lineOffset++;
        if (!line.isEmpty()) {
            int charOffset = 0;
            try (Scanner scanner = new Scanner(line).useDelimiter("[\\s]+")) {
                while (scanner.hasNext()) {
                    String word = handleWord(scanner.next());
                    for (String name : NAMES) {
                        if (name.equals(word)) {
                            results.add(MatchingResult.of(name, lineOffset, charOffset));
                        }
                    }
                    charOffset += word.length();
                }
            }
        }
    }

    private String handleWord(String word) {
        return word.replaceAll("[!@#№\\$%\\^&\\*\\(\\)_\\-=\\+}{\\]\\[|\\/\":;?.,><`~]*", "");
    }

}
