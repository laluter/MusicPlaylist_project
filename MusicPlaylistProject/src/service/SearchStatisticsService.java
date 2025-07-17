package service;

import java.util.*;

public class SearchStatisticsService {
    private Map<String, Integer> keywordCount = new HashMap<>();

    public void recordKeyword(String keyword) {
        keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
    }

    public List<String> getPopularKeywords(int topN) {
        return keywordCount.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue() - e1.getValue())
            .limit(topN)
            .map(Map.Entry::getKey)
            .toList();
    }
}
