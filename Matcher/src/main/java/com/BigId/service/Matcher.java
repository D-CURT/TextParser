package com.BigId.service;

import com.BigId.model.MatchingResult;
import com.BigId.model.TextPart;

import java.util.List;
import java.util.concurrent.Callable;

public interface Matcher extends Callable<List<MatchingResult>> {

    List<MatchingResult> find();

}
