package com.crio.jukebox.entities;

//import java.util.List;

//import java.util.List;

public class Songs extends BaseEntity {
    String artist;
    String songName;
    String songId;
    String albumName;
    String artistList;
    String genre;
    State state;

    // Id auto increment in SongRepository.java class
    public Songs(String songId, String songName, String genre, String albumName, String artist, String artistGroup) {
        this.songId = songId;
        this.artist = artist;
        this.genre = genre;
        this.songName = songName;
        this.albumName = albumName;
        this.artistList = artistGroup;
        this.state = State.UNPLAYED;
    }

    @Override
    public String getId() {
        return songId;
    }

    public State getState() {
        return state;
    }

    public String getArtistName() {
        return artist;
    }

    public String getAlbum() {
        return albumName;
    }

    
    public String getSongName() {
        return songName;
    }

    public String getSongIdbyName(String songName) {
        return this.songId;
    }

    public String getArtistGroup()
    {
        return artistList;
    }

    public String getGenre()
    {
        return genre;
    }
            @Override
    public String toString() {
        return "\n Song: " + songName + "\n Genre: " + genre + "\n Artist: " + artist +"\n Album: "+albumName
                + "\n Artist Group: " + artistList;
    }

    public void changeState(State state) {
        this.state = state;
    }
}
