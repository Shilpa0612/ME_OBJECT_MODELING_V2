package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;

import java.util.List;

public interface IUserRepository{
    public List<User> getByName(String name);

    public User save(User entity);

    public List<User> getAll();

    public boolean existsById(User entity);

    public void delete(User entity);

    public void deleteById(String id);

    public User getById(String Id);
}
