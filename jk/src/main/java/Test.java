import com.ioomc.thread.Actor;
import com.ioomc.thread.Actress;
import com.ioomc.thread.Stage;

/**
 * Created by 董乐强 on 2017/11/22.
 */
public class Test {


    public static void main(String [] args){
//        Thread actor = new Actor();
//        actor.setName("Mr.Thread");
//        actor.start();
//
//        Thread actressThread = new Thread(new Actress(),"Ms.Runnable");
//        actressThread.start();
         new Stage().start();
    }
}
