package service;

import dao.UserDao;

import dao.MusicDao;
import model.User;
import model.Music;
import java.util.List;

public class AdminService {
    private UserDao userDao;
    private MusicDao musicDao;

    public AdminService(UserDao userDao, MusicDao musicDao) {
        this.userDao = userDao;
        this.musicDao = musicDao;
    }

    public List<User> getAllUsers() {
        try { return userDao.findAll(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public void addMusic(Music music) {
        try { musicDao.save(music); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void updateMusic(Music music) {
        try { musicDao.update(music); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteMusic(int musicId) {
        try { musicDao.delete(musicId); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
