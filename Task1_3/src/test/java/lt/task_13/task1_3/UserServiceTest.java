package lt.task_13.task1_3;

import lt.task_13.task1_3.exceptions.BadEmailException;
import lt.task_13.task1_3.exceptions.BadPasswordException;
import lt.task_13.task1_3.exceptions.BadPhoneNumberException;
import lt.task_13.task1_3.model.User;
import lt.task_13.task1_3.repositories.UserRepository;
import lt.task_13.task1_3.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void testFindById() {
        User user = new User(1, "", "", "", "", "", "");
        when(userRepository.findById(Mockito.anyInt())).thenReturn(user);

        User found = userService.findById(1);
        verify(userRepository).findById(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    public void testFindAll() {
        User user = new User(1, "", "", "", "", "", "");
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);
        Iterable<User> resultIterable =  userService.findAll();
        ArrayList<User> result = new ArrayList<>();
        resultIterable.forEach(result::add);
        verify(userRepository).findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testAdd() {
        User user = new User(1, "Tomas", "Tomas", "+37011111111",
                        "tomas@gmail.com", "adress", "pAssword@1");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User usr = null;
        try {
            usr = userService.add(user);
        } catch (BadEmailException | BadPasswordException | BadPhoneNumberException e) {
           return;
        }
        verify(userRepository).save(Mockito.any(User.class));
        assertNotNull(usr);
    }

    @Test
    public void testDeleteById() {
        userService.deleteById(1);
        verify(userRepository).deleteById(Mockito.anyInt());
    }

    @Test
    public void testUpdate() {
        User user = new User(1, "Tomas", "Tomas", "+37011111111",
                "tomas@gmail.com", "adress", "pAssword@1");

        User usr = null;
        try {
            usr = userService.update(user, Mockito.anyInt());
        } catch (BadEmailException | BadPasswordException | BadPhoneNumberException e) {
            return;
        }
        
        verify(userRepository).save(Mockito.any(User.class));
    }
}
