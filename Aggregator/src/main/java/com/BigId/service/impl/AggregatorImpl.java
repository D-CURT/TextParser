package com.BigId.service.impl;

import com.BigId.model.MatchingResult;
import com.BigId.service.Aggregator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AggregatorImpl implements Aggregator {

    @Override
    public void aggregateAndPrint(List<MatchingResult> results) {
        Map<String, List<MatchingResult>> preparedResults = results.stream()
                .collect(Collectors.groupingBy(MatchingResult::getMatched));
        preparedResults.forEach((key, value) ->
                System.out.println(key + " --> " + value));
    }

}
