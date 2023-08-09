package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlayListRepository implements IPlayListRepository {

    private final Map<String, PlayList> playListMap;
    private Integer autoIncrement = 0;

    public PlayListRepository() {
        playListMap = new HashMap<>();
    }

    public PlayListRepository(Map<String, PlayList> playListMap) {
        this.playListMap = playListMap;
    }

    @Override
    public List<PlayList> getAll() {
        
        List<PlayList> list = playListMap.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList());
        if(list == null)
            throw new PlayListNotFoundException("Play List Not Found");
        else
            return list;

    }

    @Override
    public List<PlayList> getByName(String name) {
        List<PlayList> playLists = getAll();
        return playLists.stream().filter(e -> e.getPlayListName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public PlayList getById(String Id) {
        boolean ifExist = false;
        List<PlayList> playLists = getAll();
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getId().equals(Id)) {
                ifExist = true;
                return playLists.get(i);
            }
        }
        if (!ifExist)
            throw new SongNotFoundException("Song " + Id + " Id Not Found!!");
        return null;
    }

    @Override
    public PlayList save(PlayList entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            // String userName = entity.getCreatedBy();
            // check if user exists
            PlayList playList = new PlayList(Integer.toString(autoIncrement),
                    entity.getPlayListName(), entity.getCreatedBy(),entity.getSongsList());
            playListMap.put(playList.getId(), playList);
            return playList;
        }
        playListMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean existsById(PlayList entity) {
        for (Map.Entry<String, PlayList> entry : playListMap.entrySet()) {
            if (entry.getKey().equals(entity.getId()))
                return true;
        }
        return false;
    }

    @Override
    public void delete(PlayList entity) {
        boolean ifExists = existsById(entity);
        if (!ifExists)
            throw new PlayListNotFoundException("PlayList Id " + entity.getId() + " Not Found");
        else {
            for (Map.Entry<String, PlayList> entry : playListMap.entrySet()) {
                if (entry.getKey().equals(entity.getId()))
                    playListMap.remove(entry);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        boolean ifExists = false;
        for (Map.Entry<String, PlayList> entry : playListMap.entrySet()) {
            if (entry.getKey().equals(id)) {
                playListMap.remove(entry);
                ifExists = true;
            }
        }
        if (!ifExists)
            throw new PlayListNotFoundException("PlayList Id " + id + " Not Found");
    }

    

    
}
