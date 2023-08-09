package com.crio.jukebox.services;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.State;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlayListException;
import com.crio.jukebox.exceptions.NoPlayListPlayingException;
//import com.crio.jukebox.exceptions.OtherSongAlreadyPlayingException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongsRepository;
import com.crio.jukebox.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

public class PlayListService implements IPlayListService {

    private final IPlayListRepository playListRepository;
    private final ISongsRepository songsRepository;
    private final IUserRepository userRepository;

    public PlayListService(IPlayListRepository playListRepository, ISongsRepository songsRepository,
            IUserRepository userRepository) {
        this.playListRepository = playListRepository;
        this.songsRepository = songsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlayList create(String playListId,  String userId, List<String> songsIdList)
            throws UserNotFoundException, SongNotFoundException {

        User owner = userRepository.getById(userId);
        if(owner == null)
            throw new UserNotFoundException("User Not Found Exception");

        List<String> songList = new ArrayList<>();
        for (String id : songsIdList) {
            Songs song = songsRepository.getById(id);
            if(song == null)
                throw new SongNotFoundException("Song Not Found");
            else    
                songList.add(song.getId());
        }

        PlayList playList = new PlayList(playListId,  userId, songList);
        playListRepository.save(playList);
        return playList;
    }


    @Override
    public PlayList create(String playListId, String name,  String userId,
            List<String> songsList)
            throws UserNotFoundException, SongNotFoundException {

        if(playListRepository.getById(playListId) == null)
            throw new PlayListNotFoundException("Play List Not Found!!");
        PlayList playList = new PlayList(playListId, name, userId, songsList);
        if (userRepository.getById(userId) == null)
            throw new UserNotFoundException("User Not Found!!");
        playListRepository.save(playList);
        return playList;
    }

    @Override
    public List<PlayList> getAllPlayList() {
        List<PlayList> playListList = playListRepository.getAll();
        return playListList;
    }

    @Override
    public void addSongToPlayList(String song, String playListId) {
        boolean ifExists = false;
        PlayList playListEntity = playListRepository.getById(playListId);
        Songs songEntity = songsRepository.getById(song);
        /*for (PlayList playList : listOfPlayList) {
            if (playListId.equals(playList.getId())) {
                ifExists = true;
                playList.addSongToPLayList(songEntity);
            }
        }*/
        playListEntity.addSongToPLayList(songEntity);
        /*if (!ifExists)
            throw new PlayListNotFoundException("PlayList " + playListId + " Not Found!!");*/
    }

    @Override
    public void deleteSongFromPlayList(Songs song, PlayList playListName) {
        boolean ifExists = false;
        List<PlayList> listOfPlayList = playListRepository.getAll();
        for (PlayList playList : listOfPlayList) {
            if (playListName.getPlayListName().equals(playList.getPlayListName())) {
                ifExists = true;
                playList.removeSongFromPLayList(song);
            }
        }
        if (!ifExists)
            throw new PlayListNotFoundException("PlayList " + playListName + " Not Found!!");
    }

    @Override
    public void deletePlayList(PlayList playList) {
        boolean ifExists = playListRepository.existsById(playList);
        if (!ifExists)
            throw new PlayListNotFoundException(
                    "PlayList " + playList.getPlayListName() + " Not Found!!");
        playListRepository.delete(playList);
    }

    @Override
    public void deletePlayListById(String playListId) {
        playListRepository.deleteById(playListId);
    }

    // play 1st song from playlist
    @Override
    public void playPlayList(String userId, String playListId) {
        int cnt = 0;
        PlayList playList = playListRepository.getById(playListId);

        List<String> songsList = playList.getSongsList();
        if (songsList.isEmpty())
            throw new EmptyPlayListException("PlayList " + playListId + " Is Empty!!");
        String firstSong = songsList.get(0);
        playList.changeState(State.PLAYING);
        Songs songEntity = songsRepository.getById(firstSong);
        if (songEntity == null)
            throw new SongNotFoundException("Song Not Found!!");
        System.out.println(firstSong.toString());
    }

    @Override
    public PlayList getCurrentPlayingPlayList(String userId) {
        if (userRepository.getById(userId) == null)
            throw new UserNotFoundException("User Not Found!!");

        List<PlayList> playListList = playListRepository.getAll();
        List<PlayList> specificUserPlayList = new ArrayList<>();
        for (PlayList playList : playListList) {
            if (playList.getCreatedBy().equals(userId)) {
                specificUserPlayList.add(playList);
            }
        }
        for (PlayList playList : specificUserPlayList) {
            if (playList.getState().equals(State.PLAYING))
                return playList;
        }
        throw new NoPlayListPlayingException("No PlayList is Active!!");
    }
}
