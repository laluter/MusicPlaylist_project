package view;

import model.Music;
import java.util.List;

public class MusicView {
    // F-04: 검색 결과 출력
    public void printSearchResults(List<Music> musics) {
        System.out.println("ID\t제목\t아티스트\t앨범");
        for (Music music : musics) {
            System.out.printf("%s\t%s\t%s\t%s\n",
                music.getId(), music.getTitle(), music.getArtist(), music.getAlbum());
        }
    }
    // F-05: 음원 상세정보 및 링크 출력
    public void printMusicDetail(Music music) {
        System.out.println("=== 음원 상세 정보 ===");
        System.out.println("제목: " + music.getTitle());
        System.out.println("아티스트: " + music.getArtist());
        System.out.println("앨범: " + music.getAlbum());
        System.out.println("유튜브 링크: " + music.getYoutubeLink());
        System.out.println("스포티파이 링크: " + music.getSpotifyLink());
        System.out.println("====================");
    }
}
