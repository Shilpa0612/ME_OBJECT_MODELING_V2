package com.crio.jukebox.entities;

import java.util.List;
import com.crio.jukebox.exceptions.SongAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayListTest {
    
    @Test
    @DisplayName("Create PlayList")
    public void createPlayLIstTest()
    {
        PlayList playList1 = new PlayList("1", "playListName1", "1", List.of("1"));
        PlayList playList2 = new PlayList("1", "playListName1", "1", List.of("1"));
        Assertions.assertEquals(playList1.getId(), playList2.getId());
        Assertions.assertEquals(playList1.getPlayListName(), playList2.getPlayListName());
        Assertions.assertEquals(playList1.getCreatedBy(), playList2.getCreatedBy());
        Assertions.assertEquals(playList1.getSongsList(), playList2.getSongsList());
    }

    @Test
    @DisplayName("Add Song To PlayList SongAlreadyExist")
    public void addSongToPlayListSongAlreadyExistTest()
    {
        PlayList playlist = new PlayList("1", "Name1", "1", List.of("1"));
        Songs songs = new Songs("1", "name", "genre", "albumName1", "artist1", "artistGroup1");
        Assertions.assertThrows(SongAlreadyExistException.class,
                () -> playlist.addSongToPLayList(songs));
    }
    
}
