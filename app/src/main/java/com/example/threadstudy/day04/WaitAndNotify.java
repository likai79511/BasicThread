package com.example.threadstudy.day04;

/**
 *
 * 描述： 两个线程t1,t2操作同一个synchronized对象，t1执行wait时，t2调用Notify.
 *
 * 执行结果：
 * --t1-run-start--
 * --t2-run-start-
 * --t2-run-end-
 * --t1-run-end-
 *
 * 结论：
 * 1. wait会释放锁。
 * 2. notify后t1不会立刻得到锁向下执行，而是等t2完全执行完毕且释放锁之后，t1才会真正的唤醒.
 */
public class WaitAndNotify{

    private static Object mObj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        Thread.sleep(200);
        t2.start();
    }


    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (mObj){
                System.out.println("--t1-run-start--");
                try {
                    mObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--t1-run-end-");
            }
        }
    }


    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (mObj){
                System.out.println("--t2-run-start-");
                mObj.notify();
                System.out.println("--t2-run-end-");
            }
        }
    }

}
