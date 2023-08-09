package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import com.crio.jukebox.exceptions.SongAlreadyExistException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlayList extends BaseEntity {
    String playListId;
    String playListName;
    String createdBy;
    List<String> songsList = new ArrayList<>();
    State state;
    Queue<Songs> nextSongs;

    // autoIncrement playListId in playListRepository.java
    public PlayList(String id, String playListName, String createdBy, List<String> songs) {
        this.playListId = id;
        this.playListName = playListName;
        this.songsList = songs;
        this.createdBy = createdBy;
        this.state = State.UNPLAYED;
    }

    public PlayList(String id, String userId,List<String> songs) {
        this.playListId = id;
        this.songsList = songs;
        this.createdBy = userId;
        this.state = State.UNPLAYED;
    }

    public List<String> getSongsList() {
        return songsList;
    }

    private void setSongsList(List<String> songsList) {
        this.songsList.addAll(songsList);
    }

    public String getSongsListToString() {
        StringBuffer str = new StringBuffer("");
        for (String songId : songsList) {
            str.append(songId + " ");
        }
        return str.toString();
    }

    @Override
    public String getId() {
        return playListId;
    }

    public String getPlayListName() {
        return playListName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "\n PLayList: " + playListName + "\n Created By: " + createdBy + "\n Songs : "
                + getSongsListToString();
    }

    // change state of playlist

    public void addSongToPLayList(Songs songs) {
        if (this.songsList.contains(songs.getId()))
            throw new SongAlreadyExistException(
                    "Song " + songs.getSongName() + " Already exists in playList!!");
        else {
            this.songsList.add(songs.getId()+"");
            System.out.println(this.toString());
        }
    }

    public void removeSongFromPLayList(Songs songs) {
        if (!songsList.contains(songs))
            throw new SongNotFoundException(
                    "Song " + songs.getSongName() + " Not Found in PlayList!!");
        else {
            this.songsList.remove(songs);
            System.out.println(this.toString());
        }
    }

    public State getState() {
        return state;
    }

    public String changeState(State state) {
        this.state = state;
        String firstSongId = songsList.get(0);
        System.out.println("Queue: " + nextSongs.toString());
        return firstSongId;
    }

}
