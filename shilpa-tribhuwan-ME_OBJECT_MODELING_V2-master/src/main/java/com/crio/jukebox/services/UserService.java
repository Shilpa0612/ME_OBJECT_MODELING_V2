package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String name) {

        User userInstance = userRepository.save(new User(null, name));
        return userInstance;
    }

    @Override
    public List<User> getAllUser() {

        List<User> list = userRepository.getAll();
        return list;
    }

    public List<User> getUserByName(String name) {
        List<User> users = userRepository.getByName(name);
        return users;
    }

    // Delete user

}
