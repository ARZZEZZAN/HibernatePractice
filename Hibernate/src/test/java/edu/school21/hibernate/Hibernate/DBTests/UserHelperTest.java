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
    private User userArmen =  new User("Armen", "15061999");
    private User userSave =  new User("Sanya", "098765432");
    @BeforeEach
    void resetShema() {
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
    }
    @org.junit.jupiter.api.Test
    void UserSaveExceptionTest() throws UserParametersException {
        userSave.setLogin(null);
        assertThrows(UserParametersException.class,() -> {
            UserHelper.saveUser(userSave);
        });
    }

}