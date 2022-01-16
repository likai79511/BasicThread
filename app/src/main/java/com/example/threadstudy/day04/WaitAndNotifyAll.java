package com.example.threadstudy.day04;


/**
 *  描述： 3个线程 t1,t2,t3.  t1和t2 同时执行同一个synchronized锁对象的方法，该方法中去wait。  t3则去notifyAll.
 *  结果：
 *  --Thread-0--start--
 *  --Thread-1--start--
 *  --Thread-2--start--
 *  --Thread-2--end--
 *  --Thread-0--end--
 *  --Thread-1--end--
 */
public class WaitAndNotifyAll implements Runnable{

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new WaitAndNotifyAll());
        Thread t2 = new Thread(new WaitAndNotifyAll());
        Thread3 t3 = new Thread3();

        t1.start();
        t2.start();
        Thread.sleep(300);
        t3.start();
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("--"+Thread.currentThread().getName()+"--start--");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--"+Thread.currentThread().getName()+"--end--");
        }
    }


    static class Thread3 extends Thread{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("--"+Thread.currentThread().getName()+"--start--");
                lock.notifyAll();
                System.out.println("--"+Thread.currentThread().getName()+"--end--");
            }
        }
    }
}
