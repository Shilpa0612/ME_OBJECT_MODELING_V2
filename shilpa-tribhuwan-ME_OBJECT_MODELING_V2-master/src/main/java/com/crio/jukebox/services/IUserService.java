package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;

import java.util.List;

public interface IUserService {
        public User create(String name);

        public List<User> getAllUser();

}
