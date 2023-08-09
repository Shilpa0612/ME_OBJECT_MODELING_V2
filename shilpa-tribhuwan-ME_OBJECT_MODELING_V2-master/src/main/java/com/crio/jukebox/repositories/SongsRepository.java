package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.exceptions.SongNotFoundException;



public class SongsRepository implements ISongsRepository {

    private final Map<String, Songs> songsMap;
    private Integer autoIncrement = 0;

    public SongsRepository() {
        songsMap = new HashMap<>();
    }

    public SongsRepository(Map<String, Songs> songsMap) {
        this.songsMap = songsMap;
    }


    @Override
    public Songs getById(String Id) {
        List<Songs> songs = getAll();
        boolean ifExist = false;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getId().equals(Id)) {
                ifExist = true;
                return songs.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Songs> getByName(String name) {
        List<Songs> songs = getAll();
        return songs.stream().filter(e -> e.getSongName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Songs save(Songs entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            // String userName = entity.getCreatedBy();
            // check if user exists
            String albumName = entity.getAlbum();
            String artistName = entity.getArtistName();
            String genre = entity.getGenre();
            String songName = entity.getSongName();
            String artistGroup = entity.getArtistGroup();
            Songs song = new Songs(Integer.toString(autoIncrement), songName, genre, albumName, artistName, artistGroup);
            songsMap.put(song.getId(), song);
            return song;
        }
        songsMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Songs> getAll() {
        return songsMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Songs entity) {
        for (Map.Entry<String, Songs> entry : songsMap.entrySet()) {
            if (entry.getKey().equals(entity.getId()))
                return true;
        }
        return false;
    }

    @Override
    public void delete(Songs entity) {
        boolean ifExists = existsById(entity);
        if (!ifExists)
            throw new SongNotFoundException("Song Id " + entity.getId() + " Not Found");
        else {
            for (Map.Entry<String, Songs> entry : songsMap.entrySet()) {
                if (entry.getKey().equals(entity.getId()))
                    songsMap.remove(entry);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        boolean ifExists = false;
        for (Map.Entry<String, Songs> entry : songsMap.entrySet()) {
            if (entry.getKey().equals(id)) {
                songsMap.remove(entry);
                ifExists = true;
            }
        }
        if (!ifExists)
            throw new SongNotFoundException("Song Id " + id + " Not Found");
    }

    @Override
    public int count() {
        return songsMap.size();
    }


}
