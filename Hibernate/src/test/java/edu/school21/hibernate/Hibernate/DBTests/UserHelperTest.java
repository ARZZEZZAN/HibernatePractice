package edu.school21.hibernate.Hibernate.DBTests;

import edu.school21.hibernate.Entity.User;
import edu.school21.hibernate.Hibernate.UserHelper;
import edu.school21.hibernate.Exceptions.UserParametersException;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserHelperTest {
    private List<User> users;
    private int size = 0;
    private final User userArmen =  new User("Armen", "15061999");
    private final User userSave =  new User("Sanya", "098765432");
    @BeforeEach
    void resetParameters() {
        users = UserHelper.getUserList();
        size = users.size();
    }
    @org.junit.jupiter.api.Test
    void UserListSizeTest() {
        assertEquals(users.size(), size);
    }
    @org.junit.jupiter.api.Test
    void UserListElements() {
        userArmen.setId(1L);
        assertEquals(users.get(0).getId(), userArmen.getId());
        assertEquals(users.get(0).getLogin(), userArmen.getLogin());
        assertEquals(users.get(0).getPassword(), userArmen.getPassword());
    }
    @org.junit.jupiter.api.Test
    void UserSaveTest() throws UserParametersException {
        UserHelper.saveUser(userSave);
        users = UserHelper.getUserList();
        size = users.size();
        assertEquals(size, 7);
        UserHelper.deleteUser(userSave);
    }
    @org.junit.jupiter.api.Test
    void UserSaveExceptionTest() throws UserParametersException {
        userSave.setLogin(null);
        assertThrows(UserParametersException.class,() -> {
            UserHelper.saveUser(userSave);
        });
    }
    @org.junit.jupiter.api.Test
    void UserSaveExceptionTest1() throws UserParametersException {
        userSave.setPassword(null);
        assertThrows(UserParametersException.class,() -> {
            UserHelper.saveUser(userSave);
        });
    }
    @org.junit.jupiter.api.Test
    void UserGetTest() throws UserParametersException {
        User userGet = UserHelper.getUserById(1L);
        assertEquals(users.get(0).getId(), userGet.getId());
        assertEquals(users.get(0).getMessages().toString(), userGet.getMessages().toString());
        assertEquals(users.get(0).getRoomsSocial().toString(), userGet.getRoomsSocial().toString());
        assertEquals(users.get(0).getRoomCreated().toString(), userGet.getRoomCreated().toString());
    }
    @org.junit.jupiter.api.Test
    void UserUpdateTest() throws UserParametersException {
        User userGet = new User("Armen", "150699V");
        userGet.setId(1L);
        UserHelper.updateUserPassword(userGet.getId(), userGet.getPassword());
        assertEquals("150699V", UserHelper.getUserById(1L).getPassword());
        UserHelper.updateUserPassword(userGet.getId(), "15061999");
    }


}