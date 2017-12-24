1.进程
    程序的执行过程
    持有资源（共享内存和文件）和线程
 2.线程
   多个线程需要通讯，才可以交互
   互斥和同步
 3.java线程之初体验
    java对线程的支持
        java.lang.Thread 和java.lang.interface Runnable
        public void run();
    Thread常用方法
        void start()
        static void sleep(long millis);
        void join()
        static void yield();
        static Thread currentThread()
    隋唐演义小故事
    ArmyRunnable
    KeyPersonThread
    Stage