package com.ioomc.thread;

/**
 * Created by 董乐强 on 2017/12/6.
 */
public class ArmyRunnable implements Runnable {
   //volatile 保证了线程可以正确读取其他线程写入的值
   //可见性 references  JMM内存模型
    volatile boolean keepRunning=true;

    public void run() {
        while(keepRunning){
            for(int i = 0 ; i<5;i++){
                System.out.println(Thread.currentThread().getName()+"进功对方["+i+"]");
                //让出了处理器时间,下次该谁进攻还不一定？
                Thread.yield();
            }

            System.out.println(Thread.currentThread().getName()+"结束战斗");
        }
    }
}
