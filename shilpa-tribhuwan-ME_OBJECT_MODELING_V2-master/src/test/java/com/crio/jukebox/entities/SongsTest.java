package com.crio.jukebox.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongsTest {
  
    @Test
    @DisplayName("Create Song Entity Get Id")
    public void createSongTest()
    {
        Songs song1 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Songs song2 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Assertions.assertEquals(song1.getId(), song2.getId());
    }
    
    @Test
    @DisplayName("Create Song Entity Get Name")
    public void createSongGetNameTest() {
        Songs song1 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Songs song2 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Assertions.assertEquals(song1.getSongName(), song2.getSongName());
    }

    @Test
    @DisplayName("Create Song Entity Get Genre")
    public void createSongGetGenreTest() {
        Songs song1 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Songs song2 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Assertions.assertEquals(song1.getGenre(), song2.getGenre());
    }

    @Test
    @DisplayName("Create Song Entity Get Album Name")
    public void createSongGetAlbumNameTest() {
        Songs song1 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Songs song2 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Assertions.assertEquals(song1.getAlbum(), song2.getAlbum());
    }

    @Test
    @DisplayName("Create Song Entity Get Artist Name and Artist Group")
    public void createSongArtistsTest() {
        Songs song1 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Songs song2 = new Songs("1", "songName1", "genre", "albumName1", "artist1", "artistGroup");
        Assertions.assertEquals(song1.getArtistName(), song2.getArtistName());
        Assertions.assertEquals(song1.getArtistGroup(), song2.getArtistGroup());
    }
}
