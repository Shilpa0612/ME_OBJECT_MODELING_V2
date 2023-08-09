package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.CurrentPlayingSong;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.ISongsRepository;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.ISongsService;


public class PlaySongCommand implements ICommand {

    IPlayListService playlistService;
    ISongsService songsService;
    ISongsRepository songsRepository;

    public PlaySongCommand(IPlayListService playlistService, ISongsService songsService,
            ISongsRepository songsRepository) {
        this.playlistService = playlistService;
        this.songsService = songsService;
        this.songsRepository = songsRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        CurrentPlayingSong currentPlayingSong = new CurrentPlayingSong();

        if (tokens.get(2).equals("BACK")) {
            try {
                // playlistService.playPlayList(tokens.get(1), tokens.get(2));
                String songId = songsService.getCurrentPlayingSong(tokens.get(1));
                // currentPlayingSong = songsService.getCurrentPlayingSong(tokens.get(1),
                // tokens.get(2));
                Songs currentSong = songsRepository.getById(songId);
                String prevSongId = songsService.getPrevSong(tokens.get(1));
                Songs prevSong = songsRepository.getById(prevSongId);
                System.out.println("Current Song Playing");
                System.out.println("Song - " + prevSong.getSongName());
                System.out.println("Album - " + prevSong.getAlbum());

            } catch (UserNotFoundException ue) {
                System.out.println("User Not Found.");
            } catch (SongNotFoundException se) {
                System.out.println("Song Not Found in the current active playlist.");
            }
        } else if (tokens.get(2).equals("NEXT")) {
            try {
                String songId = songsService.getCurrentPlayingSong(tokens.get(1));
                Songs currentSong = songsRepository.getById(songId);
                String nextSongId = songsService.getNextSong(tokens.get(1));
                Songs nextSong = songsRepository.getById(nextSongId);
                System.out.println("Current Song Playing");
                System.out.println("Song - " + nextSong.getSongName());
                System.out.println("Album - " + nextSong.getAlbum());

            } catch (UserNotFoundException ue) {
                System.out.println("User Not Found.");
            } catch (SongNotFoundException se) {
                System.out.println("Song Not Found in the current active playlist.");
            }

        } else {
            try {
                songsService.playSpecificSong(tokens.get(1), tokens.get(2));
                Songs specificSong = songsRepository.getById(tokens.get(2));
                System.out.println("Current Song Playing");
                System.out.println("Song - " + specificSong.getSongName());
                System.out.println("Album - " + specificSong.getAlbum());
                System.out.println("Artist - " + specificSong.getArtistName());

            } catch (UserNotFoundException ue) {
                System.out.println("User Not Found.");
            } catch (SongNotFoundException se) {
                System.out.println("Given song id is not a part of the active playlist");
            }
        }


    }

}
