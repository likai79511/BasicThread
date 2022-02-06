package com.example.threadstudy.day07;


import java.util.concurrent.ThreadFactory;

/**
 *  形成死锁： 两个线程相互持有对方需要的资源
 *
 *  Thread-1: 先持有lock1，进入synchronized 方法块
 *            sleep：500 ms 且没有释放锁，当前仍持有lock1，处于Time_Waiting状态
 *  Thread-2：先持有lock2，进入synchronized 方法块
 *            sleep：500 ms 且没有释放锁，当前仍持有lock2，处于Time_Waiting状态
 *
 *  Thread-1: 结束500 ms后，需要获取lock2， 但此时lock2已经被Thread-2所持有，只能处于Blocked状态
 *  Thread-2: 结束500 ms后，需要获取lock1， 但此时lock1已经被Thread-1所持有，只能处于Blocked状态
 *
 * 形成相互等待，死锁产生。
 *
 * ** 只有在synchronized方法块全部执行完后 才会释放所持有的锁。
 *
 * 死锁定位：
 * 1. 使用jps来获取
 * ...12004 DeadLockTest
 *
 * 2. 在使用jstack获取死锁堆栈信息 jstack 12004， 信息如下：
 *
 *Found one Java-level deadlock:
 * =============================
 * "Thread-0":
 *   waiting to lock monitor 0x00000244be448200 (object 0x000000071fb227f0, a java.lang.Object),
 *   which is held by "Thread-1"
 * "Thread-1":
 *   waiting to lock monitor 0x00000244be448000 (object 0x000000071fb22800, a java.lang.Object),
 *   which is held by "Thread-0"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-0":
 *         at com.example.threadstudy.day05.DeadLockTest$Action1.run(DeadLockTest.java:32)
 *         - waiting to lock <0x000000071fb227f0> (a java.lang.Object)
 *         - locked <0x000000071fb22800> (a java.lang.Object)
 *         at java.lang.Thread.run(java.base@11.0.11/Thread.java:829)
 * "Thread-1":
 *         at com.example.threadstudy.day05.DeadLockTest$Action2.run(DeadLockTest.java:52)
 *         - waiting to lock <0x000000071fb22800> (a java.lang.Object)
 *         - locked <0x000000071fb227f0> (a java.lang.Object)
 *         at java.lang.Thread.run(java.base@11.0.11/Thread.java:829)
 *
 * Found 1 deadlock.
 *
 *
 *
 */
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    public static void main(String[] args) {
        Thread t1 = new Thread(new Run(1));
        Thread t2 = new Thread(new Run(2));
        t1.start();
        t2.start();


    }


    static class Run implements Runnable {

        private int num = 1;

        public Run(int num){
            this.num = num;
        }

        @Override
        public void run() {
            if (num == 1) {
                synchronized (lock1) {
                    System.out.println("--thread-"+num+"-- step-01--");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("--thread-"+num+"-- step-02--");
                    }
                }
            }else{
                synchronized (lock2) {
                    System.out.println("--thread-"+num+"-- step-01--");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("--thread-"+num+"-- step-02--");
                    }
                }
            }

        }
    }
}
