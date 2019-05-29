package com.BigId.model;

public class TextPart {

    public static final TextPart EMPTY = new TextPart();

    public static final int FULL_SIZE = 1000;

    private static final int ZERO_SIZE = 0;

    private final int startingLineOffset;

    private final String content;

    private final int size;

    private TextPart() {
        this.startingLineOffset = ZERO_SIZE;
        this.size = ZERO_SIZE;
        this.content = "";
    }

    private TextPart(String content, int startingLineOffset) {
        this.content = content;
        this.size = startingLineOffset % FULL_SIZE != ZERO_SIZE ? startingLineOffset % FULL_SIZE : FULL_SIZE;
        this.startingLineOffset = startingLineOffset - size;
    }

    public static TextPart of(String content, int startingLineOffset) {
        if (content != null && !content.isEmpty()) {
            return new TextPart(content, startingLineOffset);
        }
        return EMPTY;
    }

    public int getStartingLineOffset() {
        return startingLineOffset == ZERO_SIZE ? 1 : startingLineOffset;
    }

    public String getContent() {
        return content;
    }

}
