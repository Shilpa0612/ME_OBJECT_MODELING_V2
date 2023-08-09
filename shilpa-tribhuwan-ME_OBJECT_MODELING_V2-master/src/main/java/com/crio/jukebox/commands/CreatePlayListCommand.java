package com.crio.jukebox.commands;

import java.util.Collections;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.services.IPlayListService;


public class CreatePlayListCommand implements ICommand{

    private final IPlayListService playListService;

    public CreatePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        List <String> songs = Collections.singletonList(tokens.get(2));
        PlayList playList = new PlayList(tokens.get(0), tokens.get(1), tokens.get(3),songs);
        playListService.create(tokens.get(0), tokens.get(1), tokens.get(3),songs);
        System.out.println(playList.toString());
    }
}
