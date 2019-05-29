package com.BigId.service.impl;


import com.BigId.model.TextPart;
import com.BigId.service.MatchingService;
import com.BigId.util.Matcher;

public class MatchingServiceImpl implements MatchingService {

    private static final ThreadLocal<Matcher> MATCHER = ThreadLocal.withInitial(Matcher::new);

    @Override
    public Matcher getMatcher(TextPart part) {
        return MATCHER.get().applyFor(part);
    }

}
