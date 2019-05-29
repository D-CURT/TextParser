package com.BigId.service;

import com.BigId.model.MatchingResult;

import java.util.List;

public interface Aggregator {

    void aggregateAndPrint(List<MatchingResult> results);

}
