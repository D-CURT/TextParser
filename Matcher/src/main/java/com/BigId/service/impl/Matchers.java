package com.BigId.service.impl;


import com.BigId.model.TextPart;
import com.BigId.service.Matcher;

public class Matchers{

    private static final ThreadLocal<Matcher> MATCHER = ThreadLocal.withInitial(MatcherImpl::new);

    public static Matcher getMatcher(TextPart part) {
        MATCHER.set(new MatcherImpl(part));
        return MATCHER.get();
    }

}
