package com.mywallet.backend.app.service;

import com.mywallet.backend.app.dao.UserDao;
import com.mywallet.backend.app.models.User;
import com.mywallet.backend.app.utility.PasswordUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordUtility passwordUtility;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("testuser","password");
        when(passwordUtility.generateHash(user.getPassword())).thenReturn(user.getPassword());
        userService.createUser(user);

        verify(passwordUtility, times(1)).generateHash(user.getPassword());
        verify(userDao, times(1)).createUser(user);
    }

    @Test
    void testLoginUser_Success() {

        User user = new User("username", "password");
        when(userDao.getUserByUserName("username")).thenReturn(user);
        when(passwordUtility.verifyHash("password", user.getPassword())).thenReturn(true);

        User loggedInUser = userService.loginUser(user);

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    void testLoginUser_Failure() {
        String invalidUsername = "invalid_username";
        when(userDao.getUserByUserName(invalidUsername)).thenReturn(null);
        User loggedInUser = userService.loginUser(new User(invalidUsername, "password"));
        assertNull(loggedInUser);
    }

    @Test
    void testChangePassword() {
        User user = new User("testuser","password");

        when(userDao.changePassword(user)).thenReturn(user);

        User changedUser = userService.changePassword(user);

        assertEquals(user.getPassword(), changedUser.getPassword());
        verify(userDao, times(1)).changePassword(user);
    }

    @Test
    void testDeleteUser() {
        String username = "testuser";

        userService.deleteUser(username);

        verify(userDao, times(1)).deleteUser(username);
    }
}
