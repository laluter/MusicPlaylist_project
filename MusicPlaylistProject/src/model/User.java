package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private boolean isAdmin;
    private List<String> favorites = new ArrayList<>();
    private List<String> recentKeywords = new ArrayList<>();

    public User(String email, String password, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getter/Setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter/Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter/Setter for isAdmin
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Getter/Setter for favorites
    public List<String> getFavorites() {
        return favorites;
    }
    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    // Getter/Setter for recentKeywords
    public List<String> getRecentKeywords() {
        return recentKeywords;
    }
    public void setRecentKeywords(List<String> recentKeywords) {
        this.recentKeywords = recentKeywords;
    }

    // 즐겨찾기/최근검색어 관리 메소드 예시
    public void addFavorite(String musicId) {
        if (!favorites.contains(musicId)) {
            favorites.add(musicId);
        }
    }

    public void removeFavorite(String musicId) {
        favorites.remove(musicId);
    }

    public void addRecentKeyword(String keyword) {
        recentKeywords.remove(keyword);
        recentKeywords.add(0, keyword);
        if (recentKeywords.size() > 5) {
            recentKeywords.remove(5);
        }
    }
}
