package com.crio.jukebox.services;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.State;
import com.crio.jukebox.exceptions.EmptyPlayListException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongAlreadyExistException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongsRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;

public class SongsService implements ISongsService {

    private final ISongsRepository songsRepository;
    private final IPlayListRepository playListRepository;
    private final IPlayListService playListService;

    public SongsService(ISongsRepository songsRepository, IPlayListRepository playListRepository,
            IPlayListService playListService) {
        this.songsRepository = songsRepository;
        this.playListRepository = playListRepository;
        this.playListService = playListService;
    }

    @Override
    public Songs create(String id, String songName, String genre,String albumName, String artistName, String artistGroup) {
        Songs song = new Songs(id, songName, genre, albumName, artistName, artistGroup);
        songsRepository.save(song);
        return song;
    }

    @Override
    public void deleteSong(String playListId, String userId, List<String> songs) {
        // List<PlayList> listPlayList = playListRepository.getAll();
        if (playListRepository.getById(playListId) == null)
            throw new PlayListNotFoundException("PlayList " + playListId + " Not Found");

        PlayList playList = playListRepository.getById(playListId);
        List<String> songsList = playList.getSongsList();
        if(songsList.isEmpty())
            throw new EmptyPlayListException();        
        
        for (int i = 0; i < songs.size(); i++) {
            boolean found = false;
            if (songsList.contains(songs.get(i))) {
                found = true;
                Songs song = songsRepository.getById(songs.get(i));
                songsRepository.delete(song);
                songsRepository.save(song);
            }
            if (!found)
                throw new SongNotFoundException("Song Not Found!!");
        }
            

        
    }

    @Override
    public void addSong(String playListId, String userId, List<String> songs) {
        for (int i = 0; i < songs.size(); i++) {
            if (songsRepository.getById(songs.get(i)) != null)
                throw new SongAlreadyExistException("Song " + songs.get(i) + " Already Exist!!");
            else {
                PlayList playList = playListRepository.getById(playListId);
                if(playList == null)
                    throw new PlayListNotFoundException("Play List Not Found!!");
                Songs song = songsRepository.getById(songs.get(i));
                if (song == null)
                    throw new SongNotFoundException("Song Not Found!!");
                
                playList.addSongToPLayList(song);
                playListRepository.save(playList);
                songsRepository.save(song);
                System.out.println(songsRepository.toString());
            }
        }
    }

    @Override
    public String getCurrentPlayingSong(String userId) {
        PlayList playList = playListService.getCurrentPlayingPlayList(userId);
        List<Songs> songsList = songsRepository.getAll();
        for (Songs song : songsList) {
            if (song.getState().equals(State.PLAYING)) {
                return song.getId();
            }
        }
        return null;
    }


    @Override
    public String getNextSong(String userId) {
        PlayList playList = playListService.getCurrentPlayingPlayList(userId);
        String nextSong = "";
        String songId = getCurrentPlayingSong(userId);
        List<Songs> songsList = songsRepository.getAll();
        Queue<Songs> queue;
        boolean found = false;
        int i = -1;
        for (Songs song : songsList) {
            i++;
            if (song.getId().equals(songId) && found == false) {
                found = true;
                if (songsRepository.count() > i)
                    nextSong = songsList.get(i + 1).getSongName();
                else if (songsRepository.count() > 0)
                    nextSong = songsList.get(0).getSongName();
                else
                    throw new EmptyPlayListException("PlayList is Empty!!");
            }
        }
        return nextSong;// current playing song
    }

    @Override
    public String getPrevSong(String userId) {
        PlayList playList = playListService.getCurrentPlayingPlayList(userId);
        String prevSong = "";
        String songId = getCurrentPlayingSong(userId);
        List<Songs> songsList = songsRepository.getAll();
        Queue<Songs> queue;
        boolean found = false;
        int i = -1;
        for (Songs song : songsList) {
            i++;
            if (song.getId().equals(songId) && found == false) {
                found = true;
                if (i > 0)
                    prevSong = songsList.get(i - 1).getSongName();
                else if (i == 0)
                    prevSong = songsList.get(songsRepository.count() - 1).getSongName();
                else
                    throw new EmptyPlayListException("PlayList is Empty!!");
            }
        }
        return prevSong;// current playing song
    }

    @Override
    public String loadData(String fileName) {

        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");

                String id = attributes[0];
                String songName = attributes[1];
                String genre = attributes[2];
                String albumName = attributes[3];
                String artistName = attributes[4];
                String artistGroup = attributes[5];

                Songs song = new Songs(id, songName, genre, albumName,artistName,artistGroup);
                Songs returnedSong = songsRepository.save(song);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return "Songs Loaded successfully";

    }

    @Override
    public void playSpecificSong(String userId, String songId) {
        boolean ifSongPresentInCurrentPlayList = false;
        PlayList playListEntity = playListService.getCurrentPlayingPlayList(userId);
        List<String> songsList = playListEntity.getSongsList();
        for (int i = 0; i < songsList.size(); i++) {
            Songs song = songsRepository.getById(songId);
            if (songsList.get(i).equals(songId)) {
                ifSongPresentInCurrentPlayList = true;
                // Songs song = songsRepository.getById(songId);
                song.changeState(State.PLAYING);
            } else {
                if (song.getState().equals(State.PLAYING))
                    song.changeState(State.PLAYED);
            }
        }
        if (!ifSongPresentInCurrentPlayList)
            throw new SongNotFoundException("Song Not Found In Current Playing PlayList!!");
        // return null;
    }
}
