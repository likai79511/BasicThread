package com.example.threadstudy.day06;


/**
 * 单例模式 -   静态内部类，属于懒汉式
 *
 * JVM在类加载时，不会加载内部类。 所以也是延时加载。
 * 而内部类加载的时候的线程安全 是由JVM保证的
 *
 */
public class Singleton5 {

    private Singleton5(){}

    private static class SingletonInstance{
        private static Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
