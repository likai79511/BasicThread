package com.example.threadstudy.day05;

public class DeadLockTest{


    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Action1());
        Thread t2 = new Thread(new Action2());
        t1.start();
        t2.start();
    }


    static class Action1 implements Runnable{

        @Override
        public void run() {
            synchronized (lock2){
                System.out.println("---Action1 step-1--");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1){
                    System.out.println("---Action1 step-2--");
                }
            }
        }
    }

    static class Action2 implements Runnable{

        @Override
        public void run() {
            synchronized (lock1){
                System.out.println("---Action2 step-1--");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2){
                    System.out.println("---Action2 step-2--");
                }
            }
        }
    }

}
