package hpu.edu.dlq;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 董乐强 on 2017/11/22.
 */
@RestController
public class HelloController {

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "hello world";
    }
}
