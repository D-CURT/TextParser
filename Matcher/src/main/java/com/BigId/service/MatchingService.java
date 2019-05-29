package com.BigId.service;

import com.BigId.model.TextPart;
import com.BigId.util.Matcher;

public interface MatchingService {

    Matcher getMatcher(TextPart part);

}
