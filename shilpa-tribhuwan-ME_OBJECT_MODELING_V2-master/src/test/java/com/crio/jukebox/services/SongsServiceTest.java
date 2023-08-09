package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongAlreadyExistException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongsRepository;
import com.crio.jukebox.services.SongsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("SongsServiceTest")
@ExtendWith(MockitoExtension.class)
public class SongsServiceTest {

    @Mock
    ISongsRepository songsRepositoryMock;
    @Mock
    IPlayListRepository playListRepositoryMock;

    @InjectMocks
    SongsService songsService;
    

    @Test
    @DisplayName("Create Song")
    public void createSongTest()
    {
        Songs expectedSong = new Songs("1", "Song Name", "Genre","Album","Artist Name", "Artist1#Artist2");
        when(songsRepositoryMock.save(any(Songs.class))).thenReturn(expectedSong);
        Songs song = songsService.create("1", "Song Name", "Genre", "Album", "Artist Name", "Artist1#Artist2");

        verify(songsRepositoryMock, times(1)).save(any(Songs.class));

    }

    @Test
    @DisplayName("Delete Song Return PlayListNotFoundException")
    public void deleteSongPlayListNotFoundTest()
    {
        when(playListRepositoryMock.getById(anyString())).thenReturn(null);
        Assertions.assertThrows(PlayListNotFoundException.class,
                () -> songsService.deleteSong("1", "2", List.of("11", "12")));
        verify(playListRepositoryMock, times(1)).getById(anyString());
        verify(songsRepositoryMock, times(0)).getById(anyString());

    }
    
    @Test
    @DisplayName("Song Already Exist Exception")
    public void addSongALreadyExistTest()
    {
        List <Songs> songs = new ArrayList<Songs>() {
            {
                add(new Songs("1", "songName1", "genre1", "albumName1", "artist1", "artistGroup1"));
                add(new Songs("2", "songName2", "genre2", "albumName2", "artist2", "artistGroup2"));
            }
        };
        when(songsRepositoryMock.getById("1")).thenReturn(songs.get(0));
        Assertions.assertThrows(SongAlreadyExistException.class, 
                () -> songsService.addSong("1", "1", List.of("1")));
    }
    
    @Test
    @DisplayName("PlayList Not Found Exception")
    public void addSongPLayListNotFoundTest()
    {
        List<Songs> songs = new ArrayList<Songs>() {
            {
                add(new Songs("1", "songName1", "genre1", "albumName1", "artist1", "artistGroup1"));
                add(new Songs("2", "songName2", "genre2", "albumName2", "artist2", "artistGroup2"));
            }
        };
        Songs song = new Songs("3", "songName", "genre", "albumName", "artist", "artistGroup");
        when(playListRepositoryMock.getById("1")).thenReturn(null);
        Assertions.assertThrows(PlayListNotFoundException.class,
                () -> songsService.addSong("1", "1", List.of("3")));
    }
    
    @Test
    @DisplayName("Song Not Found Exception")
    public void addSongSongNotFoundTest() {
        List<Songs> songs = new ArrayList<Songs>() {
            {
                add(new Songs("1", "songName1", "genre1", "albumName1", "artist1", "artistGroup1"));
                add(new Songs("2", "songName2", "genre2", "albumName2", "artist2", "artistGroup2"));
            }
        };
        PlayList playList = new PlayList("1", "playListName", "1", List.of("songs"));
        //Songs song = new Songs("3", "songName", "genre", "albumName", "artist", "artistGroup");
        when(playListRepositoryMock.getById("1")).thenReturn(playList);
        when(songsRepositoryMock.getById("3")).thenReturn(null);
        Assertions.assertThrows(SongNotFoundException.class,
                () -> songsService.addSong("1", "1", List.of("3")));
    }

    /*@Test
    @DisplayName("Add Song")
    public void addSong()
    {
        List<Songs> expectedSongs = new ArrayList<Songs>() {
            {
                add(new Songs("1", "songName1", "genre1", "albumName1", "artist1", "artistGroup1"));
                add(new Songs("2", "songName2", "genre2", "albumName2", "artist2", "artistGroup2"));
            }
        };
        Songs song = new Songs("3", "songName", "genre", "albumName", "artist", "artistGroup");
        PlayList playList = new PlayList("1", "playListName", "1", List.of("songs"));
    
        when(playListRepositoryMock.getById("1")).thenReturn(playList);
        when(songsRepositoryMock.getById("3")).thenReturn(song);
        songsService.addSong("1", "1", List.of("3"));
        Assertions.assertEquals(playList.getSongsList(),List.of("songs","3"));
        //verify(playListRepositoryMock, times(1)).save(playList);
        //verify(songsRepositoryMock, times(1)).save(song);
    }*/
    
    
}
