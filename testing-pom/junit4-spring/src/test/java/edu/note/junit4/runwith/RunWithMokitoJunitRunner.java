package edu.note.junit4.runwith;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.note.junit4.mock.UserRepository;
import edu.note.junit4.mock.UserService;

/**
 * @author jackylee
 * @date 2025-10-09 16:50
 */
@RunWith(MockitoJUnitRunner.class)
public class RunWithMokitoJunitRunner {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void testTrue() {
        Mockito.when(userRepo.getByUserName(Mockito.anyString())).thenReturn(true);
        String username = userService.getUser("Foo");
        Assert.assertEquals("FOO", username);
    }

    @Test
    public void testFalse() {
        Mockito.when(userRepo.getByUserName(Mockito.anyString())).thenReturn(false);
        String username = userService.getUser("Foo");
        Assert.assertNull(username);
    }
}
