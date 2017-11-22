package hpu.edu.dlq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 董乐强 on 2017/11/22.
 */
@Repository
//这个是持久层框架，继承JpaRepository,传入两个泛型，一个是Girl,一个是主键
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    //    通过年龄来查询,方法名不能乱写，必须这样写，这是规定
    public List<Girl> findByAge(Integer age);

}
