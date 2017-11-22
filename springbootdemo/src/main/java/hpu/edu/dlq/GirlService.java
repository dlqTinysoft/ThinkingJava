package hpu.edu.dlq;

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
}
