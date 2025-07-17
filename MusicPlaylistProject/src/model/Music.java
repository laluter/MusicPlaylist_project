package model;

import java.util.Objects;

public class Music {
    private int id;
    private String title;
    private String artist;
    private String album;
    private String youtubeLink;
    private String spotifyLink;

    public Music(int id, String title, String artist, String album, String youtubeLink, String spotifyLink) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.youtubeLink = youtubeLink;
        this.spotifyLink = spotifyLink;
    }

    
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public String getYoutubeLink() { return youtubeLink; }
    public String getSpotifyLink() { return spotifyLink; }

    
   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return id == music.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
