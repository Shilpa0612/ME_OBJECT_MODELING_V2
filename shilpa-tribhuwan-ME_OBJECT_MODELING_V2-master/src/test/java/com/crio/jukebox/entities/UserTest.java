package com.crio.jukebox.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.mockito.Mock;

public class UserTest {
    
    @Test
    @DisplayName("create user Id")
    public void createUserTest()
    {
        User expectedUser = new User("1", "name");
        User actual = new User("1", "name");
        Assertions.assertEquals(expectedUser.getId(), actual.getId());
    }

    @Test
    @DisplayName("create user getName")
    public void createUserGetNameTest() {
        User expectedUser = new User("1", "name");
        User actual = new User("1", "name");
        Assertions.assertEquals(expectedUser.getUserName(), actual.getUserName());
    }
    
    @Test
    @DisplayName("Create User toString( )")
    public void createUserToString()
    {
        User expectedUser = new User("1", "name");
        String expectedString = expectedUser.toString();
        User actual = new User("1", "name");
        String actualString = actual.toString();
        Assertions.assertEquals(expectedString,actualString);
    }
}
