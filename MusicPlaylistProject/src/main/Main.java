package main;

import dao.impl.UserDaoImpl;
import dao.impl.MusicDaoImpl;
import dao.impl.FavoriteDaoImpl; 
import service.UserService;
import service.MusicService;
import service.SearchStatisticsService;
import service.AdminService;
import util.DBUtil;
import view.ConsoleMenu;
import view.AdminMenu;
import view.MusicView;
import view.AdminView;

public class Main {
    public static void main(String[] args) throws Exception {
        var conn = DBUtil.getConnection();
        // DAO 생성
        UserDaoImpl userDao = new UserDaoImpl(conn);
        MusicDaoImpl musicDao = new MusicDaoImpl(conn);
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl(conn);
        
        // 서비스 생성
        UserService userService = new UserService(userDao, favoriteDao); 
        MusicService musicService = new MusicService(musicDao);
        SearchStatisticsService statsService = new SearchStatisticsService();
        AdminService adminService = new AdminService(userDao, musicDao);
        
        // 뷰 생성
        MusicView musicView = new MusicView();
        AdminMenu adminMenu = new AdminMenu(adminService, statsService);
        AdminView adminView = new AdminView(adminService, statsService);
        
        // 콘솔 메뉴 실행
        ConsoleMenu menu = new ConsoleMenu(userService, musicService, statsService, adminMenu, musicView, adminView);
        menu.showMainMenu();
    }
}

















