package service;

import dao.FavoriteDao;
import dao.UserDao;
import model.User;

import java.util.Collections;
import java.util.List;

public class UserService {
    private UserDao userDao;
    private User currentUser;
    private FavoriteDao favoriteDao;
    

    public UserService(UserDao userDao, FavoriteDao favoriteDao) {
        this.userDao = userDao;
        this.favoriteDao = favoriteDao;
    }

    // F-01: 회원가입/로그인
    public boolean login(String email, String password) {
        try {
            User user = userDao.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String email, String password, boolean isAdmin) {
        try {
            if (userDao.findByEmail(email) == null) {
                User user = new User(email, password, isAdmin);
                userDao.save(user);
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // F-02: 로그아웃
    public void logout() {
        currentUser = null;
    }

    // F-06: 즐겨찾기 추가/삭제
    public void addFavorite(int musicId) {
        if (currentUser != null) {
            try {
                favoriteDao.addFavorite(currentUser.getEmail(), musicId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFavorite(int delId) {
        if (currentUser != null) {
            try {
                favoriteDao.removeFavorite(currentUser.getEmail(), delId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getFavorites() {
        if (currentUser != null) {
            try {
                return favoriteDao.getFavorites(currentUser.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
    
    // F-08: 최근 검색어 저장/조회 (최대 5개)
    public void addRecentKeyword(String keyword) {
        if (currentUser != null) {
            List<String> keywords = currentUser.getRecentKeywords();
            keywords.remove(keyword);
            keywords.add(0, keyword);
            if (keywords.size() > 5) keywords.remove(5);
            try { 
            	userDao.update(currentUser); } 
            catch (Exception e) { 
            	e.printStackTrace(); }
        }
    }

    public List<String> getRecentKeywords() {
        return currentUser != null ? currentUser.getRecentKeywords() : null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
