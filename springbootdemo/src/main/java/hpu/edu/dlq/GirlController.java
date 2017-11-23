package hpu.edu.dlq;

import hpu.edu.dlq.exception.Result;
import hpu.edu.dlq.exception.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/22.
 */
@RestController
public class GirlController {
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;

    /**
     * 查询所有girl的信息
     *
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> girlList() {
        logger.info("girlList");
        System.out.println("girlList");
        return girlRepository.findAll();
    }

    //post请求
    @PostMapping("/girls")
//    public Girl girlAdd(@RequestParam("cupSize")String cupSize,
//                        @RequestParam("age") Integer age){
//        Girl girl = new Girl();
//        girl.setCupSize(cupSize);
//        girl.setAge(age);
//        return girlRepository.save(girl);
//    }
    /**
     * 表单可以封装为一个对象
     * 验证后的结果，放到BindingResult里面 @Valid进行标识要验证这个对象
     */
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        //判断，验证是否通过
        if (bindingResult.hasErrors()) {
//            Result<Girl> result = new Result<>();
//            result.setCode(1);
//            result.setMessge(bindingResult.getFieldError().getDefaultMessage());
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//        Result<Girl> result = new Result<>();
//        result.setCode(0);
//        result.setMessge("成功");
//        result.setData(girlRepository.save(girl));
        return ResultUtil.success(girlRepository.save(girl));
    }

    @GetMapping("/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    @PutMapping("/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    @DeleteMapping("/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 根据年龄来进行查询
     *
     * @param age
     * @return
     */
    @GetMapping("/girls/age")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping("/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    @GetMapping(value="/girls/getAge/{id}")
    //不能够直接抛，得不到我们想要的结果
    public void getAge(@PathVariable("id") Integer id)  {
        //抛出的异常，被拦截了
        girlService.getAge(id);
    }
}
