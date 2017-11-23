package hpu.edu.dlq;

import hpu.edu.dlq.exception.GirlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Created by 董乐强 on 2017/11/22.
 */
@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    //    这个地方必须加事物，才可以的,要么全部插入，要不全不插入
//    加个事物的注解就ok了
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setAge(18);
        girlA.setCupSize("B");
        girlRepository.save(girlA);
        Girl girlB = new Girl();
        girlB.setAge(19);
        girlB.setCupSize("C");
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回你还在上小学吧 code = 100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code =101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     *通过id查询一个女生信息,测试
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
            return girlRepository.findOne(id);
        }
    }















