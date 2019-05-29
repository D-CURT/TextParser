package com.BigId.service.impl;


import com.BigId.model.TextPart;
import com.BigId.service.Matcher;

public class Matchers {

    public static Matcher getMatcher(TextPart part) {
        return new MatcherImpl(part);
    }

}
