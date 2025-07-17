package view;

import service.AdminService;
import service.SearchStatisticsService;
import model.User;
import model.Music;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private AdminService adminService;
    private SearchStatisticsService statsService;
    private Scanner scanner = new Scanner(System.in);

    public AdminMenu(AdminService adminService, SearchStatisticsService statsService) {
        this.adminService = adminService;
        this.statsService = statsService;
    }

    public void show() {
        while (true) {
            System.out.println("1. 회원 목록 2. 검색 통계 3. 음원 추가 4. 음원 수정 5. 음원 삭제 6. 로그아웃");
            int sel = scanner.nextInt(); scanner.nextLine();
            switch (sel) {
                case 1 -> printUserList();
                case 2 -> printSearchStats();
                case 3 -> addMusic();
                case 4 -> updateMusic();
                case 5 -> deleteMusic();
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
        System.out.println("인기 검색어: " + String.join(", ", statsService.getPopularKeywords(10)));
    }

    private void addMusic() {
        System.out.print("곡명: "); String title = scanner.nextLine();
        System.out.print("아티스트: "); String artist = scanner.nextLine();
        System.out.print("앨범: "); String album = scanner.nextLine();
        System.out.print("유튜브: "); String youtube = scanner.nextLine();
        System.out.print("스포티파이: "); String spotify = scanner.nextLine();
 
        Music music = new Music(0, title, artist, album, youtube, spotify);
        adminService.addMusic(music);
        System.out.println("음원 추가 완료");
    }

    private void updateMusic() {
        System.out.print("수정할 음원 ID: ");
        String idStr = scanner.nextLine();
        try {
            int id = Integer.parseInt(idStr); 
            System.out.print("새 곡명: "); String title = scanner.nextLine();
            System.out.print("새 아티스트: "); String artist = scanner.nextLine();
            System.out.print("새 앨범: "); String album = scanner.nextLine();
            System.out.print("새 유튜브: "); String youtube = scanner.nextLine();
            System.out.print("새 스포티파이: "); String spotify = scanner.nextLine();
            
            Music music = new Music(id, title, artist, album, youtube, spotify);
            adminService.updateMusic(music);
            System.out.println("음원 수정 완료");
        } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 ID 형식입니다. 숫자를 입력하세요.");
        }
    }

    private void deleteMusic() {
        System.out.print("삭제할 음원 ID: ");
        String idStr = scanner.nextLine();
        try {
            int id = Integer.parseInt(idStr); 
            adminService.deleteMusic(id);
            System.out.println("음원 삭제 완료");
        } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 ID 형식입니다. 숫자를 입력하세요.");
        }
    }
}
