package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Songs;

public interface ISongsRepository {
    public List<Songs> getByName(String name);

    public Songs save(Songs entity);

    public List<Songs> getAll();

    public boolean existsById(Songs entity);

    public void delete(Songs entity);

    public void deleteById(String id);

    public Songs getById(String Id);

    public int count();
}
