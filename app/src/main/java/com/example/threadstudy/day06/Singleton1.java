package com.example.threadstudy.day06;

/**
 * 单例 - 饿汉式（静态常量）
 *
 * 饿汉： 提前创建好实例
 * 懒汉： 用的时候才去创建
 *
 * static修饰的静态变量，是JVM在类加载的时候就会创建。
 *
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

}
