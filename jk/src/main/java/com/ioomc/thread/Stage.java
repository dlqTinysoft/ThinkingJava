package com.ioomc.thread;

/**
 * Created by 董乐强 on 2017/12/6.
 */
public class Stage extends  Thread {



    @Override
    public void run() {
        System.out.println("开始演出");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用Runnable接口创建线程
       ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
       ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
       //启动线程
       Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty,"隋军");
       Thread armyOfRevolt = new Thread(armyTaskOfRevolt,"农民起义军");
       armyOfSuiDynasty.start();
       armyOfRevolt.start();

//舞台线程休眠，大家关心军队的弑杀
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //armyTaskOfSuiDynasty.keepRunning=false;
        //armyTaskOfRevolt.keepRunning=false;
        System.out.println("正当双方激战震撼,半路杀出个程咬金");

         Thread mrCheng = new KeyPersonThread();
         mrCheng.setName("程咬金");
         System.out.println("程咬金为了百姓安居乐业，尽快结束战斗");
        //军队停止作战
        armyTaskOfSuiDynasty.keepRunning=false;
         armyTaskOfRevolt.keepRunning=false;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //历史大戏留给关键人物
        mrCheng.start();

        //所有线程等待程先生完成历史使命
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("战争结束");
        System.out.println("谢谢观赏");

    }


}
