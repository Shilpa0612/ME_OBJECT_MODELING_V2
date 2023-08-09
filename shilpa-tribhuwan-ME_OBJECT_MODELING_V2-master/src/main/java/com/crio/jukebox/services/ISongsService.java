package com.crio.jukebox.services;

import com.crio.jukebox.entities.Songs;
import java.util.List;

public interface ISongsService {
    public Songs create(String id, String songName, String genre, String albumName,
            String artistName, String artistGroup);

    public void deleteSong(String id, String userId, List<String> songs);

    public void addSong(String id, String userId, List<String> songs);

    public String getCurrentPlayingSong(String uId);

    public String getNextSong(String uId);

    public String getPrevSong(String uId);

    public String loadData(String fileName);

    public void playSpecificSong(String userId, String songId);
}
