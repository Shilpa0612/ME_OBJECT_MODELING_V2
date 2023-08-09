package com.crio.jukebox.services;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.User;

import java.util.List;

public interface IPlayListService {
    public PlayList create(String Id, String createdBy, List<String> songsList);

    public PlayList create(String playListId, String name, String userId, List<String> songsList);

    public List<PlayList> getAllPlayList();

    public void deleteSongFromPlayList(Songs song, PlayList playListName);

    public void deletePlayList(PlayList playlist);

    public void deletePlayListById(String playListId);

    public PlayList getCurrentPlayingPlayList(String uId);

    public void playPlayList(String uId, String playListId);

    public void addSongToPlayList(String song, String playListName);
}
