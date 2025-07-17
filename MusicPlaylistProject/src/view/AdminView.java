package view;

import service.AdminService;
import service.SearchStatisticsService;
import model.User;
import model.Music;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private AdminService adminService;
    private SearchStatisticsService statsService;
    private Scanner scanner = new Scanner(System.in);

    public AdminView(AdminService adminService, SearchStatisticsService statsService) {
        this.adminService = adminService;
        this.statsService = statsService;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("=== 관리자 메뉴 ===");
            System.out.println("1. 회원 목록 조회");
            System.out.println("2. 전체 인기 검색 확인");
            System.out.println("3. 음원 데이터 추가");
            System.out.println("4. 음원 데이터 수정");
            System.out.println("5. 음원 데이터 삭제");
            System.out.println("6. 로그아웃");
            int sel = scanner.nextInt(); scanner.nextLine();
            switch (sel) {
                case 1 -> printUserList();
                case 2 -> printSearchStats();
                case 3 -> addMusicMenu();
                case 4 -> updateMusicMenu();
                case 5 -> deleteMusicMenu();
                case 6 -> { return; }
            }
        }
    }

    private void printUserList() {
        List<User> users = adminService.getAllUsers();
        System.out.println("이메일\t관리자여부\t즐겨찾기수");
        for (User user : users) {
            System.out.printf("%s\t%s\t%d\n", user.getEmail(), user.isAdmin(), user.getFavorites().size());
        }
    }

    private void printSearchStats() {
        System.out.println("=== 인기 검색어 ===");
        List<String> keywords = statsService.getPopularKeywords(10);
        for (String k : keywords) {
            System.out.println(k);
        }
    }

    private void addMusicMenu() {
        System.out.print("곡명: "); String title = scanner.nextLine();
        System.out.print("아티스트: "); String artist = scanner.nextLine();
        System.out.print("앨범: "); String album = scanner.nextLine();
        System.out.print("유튜브 링크: "); String youtube = scanner.nextLine();
        System.out.print("스포티파이 링크: "); String spotify = scanner.nextLine();
        
        // music_id는 DB에서 자동 생성됨 (임시값 0 사용)
        Music music = new Music(0, title, artist, album, youtube, spotify);
        adminService.addMusic(music);
        System.out.println("음원 추가 완료");
    }

    private void updateMusicMenu() {
        try {
            System.out.print("수정할 음원 ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("새 곡명: "); String title = scanner.nextLine();
            System.out.print("새 아티스트: "); String artist = scanner.nextLine();
            System.out.print("새 앨범: "); String album = scanner.nextLine();
            System.out.print("새 유튜브 링크: "); String youtube = scanner.nextLine();
            System.out.print("새 스포티파이 링크: "); String spotify = scanner.nextLine();
            
            Music music = new Music(id, title, artist, album, youtube, spotify);
            adminService.updateMusic(music);
            System.out.println("음원 수정 완료");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 ID 형식입니다. 숫자를 입력하세요.");
        }
    }

    private void deleteMusicMenu() {
        try {
            System.out.print("삭제할 음원 ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            adminService.deleteMusic(id);
            System.out.println("음원 삭제 완료");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 ID 형식입니다. 숫자를 입력하세요.");
        }
    }
}

