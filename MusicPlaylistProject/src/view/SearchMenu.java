package view;

import service.SearchStatisticsService;
import java.util.Scanner;
import service.UserService;

public class SearchMenu {
    private SearchStatisticsService statsService;
    private UserService userService;

    public SearchMenu(SearchStatisticsService statsService, UserService userService) {
        this.statsService = statsService;
        this.userService = userService;
    }

    
    // F-08: 최근 검색어 보기
    public void printRecentKeywords() {
        var keywords = userService.getRecentKeywords();
        System.out.println("최근 검색어: " + String.join(", ", keywords));
    }

    // F-09: 인기 검색어 보기
    public void printPopularKeywords() {
        var keywords = statsService.getPopularKeywords(5);
        System.out.println("인기 검색어: " + String.join(", ", keywords));
    }
}
