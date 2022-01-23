package com.example.threadstudy.day06;

/**
 * 单例模式 -   懒汉式（synchronized标记整个方法）
 *
 * 优点： 不会存在并发安全问题， 因为synchronized标记的方法具有原子性 和 可见性。
 * 确定： 无论INSTANCE是否已经初始化，多线程运行该方法都会抢锁等待，浪费资源。
 *
 */
public class Singleton3 {

    private Singleton3(){}

    private static Singleton3 INSTANCE = null;

    public synchronized static Singleton3 getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }

}
