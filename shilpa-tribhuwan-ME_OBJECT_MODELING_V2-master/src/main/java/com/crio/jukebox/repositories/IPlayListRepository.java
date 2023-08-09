package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.PlayList;


public interface IPlayListRepository {
    public List<PlayList> getAll();

    public List<PlayList> getByName(String name);

    public PlayList save(PlayList entity);

    public boolean existsById(PlayList entity);

    public void delete(PlayList entity);

    public void deleteById(String id);

    public PlayList getById(String Id);

}
