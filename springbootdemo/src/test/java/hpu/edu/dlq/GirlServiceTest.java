package hpu.edu.dlq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 董乐强 on 2017/11/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {
    @Test
    public void insertTwo() throws Exception {
    }

    @Test
    public void getAge() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
    }

    @Autowired
    private GirlService girlService;
    @Test
    public void findOneTest(){
        Girl girl = girlService.findOne(73);
        Assert.assertEquals(new Integer(13),girl.getAge());
    }
}
