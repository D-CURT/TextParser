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
        if (matched != null && !matched.isEmpty()) {
            return new MatchingResult(matched, lineOffset, charOffset);
        }
        return null;
    }

    @Override
    public String toString() {
        return "[lineOffset=" + lineOffset + ", charOffset=" + charOffset + "]";
    }

    public String getMatched() {
        return matched;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public int getCharOffset() {
        return charOffset;
    }

}
