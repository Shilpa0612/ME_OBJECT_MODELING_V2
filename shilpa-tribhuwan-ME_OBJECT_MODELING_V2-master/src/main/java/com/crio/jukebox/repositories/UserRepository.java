package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;
//import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.User;



public class UserRepository implements IUserRepository {

    private final Map<String, User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository() {
        userMap = new HashMap<String, User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement), entity.getUserName());
            userMap.put(u.getId(), u);
            return u;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> getAll() {
        return userMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(User user) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if (entry.getKey().equals(user.getId()))
                return true;
        }
        return false;
    }

    @Override
    public void delete(User entity) {
        boolean ifExists = existsById(entity);
        if (!ifExists)
            throw new UserNotFoundException("User Id " + entity.getId() + " Not Found");
        else {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                if (entry.getKey().equals(entity.getId()))
                    userMap.remove(entry);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        boolean ifExists = false;
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if (entry.getKey().equals(id)) {
                userMap.remove(entry);
                ifExists = true;
            }
        }
        if (!ifExists)
            throw new UserNotFoundException("User Id " + id + " Not Found");

    }

    @Override
    public List<User> getByName(String name) {
        List<User> usr = getAll();
        return usr.stream().filter(e -> e.getUserName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public User getById(String Id) {
        List<User> usr = getAll();
        boolean ifExist = false;
        for (int i = 0; i < usr.size(); i++) {
            if (usr.get(i).getId().equals(Id)) {
                ifExist = true;
                return usr.get(i);
            }
        }
        if (!ifExist)
            throw new UserNotFoundException("User " + Id + " Not Found!!");
        return null;
    }

    
}
