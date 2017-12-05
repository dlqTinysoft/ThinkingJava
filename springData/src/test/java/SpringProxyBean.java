import com.ccnu.edu.domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * Created by 董乐强 on 2017/12/3.
 */
public class SpringProxyBean {

    private ApplicationContext ctx = null;


    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("proxy_bean.xml");
    }
    @After
    public void destory(){
        ctx=null;
    }

    @Test
    public void testProxy(){
         Student student = (Student) ctx.getBean("student");
        System.out.println(student);
    }



}
