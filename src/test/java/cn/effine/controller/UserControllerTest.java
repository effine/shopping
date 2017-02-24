package cn.effine.controller;

import cn.effine.model.User;
import cn.effine.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created by effine on 2/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testSignup() {
        // TODO 下面的 mock 与 @InjectMocks 声明的区别？
        //UserController controller = mock(UserController.class);
//        UserService userService  = mock(UserService.class);
        when(userService.signup(any(User.class))).thenReturn(true);
        Assert.assertTrue(Boolean.parseBoolean(userController.signup(new User())));
    }
}
