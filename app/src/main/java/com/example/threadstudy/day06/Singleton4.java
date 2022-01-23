package com.example.threadstudy.day06;

/**
 * 单例模式 -  懒汉式（双重检查）
 *
 * 优点：相比于 synchronized标记整个方法来说，需要抢锁的范围降低，只有当INSTANCE没有
 * 初始化完成，且多线程并行执行，才会产生抢锁的问题。
 *
 * 延迟加载，效率较高
 *
 * 为什么INSTANCE变量需要用volatile修饰： 为了防止重排序
 * 1. 新建对象实际上有3个步骤， 并非原子操作 （创建空，call构造方法，赋值引用）
 * 2. 重排序会带来内部数据NullPointException (对象非空，内容为空，因为重排序所以call构造方法被放到最后面了)
 */
public class Singleton4 {

    private Singleton4() {
    }

    private volatile static Singleton4 INSTANCE = null;

    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }


}
