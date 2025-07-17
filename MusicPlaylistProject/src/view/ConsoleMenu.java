package view;

import service.*;
import model.Music;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleMenu {
    private UserService userService;
    private MusicService musicService;
    private SearchStatisticsService statsService;
    private AdminMenu adminMenu;
    private MusicView musicView;
    private AdminView adminView;
    private final Scanner scanner;

    public ConsoleMenu(UserService userService, MusicService musicService,
                       SearchStatisticsService statsService, AdminMenu adminMenu, 
                       MusicView musicView, AdminView adminView) {
        this.userService = userService;
        this.musicService = musicService;
        this.statsService = statsService;
        this.adminMenu = adminMenu;
        this.musicView = musicView;
        this.scanner = new Scanner(System.in);
        this.adminView = adminView;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("1. 로그인  2. 회원가입  3. 종료");
            int sel = scanner.nextInt(); scanner.nextLine();
            if (sel == 1) loginMenu();
            else if (sel == 2) registerMenu();
            else break;
        }
    }

    private void loginMenu() {
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        if (userService.login(email, pw)) {
            if (userService.getCurrentUser().isAdmin()) {
                adminView.showAdminMenu();
            } else {
                userMenu();
            }
        } else {
            System.out.println("로그인 실패");
        }
    }

    private void registerMenu() {
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();
        if (userService.register(email, pw, false)) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("이미 존재하는 이메일입니다.");
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("1. 음원 검색  2. 즐겨찾기 목록  3. 최근 검색어  4. 인기 검색어  5. 로그아웃");
            int sel = scanner.nextInt(); scanner.nextLine();
            if (sel == 1) searchMusicMenu();
            else if (sel == 2) printFavorites();
            else if (sel == 3) printRecentKeywords();
            else if (sel == 4) printPopularKeywords();
            else { userService.logout(); break; }
        }
    }

    private void searchMusicMenu() {
        System.out.print("검색어 입력 (제목/아티스트): ");
        String keyword = scanner.nextLine();

        if (keyword.matches("\\d+")) {
            try {
                int id = Integer.parseInt(keyword);
                Music music = musicService.getMusicById(id);
                if (music != null) {
                    musicView.printMusicDetail(music);
                    System.out.print("즐겨찾기할 음원ID 입력(건너뛰려면 엔터): ");
                    String favIdInput = scanner.nextLine();
                    if (!favIdInput.isBlank()) {
                        try {
                            int favId = Integer.parseInt(favIdInput);
                            userService.addFavorite(favId);
                            System.out.println("즐겨찾기에 추가되었습니다.");
                        } catch (NumberFormatException e) {
                            System.out.println("잘못된 ID 형식입니다.");
                        }
                    }
                } else {
                    System.out.println("해당 ID의 음원이 없습니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 ID 형식입니다.");
            }
        } else {
        	//musics = title
            List<Music> musics = musicService.searchByTitle(keyword);
            List<Music> artistResults = musicService.searchByArtist(keyword);
            
         // 중복 제거를 위해 Set 사용
            Set<Music> combinedResults = new LinkedHashSet<>(musics);
            combinedResults.addAll(artistResults);
            List<Music> results = new ArrayList<>(combinedResults);
            
            if (!results.isEmpty()) {
            	musicView.printSearchResults(results);
                statsService.recordKeyword(keyword);
                userService.addRecentKeyword(keyword);


                System.out.print("상세보기할 음원ID 입력(건너뛰려면 엔터): ");
                String detailIdInput = scanner.nextLine();
                if (!detailIdInput.isBlank()) {
                    try {
                        int detailId = Integer.parseInt(detailIdInput);
                        Music detail = musicService.getMusicById(detailId);
                        if (detail != null) {
                            musicView.printMusicDetail(detail);
                        } else {
                            System.out.println("해당 ID의 음원이 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 ID 형식입니다.");
                    }
                }

                System.out.print("즐겨찾기할 음원ID 입력(건너뛰려면 엔터): ");
                String favIdInput = scanner.nextLine();
                if (!favIdInput.isBlank()) {
                    try {
                        int favId = Integer.parseInt(favIdInput);
                        userService.addFavorite(favId);
                        System.out.println("즐겨찾기에 추가되었습니다.");
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 ID 형식입니다.");
                    }
                }
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        }
    }

    private void printFavorites() {
        List<Integer> favIds = userService.getFavorites(); // int로 변경
        if (favIds == null || favIds.isEmpty()) {
            System.out.println("즐겨찾기 없음");
            return;
        }
        System.out.println("음원ID\t제목"); // 제목 추가 출력
        for (int id : favIds) {
            Music music = musicService.getMusicById(id);
            if (music != null) {
                System.out.printf("%d\t%s\n", music.getId(), music.getTitle());
            }
        }
        System.out.print("삭제할 음원ID 입력(건너뛰려면 엔터): ");
        String delIdInput = scanner.nextLine();
        if (!delIdInput.isBlank()) {
            try {
                int delId = Integer.parseInt(delIdInput);
                userService.removeFavorite(delId);
                System.out.println("즐겨찾기에서 삭제되었습니다.");
            } catch (NumberFormatException e) {
                System.out.println("잘못된 ID 형식입니다.");
            }
        }
    }

    private void printRecentKeywords() {
        List<String> keywords = userService.getRecentKeywords();
        if (keywords == null || keywords.isEmpty()) {
            System.out.println("최근 검색어 없음");
            return;
        }
        System.out.println("최근 검색어: " + String.join(", ", keywords));
    }

    private void printPopularKeywords() {
        List<String> keywords = statsService.getPopularKeywords(5);
        if (keywords.isEmpty()) {
            System.out.println("인기 검색어 없음");
            return;
        }
        System.out.println("인기 검색어: " + String.join(", ", keywords));
    }
}

