package com.BigId.service;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;

import java.util.List;

public interface Matcher {

    List<MatchingResult> find();

    Matcher applyFor(TextPart part);

}
