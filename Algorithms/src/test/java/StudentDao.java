/**
 * Created by 董乐强 on 2017/12/1.
 */
public interface StudentDao {
    void test();
    default void test1(){
        System.out.println("hello world");
    }
}
