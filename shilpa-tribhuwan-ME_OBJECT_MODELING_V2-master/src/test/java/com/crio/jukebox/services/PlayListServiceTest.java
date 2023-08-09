package com.crio.jukebox.services;

import com.crio.jukebox.repositories.IUserRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongsRepository;
//import com.crio.jukebox.services.PlayListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlayList Service Test")
@ExtendWith(MockitoExtension.class)
public class PlayListServiceTest {
    
    @Mock
    IPlayListRepository playListRepositoryMock;
    @Mock
    ISongsRepository songsRepositoryMock;
    @Mock
    IUserRepository userRepositoryMock;

    @InjectMocks
    PlayListService playListService;

    @Test
    @DisplayName("Create UserNotFound")
    public void createUserTest()
    {
      when(userRepositoryMock.getById(anyString())).thenThrow(UserNotFoundException.class);
      Assertions.assertThrows(UserNotFoundException.class,
              () -> userRepositoryMock.getById(anyString()));
      
    }
    
       
    @Test
    @DisplayName("Create Method UserNotFound Exception")
    public void createTest()
    {
        PlayList playList = new PlayList("1", "name", "1", List.of("song1"));
        when(playListRepositoryMock.getById(anyString())).thenReturn(playList);
        when(userRepositoryMock.getById(anyString())).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> playListService.create("1", "name", "1", List.of("song1")));
    }

    
    @Test
    @DisplayName("Get All")
    public void getAllTest()
    {
        List<PlayList> expectedPlayList = new ArrayList<PlayList>() {
            {
                add(new PlayList("1", "playListName1", "1", null));
                add(new PlayList("2", "playListName2", "2", null));
            }
        };

        when(playListRepositoryMock.getAll()).thenReturn(expectedPlayList);
        List<PlayList> list = playListService.getAllPlayList();
        Assertions.assertEquals(expectedPlayList, list);
    }
    
    
    
}
