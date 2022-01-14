package com.example.threadstudy.day03;

/**
 * 两个线程 同时调用 synchronized 方法， 一个持有了锁，另一个在等待 持有锁的线程释放锁。
 *
 * t1:先持有了 synchronized的锁（this class），然后进入了sleep： TIMED_WAITING
 * t2: 因为t1持有了锁且没有释放，所以t2处于：BLOCKED
 *
 * t1: 在1s后 进入了wait（）： WAITING 。  （因为wait会释放锁）
 * t2: 因为t1执行了wait,而wait会释放锁，所以t2可以进入 synchronized方法体，执行了sleep: TIMED_WAITING
 *
 * t1: wait不会自动唤醒，所以一直处于：WAITING
 * t2: 执行了wait后 和 t1一样，一直处于：WAITING
 */
public class ThreadWaitingStatus implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Runnable tb = new ThreadWaitingStatus();
        Thread t1 = new Thread(tb);
        Thread t2 = new Thread(tb);
        t1.start();
        t2.start();
        Thread.sleep(100);
        System.out.println("--t1 -0 status: "+t1.getState());
        System.out.println("--t2 -0 status: "+t2.getState());
        Thread.sleep(1000);
        System.out.println("--t1 -1 status: "+t1.getState());
        System.out.println("--t2 -1 status: "+t2.getState());
        Thread.sleep(1000);
        System.out.println("--t1 -2 status: "+t1.getState());
        System.out.println("--t2 -2 status: "+t2.getState());
    }

    @Override
    public void run() {
        m1();
    }

    private synchronized void m1(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
