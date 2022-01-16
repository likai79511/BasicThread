package com.example.threadstudy.day04;

/**
 * 两个线程交替打印数字
 *
 * 通过一个lock 去 wait 和 notify实现
 *
 */
public class PrintNumMain {

    private static int num = 0;

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Run(),"偶数");
        Thread t2 = new Thread(new Run(),"奇数");
        t1.start();
        Thread.sleep(100);
        t2.start();
    }


    static class Run implements Runnable {

        @Override
        public  void run() {
            synchronized (lock){
                while (num <= 100) {
                    System.out.println("--" + Thread.currentThread().getName() + "--" + num++);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
