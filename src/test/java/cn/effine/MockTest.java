package cn.effine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author effine
 * @Date 2017-02-27 10:35
 * @email zhangyafei#co-mall.com
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @Test
    public void testVerify() {
        List<Integer> list = mock(List.class);
        list.add(1);
        verify(list, times(1)).add(1);
    }
}
