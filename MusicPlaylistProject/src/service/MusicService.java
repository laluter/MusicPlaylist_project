package service;

import dao.MusicDao;

import model.Music;

import java.util.Collections;
import java.util.List;

public class MusicService {
    private MusicDao musicDao;

    public MusicService(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    // F-03: 음원 검색
    public List<Music> search(String keyword) {
        try {
            return musicDao.findByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // F-04: 검색 결과 표시 (title, artist, album)
    public void printSearchResults(List<Music> musics) {
        System.out.println("검색 결과:");
        System.out.println("ID\t제목\t아티스트\t앨범");
        for (Music music : musics) {
            System.out.printf("%s\t%s\t%s\t%s\n",
                music.getId(), music.getTitle(), music.getArtist(), music.getAlbum());
        }
    }
    // 음원 상세 조회 기능 (F-05)
    public Music getMusicById(int id) {
        try {
            return musicDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 제목으로 검색 추가
    public List<Music> searchByTitle(String title) {
        try {
            return musicDao.findByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    // 제목으로 단일 조회 추가
    public Music getMusicByTitle(String title) {
        try {
            List<Music> musics = musicDao.findByTitle(title);
            return musics.isEmpty() ? null : musics.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //아티스트명으로 검색 추가
    public List<Music> searchByArtist(String artist) {
        try {
            return musicDao.findByArtist(artist);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
