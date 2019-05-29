package com.BigId.service;

import com.BigId.model.MatchingResult;

import java.util.List;

public interface Aggregator {

    void combineAndPrint(List<MatchingResult> results);

}
