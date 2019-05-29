package com.BigId.model;

public class MatchingResult {

    private final String matched;

    private final int lineOffset;

    private final int charOffset;

    private MatchingResult(String matched, int lineOffset, int charOffset) {
        this.matched = matched;
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    public static MatchingResult of(String matched, int lineOffset, int charOffset) {
        return new MatchingResult(matched, lineOffset, charOffset);
    }

    @Override
    public String toString() {
        return "[lineOffset=" + lineOffset + ", charOffset=" + charOffset + "]";
    }

    public String getMatched() {
        return matched;
    }

}
