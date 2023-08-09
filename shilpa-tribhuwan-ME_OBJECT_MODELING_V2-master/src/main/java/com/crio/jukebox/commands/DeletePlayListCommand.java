package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IPlayListService;


public class DeletePlayListCommand implements ICommand {

    IPlayListService playlistService;

    public DeletePlayListCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String msg = "";
        try {
            String playListId = tokens.get(0);
            playlistService.deletePlayListById(playListId);

        } catch (PlayListNotFoundException pe) {
            System.out.println("Playlist Not Found");
        } catch (UserNotFoundException ue) {
            System.out.println("User Not Found");
        }
        System.out.println(msg);
    }

}
