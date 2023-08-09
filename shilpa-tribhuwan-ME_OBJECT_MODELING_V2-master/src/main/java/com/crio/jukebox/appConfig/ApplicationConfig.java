package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlayListCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongsRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongsRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.ISongsService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.SongsService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {
    private final IPlayListRepository playListRepository = new PlayListRepository();
    private final ISongsRepository songsRepository = new SongsRepository();
    private final IUserRepository userRepository = new UserRepository();

    private final IPlayListService playListService = new PlayListService(playListRepository, songsRepository,userRepository);
    private final ISongsService songsService = new SongsService(songsRepository, playListRepository,playListService);
    private final IUserService userService = new UserService(userRepository);

    private final ICommand loadDataCommand = new LoadDataCommand(songsService);
    private final ICommand createPlaylistCommand = new CreatePlayListCommand(playListService);
    private final ICommand createUserCommand = new CreateUserCommand(userService);

    private final ICommand deletePlaylistCommand = new DeletePlayListCommand(playListService);
    private final ICommand modifyPlaylistCommand = new ModifyPlayListCommand(playListService, songsService,playListRepository);
    private final ICommand playPlaylistCommand = new PlayPlayListCommand(playListService);
    private final ICommand playSongCommand = new PlaySongCommand(playListService,songsService,songsRepository);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlaylistCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        return commandInvoker;
    }

}
