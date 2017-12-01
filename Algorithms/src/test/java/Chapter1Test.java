import com.ccnu.edu.cn.MaxHeap;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by 董乐强 on 2017/11/30.
 * 面试题，测试
 */
public class Chapter1Test {
    @Test
    public void test1() {
        int x = 3;
        switch (1) {
            case 1:
                System.out.println(x);
            case 2:
                System.out.println(x);
            default:
                System.out.println(5);
        }
    }

    @Test
    public void test2MaxHeap(){
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        for(int i = 1 ; i<=16;i++)
        maxHeap.insert(i);
        Iterator<Integer> iterator = maxHeap.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println();
        for(int i = 0 ;i<=15;i++) {
            System.out.print(maxHeap.getMaxHeadTop()+" ");
        }
    }
}
