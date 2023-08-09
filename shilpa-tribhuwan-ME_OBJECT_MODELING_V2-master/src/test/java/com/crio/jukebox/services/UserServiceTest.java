package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private IUserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Create should return User")
    public void createShouldReturnUser()
    {
        User expectedUser = new User("1", "Kiran");
        // when(userRepositoryMock.save(any(User))).thenReturn(expectedUser);
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.create("Kiran");
        Assertions.assertEquals(expectedUser, actualUser);

        verify(userRepositoryMock, times(1)).save(any(User.class));

    }

    @Test
    @DisplayName("Get All Users")
    public void getAllUserTest()
    {
        List <User> users = new ArrayList<User>() {
            {
                add(new User("1", "XYZ"));
                add(new User("2", "ABC"));
                add(new User("3", "PQR"));
            }
        };

        List<User> expectedUserList = new ArrayList<User>() {
            {
                add(new User("1", "XYZ"));
                add(new User("2", "ABC"));
                add(new User("3", "PQR"));
            }
        };

        when(userRepositoryMock.getAll()).thenReturn(expectedUserList);
        List<User> actualUserList = userService.getAllUser();

        Assertions.assertEquals(expectedUserList, actualUserList);
    }
    

    @Test
    @DisplayName("Get User By Name")
    public void getUserByNameTest()
    {
        List<User> users = new ArrayList<User>() {
            {
                add(new User("1", "XYZ"));
                add(new User("2", "ABC"));
                add(new User("3", "PQR"));
            }
        };

        List<User> expectedUser = List.of(new User("2", "ABC"));

        when(userRepositoryMock.getByName("ABC")).thenReturn(expectedUser);
        List<User> actualUser = userService.getUserByName("ABC");

        Assertions.assertEquals(expectedUser, actualUser);

    }
    
    @Test
    @DisplayName("Return null in get User")
    public void getUserByNameReturnNullTest() {
        List<User> users = new ArrayList<User>() {
            {
                add(new User("1", "XYZ"));
                add(new User("2", "ABC"));
                add(new User("3", "PQR"));
            }
        };

        List<User> expectedUser = null;
        when(userRepositoryMock.getByName("ABCD")).thenReturn(expectedUser);
        List<User> actualUser = userService.getUserByName("ABCD");

        Assertions.assertEquals(expectedUser, actualUser);

    }
}
