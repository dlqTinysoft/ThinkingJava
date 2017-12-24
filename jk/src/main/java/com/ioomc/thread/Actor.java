package com.ioomc.thread;

/**
 * Created by 董乐强 on 2017/12/6.
 */
public class Actor extends Thread {
    @Override
    public void run() {
        System.out.println(getName()+"是一个演员");
        int count = 0 ;
        boolean keepRunning = true;
        while(keepRunning) {
            System.out.println(getName() + "登台演出：" + (++count));
            if(count == 100)
                keepRunning=false;
            if(count %10 ==0)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(getName()+"的演出结束");
    }
}

