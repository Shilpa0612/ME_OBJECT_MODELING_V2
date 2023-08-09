package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.ISongsService;


public class ModifyPlayListCommand implements ICommand {

    private final IPlayListService playListService;
    private final ISongsService songsService;
    private final IPlayListRepository playListRepository;

    public ModifyPlayListCommand(IPlayListService playlistService, ISongsService songsService,
            IPlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
        this.playListService = playlistService;
        this.songsService = songsService;
    }

    @Override
    public void execute(List<String> tokens) {

        int sz = tokens.size();

        List<String> listOfSongId = new ArrayList<>();
        for (int i = 4; i < sz; i++)
            listOfSongId.add(tokens.get(i));

        if (tokens.get(1).equals("ADD-SONG")) {
            try {
                songsService.addSong(tokens.get(2), tokens.get(3), listOfSongId);
            } catch (PlayListNotFoundException pe) {
                System.out.println("Playlist Not Found");
            } catch (UserNotFoundException ue) {
                System.out.println("User Not Found");
            } catch (SongNotFoundException se) {
                System.out.println("Some Requested Songs Not Available. Please try again.");
            }
        } else if (tokens.get(1).equals("DELETE-SONG")) {
            try {
                songsService.deleteSong(tokens.get(2), tokens.get(3), listOfSongId);
            } catch (PlayListNotFoundException pe) {
                System.out.println("Playlist Not Found");
            } catch (UserNotFoundException ue) {
                System.out.println("User Not Found");
            } catch (SongNotFoundException se) {
                System.out.println(
                        "Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }

        PlayList playList = playListRepository.getById(tokens.get(2));
        System.out.println("Playlist ID - " + playList.getId());
        System.out.println("Playlist Name - " + playList.getPlayListName());
        List<String> listOfNewSongId = playList.getSongsList();
        System.out.print("Song IDs - ");
        int n = listOfNewSongId.size();
        for (int i = 0; i < n - 1; i++) {
            System.out.print(listOfNewSongId.get(i) + " ");
        }
        System.out.println(listOfNewSongId.get(n - 1));
    }
}

