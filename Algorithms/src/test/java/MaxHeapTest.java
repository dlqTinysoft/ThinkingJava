import com.ccnu.edu.cn.MaxHeap;
import com.ccnu.edu.cn.SortClass;
import com.ccnu.edu.cn.SortClassImpl;
import org.junit.Test;

/**
 * Created by 董乐强 on 2017/12/5.
 * 用来测试最大堆操作的方法
 */
public class MaxHeapTest {

    @Test
    public void test(){
        Integer [] data = new Integer[]{6,8,4,2};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(data);
        System.out.println(maxHeap.getMaxHeadTop());
        System.out.println(maxHeap.getMaxHeadTop());
        System.out.println(maxHeap.getMaxHeadTop());
        System.out.println(maxHeap.getMaxHeadTop());
    }

    @Test
    public void testHeapSort(){
        Integer [] data = new Integer[]{6,8,4,2,58,14,78,36,1,77,65,21,19};
        SortClass sortClass = new SortClassImpl<>();
        sortClass.HeapSort(data);
        for(Integer i:data)
            System.out.print(i+" ");

    }
}
