package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.ISongsService;

public class LoadDataCommand implements ICommand {

    private final ISongsService songService;

    public LoadDataCommand(ISongsService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        System.out.println(songService.loadData(tokens.get(1)));
    }
}
