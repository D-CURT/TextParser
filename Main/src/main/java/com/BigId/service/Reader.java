package com.BigId.service;

import com.BigId.model.MatchingResult;

import java.util.List;

public interface Reader {

    List<MatchingResult> readFile(String fileName, boolean useUrl);

}
